package projeto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;

public class Laboratório {

	private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
	private ArrayList<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
	private ArrayList<Publicação> publicacoes = new ArrayList<Publicação>();

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
				System.out.println("\nOpção inválida! Voltando ao menu inicial...\n");
			}
			if (novo != null) {
				novo.addPesquisador(entrada);
				pesquisadores.add(novo);
			}
		} catch (InputMismatchException e) {
			System.err.println("\nErro: Entrada não é um número inteiro!");
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
			System.out.println("\nEscolha o projeto ao qual o colaborador será associado:");
			Main.projetos(projetos);
			try {
				int escolha = entrada.nextInt();
				Projeto escolhido = projetos.get(escolha);
				if (escolhido.getStatus().equals("Em Elaboração")) {
					System.out.println("\nEscolha o colaborador a ser associado:");
					Main.participantes(pesquisadores);
					escolha = entrada.nextInt();
					if (!escolhido.isPesquisadorAdicionado(pesquisadores.get(escolha))
							&& !pesquisadores.get(escolha).isMaxProjetos()) {
						escolhido.addPesquisador(pesquisadores.get(escolha));
						pesquisadores.get(escolha).associarProjeto(escolhido);
						System.out.println("\nColaborador associado ao projeto com sucesso!");
					} else
						System.out.println("\nPesquisador já faz parte do projeto!");
				} else
					System.out.println("\nEste projeto não pode mais receber colaboradores!");
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosição do array inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("Entrada não é um número inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nNão existem projetos e/ou colaboradores cadastrados!");
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
				System.err.println("\nPosição do array inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("Entrada não é um número inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nNão existem projetos cadastrados!");
		}
	}

	public void incluir(Scanner entrada) {
		if (!isProjetosVazio()) {
			System.out.println("\nEscolha o projeto ao qual deseja incluir informações:");
			Main.projetos(projetos);
			try {
				int escolha = entrada.nextInt();
				entrada.nextLine();
				Projeto escolhido = projetos.get(escolha);
				if (escolhido.getStatus().equals("Em Andamento"))
					escolhido.incluirPublicacoes(entrada, publicacoes);
				else
					System.out.println("\nEste projeto não pode receber novas publicações no momento!");
			} catch (IndexOutOfBoundsException e) {
				System.err.println("\nPosição do Array Inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("\nEntrada não é um número inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nNão existem colaboradores cadastrados!");
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
				System.err.println("\nPosição do Array Inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("\nEntrada não é um número inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nNão existem colaboradores cadastrados!");
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
				System.err.println("\nPosição do Array Inexistente!");
			} catch (InputMismatchException e) {
				System.err.println("\nEntrada não é um número inteiro!");
				entrada.nextLine();
			}
		} else {
			System.out.println("\nNão existem projetos cadastrados!");
		}
	}

	public void relatorio(Scanner entrada) {
		System.out.println("Número de colaboradores cadastrados: " + pesquisadores.size());
		System.out.println("Total de projetos cadastrados: " + projetos.size());
		int andamento = 0, elaboracao = 0, concluido = 0;
		for (Projeto projeto : projetos) {
			if (projeto.getStatus().equals("Em Andamento"))
				andamento++;
			else if (projeto.getStatus().equals("Em Elaboração"))
				elaboracao++;
			else
				concluido++;
		}
		System.out.println("	Projetos em elaboração: " + elaboracao);
		System.out.println("	Projetos em andamento: " + andamento);
		System.out.println("	Projetos concluídos: " + concluido);
		System.out.println("Número de publicações cadastradas: " + publicacoes.size());
	}

	public boolean isProjetosVazio() {
		return this.projetos.isEmpty();
	}

	public boolean isPesquisadoresVazio() {
		return this.pesquisadores.isEmpty();
	}
}
