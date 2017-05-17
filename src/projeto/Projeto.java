package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.lang.IndexOutOfBoundsException;

public class Projeto implements Comparable<Projeto> {

	private String titulo; // Armazena o título do projeto
	private Date dataInicio; // Armazenam a data de início do projeto
	private Date dataTermino; // Armazenam a data de término do projeto
	private String agenciaFinanciadora; // Armazena o nome da agência
										// financiadora do projeto
	private double valorFinanciado; // Armazena o valor que foi financiado no
									// projeto
	private String objetivo; // Armazena o objetivo do projeto
	private String descricao; // Armazena a descrição do projeto
	private Pesquisador orientador; // Armazena o professor orientador do projeto
	private ArrayList<Pesquisador> participantes = new ArrayList<Pesquisador>(); // Armazena
																					// os
																					// participantes
																					// do
																					// projeto
	private String status; // Armazena o status atual do projeto
	private ArrayList<Publicação> publicacoes = new ArrayList<Publicação>(); // Armazena
																				// as
																				// publicações
																				// feitas

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAgenciaFinanciadora() {
		return agenciaFinanciadora;
	}

	public void setAgenciaFinanciadora(String agenciaFinanciadora) {
		this.agenciaFinanciadora = agenciaFinanciadora;
	}

	public double getValorFinanciado() {
		return valorFinanciado;
	}

