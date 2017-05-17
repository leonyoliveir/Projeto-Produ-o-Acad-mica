package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class Main {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		ArrayList<Projeto> projetos = new ArrayList<Projeto>();
		ArrayList<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
		int opcao;
		
		do {
			menu();
			try {
				opcao = entrada.nextInt();
				entrada.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Erro: Formato de entrada não é um número inteiro! Finalizando sistema...");
				opcao = 0;
			}
			switch (opcao) {
			case 1:
				cadastrar(entrada, pesquisadores);
				break;
			case 2:
				adicionar(entrada, projetos, pesquisadores);
				break;
			case 3:
				alocar(entrada, projetos, pesquisadores);
				break;
			case 4:
				alterar(entrada, projetos, pesquisadores);
				break;
			case 5:
				incluir(entrada, projetos, pesquisadores);
				break;
			case 6:
				consultaColaborador(entrada, pesquisadores);
				break;
			case 7:
				consultaProjeto(entrada, projetos);
				break;
			case 8:
				relatorio(entrada, projetos, pesquisadores);
				break;
			case 0:
				System.out.println("\nObrigado por utilizar o Sistema de Gestão de Produção Acadêmica Wolves and Villagers!");
				break;
			default:
				System.out.println("\nOpção inválida, tente novamente!\n");
				break;
			}
		} while (opcao != 0);
		entrada.close();

	}

	public static void menu() {
		System.out.println("\nBem vindo ao Sistema de Gestão de Produção Acadêmica Wolves and Villagers!\n");
		System.out.println("Por favor, escolha uma opção para iniciar:\n");
		System.out.println("1 - Cadastrar novo colaborador");
		System.out.println("2 - Adicionar um novo projeto de pesquisa");
		System.out.println("3 - Alocar um novo participante em um projeto de pesquisa");
		System.out.println("4 - Editar o status de um projeto de pesquisa");
		System.out.println("5 - Incluir informações em um projeto de pesquisa");
		System.out.println("6 - Consultar colaborador");
		System.out.println("7 - Consultar projeto de pesquisa");
		System.out.println("8 - Fornecer relatório de produção acadêmica");
		System.out.println("0 - Sair\n");
	}
	
	public static void participantes(ArrayList<Pesquisador> colaboradores){
		int i = 0;
		for(Pesquisador print : colaboradores){
			System.out.println(i + " - " + print.getNome() + "\n");
			i++;
		}
	}
	
	public static void professores(ArrayList<Pesquisador> colaboradores) {
		int i = 0;
		for(Pesquisador print : colaboradores){
			if(print.isProfessor()) System.out.println(i + " - " + print.getNome() + "\n");
			i++;
		}
	}
	
	public static void projetos(ArrayList<Projeto> projetos) {
		int i = 0;
		for(Projeto print : projetos){
			System.out.println(i + " - " + print.getTitulo() + "\n");
			i++;
		}
	}

	public static void cadastrar(Scanner entrada, ArrayList<Pesquisador> colaboradores) {
		System.out.println("\nPor favor, escolha o tipo de colaborador que deseja adicionar ao sistema\n");
		System.out.println("1 - Pesquisador\n2 - Graduando\n3 - Mestrando\n4 - Doutorando\n5 - Professor\n");
		
		try{
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
			if(novo != null){
				novo.addPesquisador(entrada);
				colaboradores.add(novo);
			}	
		} catch (InputMismatchException e) {
			System.err.println("Erro: Entrada não é um número inteiro!");
		}
	}
	
	public static void adicionar(Scanner entrada, ArrayList<Projeto> projetos, ArrayList<Pesquisador> pesquisadores) {
		Projeto novo = new Projeto();
		boolean valido = novo.addProjeto(entrada, pesquisadores);
		if (valido) projetos.add(novo);
	}
	
	public static void alocar(Scanner entrada, ArrayList<Projeto> projetos, ArrayList<Pesquisador> pesquisadores){
		System.out.println("\nEscolha o projeto ao qual o colaborador será associado:");
		projetos(projetos);
		int escolha = entrada.nextInt();
		try{
			Projeto escolhido = projetos.get(escolha);
			if(escolhido.getStatus().equals("Em Elaboração")) {
				System.out.println("\nEscolha o colaborador a ser associado:");
				participantes(pesquisadores);
				escolha = entrada.nextInt();
				if (!escolhido.isPesquisadorAdicionado(pesquisadores.get(escolha)) && !pesquisadores.get(escolha).isMaxProjetos()){
					escolhido.addPesquisador(pesquisadores.get(escolha));
					pesquisadores.get(escolha).associarProjeto(escolhido);
					System.out.println("\nColaborador associado ao projeto com sucesso!");
				} 
				else System.out.println("\nPesquisador já faz parte do projeto!");
			} 
			else System.out.println("\nEste projeto não pode mais receber colaboradores!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("\nPosição do array inexistente!");
		}
	}
	
	public static void alterar(Scanner entrada, ArrayList<Projeto> projetos, ArrayList<Pesquisador> pesquisadores) {
		System.out.println("\nEscolha o projeto ao qual deseja alterar o status:");
		projetos(projetos);
		int escolha = entrada.nextInt();
		try{
			Projeto escolhido = projetos.get(escolha);
			escolhido.trocarStatus(entrada);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("\nPosição do array inexistente!");
		}
	}
	
	public static void incluir(Scanner entrada, ArrayList<Projeto> projetos, ArrayList<Pesquisador> pesquisadores) {
		System.out.println("\nEscolha o projeto ao qual deseja incluir informações:");
		projetos(projetos);
		int escolha = entrada.nextInt();
		entrada.nextLine();
		try{
			Projeto escolhido = projetos.get(escolha);
			if (escolhido.getStatus().equals("Em Andamento")) escolhido.incluirPublicacoes(entrada);
			else System.out.println("\nEste projeto não pode receber novas publicações no momento!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("\nPosição do Array Inexistente!");
		}
	}
	
	public static void consultaColaborador(Scanner entrada, ArrayList<Pesquisador> colaboradores) {
		System.out.println("\nEscolha o colaborador ao qual deseja consultar:");
		participantes(colaboradores);
		int escolha = entrada.nextInt();
		entrada.nextLine();
		try{
			Pesquisador escolhido = colaboradores.get(escolha);
			escolhido.colaborador();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("\nPosição do Array Inexistente!");
		}
	}
	
	public static void consultaProjeto(Scanner entrada, ArrayList<Projeto> projetos) {
		System.out.println("\nEscolha o projeto ao qual deseja consultar:");
		projetos(projetos);
		int escolha = entrada.nextInt();
		entrada.nextLine();
		try{
			Projeto escolhido = projetos.get(escolha);
			escolhido.projeto();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("\nPosição do Array Inexistente!");
		}
	}
	
	public static void relatorio (Scanner entrada, ArrayList<Projeto> projetos, ArrayList<Pesquisador> pesquisadores) {
		System.out.println("Número de colaboradores cadastrados: " + pesquisadores.size());
		System.out.println("Total de projetos cadastrados: " + projetos.size());
		int andamento = 0, elaboracao = 0, concluido = 0;
		for(Projeto projeto : projetos) {
			if(projeto.getStatus().equals("Em Andamento")) andamento++;
			else if(projeto.getStatus().equals("Em Elaboração")) elaboracao++;
			else concluido++;
		}
		System.out.println("	Projetos em elaboração: " + elaboracao);
		System.out.println("	Projetos em andamento: " + andamento);
		System.out.println("	Projetos concluídos: " + concluido);
	}
}
