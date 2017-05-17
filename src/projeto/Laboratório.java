package projeto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;

public class Laborat�rio {

	private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
	private ArrayList<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
	private ArrayList<Publica��o> publicacoes = new ArrayList<Publica��o>();

	public void cadastrar(Scanner entrada) {
		System.out.println("\nPor favor, escolha o tipo de colaborador que deseja adicionar ao sistema\n");
		System.out.println("1 - Pesquisador\n2 - Graduando\n3 - Mestrando\n4 - Doutorando\n5 - Professor\n");

		try {
			int escolha = entrada.nextInt();
			entrada.nextLine();
			Pesquisador novo = null;

			switch (escolha) {
			case 1:
				novo = new Pesquisador();
				break;
			case 2:
				novo = new Graduando();
				break;
			case 3:
				novo = new Mestrando();
				break;
			case 4:
				novo = new Doutorando();
				break;
			case 5:
				novo = new Professor();
				break;
			default:
				System.out.println("\nOp��o inv�lida! Voltando ao menu inicial...\n");
			}
			if (novo != null) {
				novo.addPesquisador(entrada);
				pesquisadores.add(novo);
			}
		} catch (InputMismatchException e) {
			System.err.println("\nErro: Entrada n�o � um n�mero inteiro!");
			entrada.nextLine();
		}
	}

	public void adicionar(Scanner entrada) {
		Projeto novo = new Projeto();
		boolean valido = novo.addProjeto(entrada, pesquisadores);
		if (valido)
			projetos.add(novo);
	}

	public void alocar(Scanner entrada) {
		if (!isProjetosVazio() && !isPesquisadoresVazio()) {
			System.out.println("\nEscolha o projeto ao qual o colaborador ser� associado:");
			Main.projetos(projetos);
			try {
				int escolha = entrada.nextInt();
				Projeto escolhido = projetos.get(escolha);
				if (escolhido.getStatus().equals("Em Elabora��o")) {
					System.out.println("\nEscolha o colaborador a ser associado:");
					Main.participantes(pesquisadores);
					escolha = entrada.nextInt();
					if (!escolhido.isPesquisadorAdicionado(pesquisadores.get(escolha))
							&& !pesquisadores.get(escolha).isMaxProjetos()) {
						escolhido.addPesquisador(pesquisadores.get(escolha));
						pesquisadores.get(escolha).associarProjeto(escolhido);
						System.out.println("\nColaborador associado ao projeto com sucesso!");
					} else
						System.out.println("\nPesquisador j� faz parte do projeto!");
				} else
					System.out.println("\nEste projeto n�o pode mais receber colaboradores!");
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosi��o do array inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("Entrada n�o � um n�mero inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nN�o existem projetos e/ou colaboradores cadastrados!");
		}
	}

	public void alterar(Scanner entrada) {
		if (!isProjetosVazio()) {
			System.out.println("\nEscolha o projeto ao qual deseja alterar o status:");
			Main.projetos(projetos);
			try {
				int escolha = entrada.nextInt();
				Projeto escolhido = projetos.get(escolha);
				escolhido.trocarStatus(entrada);
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosi��o do array inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("Entrada n�o � um n�mero inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nN�o existem projetos cadastrados!");
		}
	}

	public void incluir(Scanner entrada) {
		if (!isProjetosVazio()) {
			System.out.println("\nEscolha o projeto ao qual deseja incluir informa��es:");
			Main.projetos(projetos);
			try {
				int escolha = entrada.nextInt();
				entrada.nextLine();
				Projeto escolhido = projetos.get(escolha);
				if (escolhido.getStatus().equals("Em Andamento"))
					escolhido.incluirPublicacoes(entrada, publicacoes);
				else
					System.out.println("\nEste projeto n�o pode receber novas publica��es no momento!");
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosi��o do Array Inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("\nEntrada n�o � um n�mero inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nN�o existem colaboradores cadastrados!");
		}
	}

	public void consultaColaborador(Scanner entrada) {
		if (!isPesquisadoresVazio()) {
			System.out.println("\nEscolha o colaborador ao qual deseja consultar:");
			Main.participantes(pesquisadores);
			try {
				int escolha = entrada.nextInt();
				entrada.nextLine();
				Pesquisador escolhido = pesquisadores.get(escolha);
				escolhido.colaborador();
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosi��o do Array Inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("\nEntrada n�o � um n�mero inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nN�o existem colaboradores cadastrados!");
		}
	}

	public void consultaProjeto(Scanner entrada) {
		if (!isProjetosVazio()) {
			System.out.println("\nEscolha o projeto ao qual deseja consultar:");
			Main.projetos(projetos);
			try {
				int escolha = entrada.nextInt();
				entrada.nextLine();
				Projeto escolhido = projetos.get(escolha);
				escolhido.projeto();
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosi��o do Array Inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("\nEntrada n�o � um n�mero inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nN�o existem projetos cadastrados!");
		}
	}

	public void relatorio(Scanner entrada) {
		System.out.println("N�mero de colaboradores cadastrados: " + pesquisadores.size());
		System.out.println("Total de projetos cadastrados: " + projetos.size());
		int andamento = 0, elaboracao = 0, concluido = 0;
		for (Projeto projeto : projetos) {
			if (projeto.getStatus().equals("Em Andamento"))
				andamento++;
			else if (projeto.getStatus().equals("Em Elabora��o"))
				elaboracao++;
			else
				concluido++;
		}
		System.out.println("	Projetos em elabora��o: " + elaboracao);
		System.out.println("	Projetos em andamento: " + andamento);
		System.out.println("	Projetos conclu�dos: " + concluido);
		System.out.println("N�mero de publica��es cadastradas: " + publicacoes.size());
	}

	public boolean isProjetosVazio() {
		return this.projetos.isEmpty();
	}

	public boolean isPesquisadoresVazio() {
		return this.pesquisadores.isEmpty();
	}
}