	public void setValorFinanciado(double valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDataInicio(Date data) {
		this.dataInicio = data;
	}

	public void setDataTermino(Date data) {
		this.dataTermino = data;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public Date getDataTermino() {
		return this.dataTermino;
	}

	public void addPesquisador(Pesquisador pesquisador) {
		this.participantes.add(pesquisador);
	}

	public boolean isPesquisadorAdicionado(Pesquisador pesquisador) {
		return this.participantes.contains(pesquisador);
	}

	public boolean addProjeto(Scanner entrada, ArrayList<Pesquisador> pesquisadores) {
		if (pesquisadores.isEmpty() || !isProfessorCadastrado(pesquisadores)) {
			System.out.println("\nÉ necessário existirem colaboradores cadastrados para a criação do projeto!");
			return false;
		} else {
			boolean valido = false;
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			System.out.println("\nDigite o título do projeto:");
			this.setTitulo(entrada.nextLine());
			System.out.println("\nDigite o nome da agência financiadora:");
			this.setAgenciaFinanciadora(entrada.nextLine());
			System.out.println("\nDigite o valor financiado no projeto (Ex: 1000,00):");
			do {
				try {
					this.setValorFinanciado(entrada.nextDouble());
					valido = true;
					entrada.nextLine();
				} catch (InputMismatchException e) {
					System.err.println("\nValor inválido! Tente novamente...");
				}
			} while (!valido);
			valido = false;
			System.out.println("\nQual o objetivo do projeto?");
			this.setObjetivo(entrada.nextLine());
			System.out.println("\nDescreva o projeto de pesquisa:");
			this.setDescricao(entrada.nextLine());
			System.out.println("\nDigite a data de início do projeto (dd/mm/yyyy):");
			do {
				try {
					Date data = formato.parse(entrada.nextLine());
					this.setDataInicio(data);
					valido = true;
				} catch (ParseException e) {
					System.err.println("Data inválida, tente novamente!");
				}
			} while (!valido);
			valido = false;
			System.out.println("\nDigite a data de término do projeto (dd/mm/yyyy):");
			do {
				try {
					Date data = formato.parse(entrada.nextLine());
					if (data.after(this.dataInicio)) {
						this.setDataTermino(data);
						valido = true;
					} else
						System.out.println("\nData anterior à data de início!");
				} catch (ParseException e) {
					System.err.println("\nData inválida, tente novamente!");
				}
			} while (!valido);
			valido = false;
			do {
				System.out.println("\nEscolha um professor para orientar o projeto:\n");
				Main.professores(pesquisadores);
				try{
					int escolha = entrada.nextInt();
					if (pesquisadores.get(escolha).isProfessor() == true) {
						this.orientador = pesquisadores.get(escolha);
						pesquisadores.get(escolha).associarProjeto(this);
						this.setStatus("Em Elaboração");
						System.out.println("\nProjeto cadastrado com sucesso!");
						valido = true;
					} else
						System.out.println("\nEste pesquisador não é um professor!");
				} catch (InputMismatchException e) {
					System.err.println("\nFormato de entrada não é um número inteiro!");
					entrada.nextLine();
				} catch (IndexOutOfBoundsException e) {
					System.err.println("\nPosição do Array Inexistente!");
				}
			} while (valido == false);
			return true;
		}

	}

	public void trocarStatus(Scanner entrada) {
		System.out.println("\nO status atual do projeto é " + this.getStatus() + ". Deseja alterar?\n1 - Sim\n2 - Não");
		try {
			int escolha = entrada.nextInt();
			if (escolha == 1) {
				if (this.getStatus().equals("Em Elaboração")) {
					if (this.participantes.isEmpty())
						System.out.println("\nProjeto não possui participantes ainda!");
					else {
						this.setStatus("Em Andamento");
						System.out.println("\nStatus alterado de 'Em Elaboração' para 'Em Andamento'");
					}
				} else if (this.getStatus().equals("Em Andamento")) {
					if (this.publicacoes.isEmpty())
						System.out.println(
								"\nO status desse projeto não pode ser alterado para concluído pois não possui publicações associadas!");
					else {
						this.setStatus("Concluído");
						System.out.println("\nStatus alterado de 'Em Andamento' para 'Concluído'!");
					}
				} else
					System.out.println("\nProjeto já foi concluído!");
			}
		} catch (InputMismatchException e) {
			System.err.println("\nFormato de entrada não é um número inteiro!");
			entrada.nextLine();
		}
	}

	public void incluirPublicacoes(Scanner entrada, ArrayList<Publicação> geralpublicacoes) {
		Publicação publicacao = new Publicação();
		publicacao.setProjetoAssociado(this.getTitulo());
		System.out.println("\nDigite o título da publicação:");
		publicacao.setTitulo(entrada.nextLine());
		System.out.println("\nDigite o nome da conferência onde foi divulgada a publicação:");
		publicacao.setConferencia(entrada.nextLine());
		System.out.println("\nDigite o ano de publicação:");
		publicacao.setAnoPublicacao(entrada.nextInt());
		System.out.println("\nQual o professor orientador da publicação?");
		Main.professores(this.participantes);
		try {
			int escolha = entrada.nextInt();
			boolean valido = false;
			while (!valido) {
				if (this.participantes.get(escolha).isProfessor()) {
					publicacao.addAutor(this.participantes.get(escolha));
					this.participantes.get(escolha).associarPublicaco(publicacao);
					Professor prof = (Professor) this.participantes.get(escolha);
					prof.addOrientacao(publicacao.getTitulo());
					valido = true;
				} else
					System.out.println("\nColaborador não é professor!");
			}
			boolean finalizar = false;
			while (!finalizar) {
				System.out.println("\nAdicione o autor da publicação:");
				Main.participantes(this.participantes);

				escolha = entrada.nextInt();
				if (!this.participantes.get(escolha).isProfessor()
						&& !publicacao.isAutorAdicionado(this.participantes.get(escolha))) {
					publicacao.addAutor(this.participantes.get(escolha));
					this.participantes.get(escolha).associarPublicaco(publicacao);
					System.out.println("\nAutor adicionado!");
				} else {
					System.out.println("\nAutor já foi adicionado!");
				}
				System.out.println("\nDeseja adicionar outro autor?\n1 - Sim\n2 - Não");
				escolha = entrada.nextInt();
			}
			if (escolha == 2)
				finalizar = true;
		} catch (InputMismatchException e) {
			System.err.println("\nEntrada não é um número inteiro!");
			entrada.nextLine();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("\nPosição do Array Inexistente!");
		}
		this.publicacoes.add(publicacao);
		geralpublicacoes.add(publicacao);
		System.out.println("\nPublicação adicionada com sucesso!");
	}

	public static boolean isProfessorCadastrado(ArrayList<Pesquisador> participante) {
		boolean is = false;
		for (Pesquisador pesq : participante) {
			if (pesq.isProfessor())
				is = true;
		}
		return is;
	}

	public void projeto() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		formato.setLenient(false);
		System.out.println("Título do projeto: " + this.titulo);
		System.out.println("Objetivo do projeto: " + this.objetivo);
		System.out.println("Descrição do projeto: " + this.descricao);
		System.out.println("Agência financiadora do projeto: " + this.agenciaFinanciadora);
		System.out.println("Valor financiado no projeto: R$" + this.valorFinanciado);
		System.out.println("Data de início do projeto: " + formato.format(this.dataInicio));
		System.out.println("Data de término do projeto: " + formato.format(this.dataTermino));
		System.out.println("Status atual do projeto: " + this.status);
		System.out.println("Colaboradores participantes do projeto: ");
		for (Pesquisador participante : this.participantes) {
			if (!participante.isProfessor())
				System.out.println("	" + participante.getNome());
		}
		System.out.println("Professor orientador do projeto: " + this.orientador.getNome());
		System.out.println("Publicações associadas ao projeto: ");
		Collections.sort(this.publicacoes);
		for (Publicação publicacao : this.publicacoes) {
			System.out.println("	Título da publicação: " + publicacao.getTitulo());
			System.out.println("	  Ano de publicação: " + publicacao.getAnoPublicacao());
			System.out.println("	  Conferência: " + publicacao.getConferencia());
		}
	}

	@Override
	public int compareTo(Projeto projeto) {
		if (this.dataTermino.after(projeto.dataTermino))
			return -1;
		else if (this.dataTermino.before(projeto.dataTermino))
			return 1;
		else
			return 0;
	}
}
