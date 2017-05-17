package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		
		Laboratório lab = new Laboratório();
		int opcao = -1;
		Scanner entrada = new Scanner(System.in);
		
		do {
			menu();
			try {
				opcao = entrada.nextInt();
				entrada.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Erro: Formato de entrada não é um número inteiro! Finalizando sistema...");
				entrada.nextLine();
			}
			switch (opcao) {
			case 1:
				lab.cadastrar(entrada);
				break;
			case 2:
				lab.adicionar(entrada);
				break;
			case 3:
				lab.alocar(entrada);
				break;
			case 4:
				lab.alterar(entrada);
				break;
			case 5:
				lab.incluir(entrada);
				break;
			case 6:
				lab.consultaColaborador(entrada);
				break;
			case 7:
				lab.consultaProjeto(entrada);
				break;
			case 8:
				lab.relatorio(entrada);
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

	
}
