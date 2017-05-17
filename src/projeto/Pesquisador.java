package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Pesquisador {

	private String nome;					// Armazena o nome do participante
	private String email;					// Armazena o email do participante
	private ArrayList<Projeto> projetos = new ArrayList<Projeto>();	// Armazena os projetos que o participante faz parte
	private ArrayList<Publicação> publicacoes = new ArrayList<Publicação>();
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean isProfessor(){
		return false;
	}
	
	public boolean isMaxProjetos() {
		return false;
	}
	
	public int numeroProjetos() {
		return this.projetos.size();
	}
	
	public void addPesquisador(Scanner entrada) {
		System.out.println("\nDigite o nome do pesquisador:");
		this.setNome(entrada.nextLine());
		System.out.println("\nDigite o email do pesquisador:");
		this.setEmail(entrada.nextLine());
		System.out.println("\nPesquisador cadastrado com sucesso!\n");
	}
	
	public void associarProjeto(Projeto projeto) {
		this.projetos.add(projeto);
	}
	
	public void associarPublicaco(Publicação publicacao) {
		this.publicacoes.add(publicacao);
	}
	
	public void colaborador() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		formato.setLenient(false);
		System.out.println("Nome do colaborador: " + this.nome);
		System.out.println("E-mail do colaborador: " + this.email);
		System.out.println("Projetos no qual o colaborador está inscrito: ");
		this.projetos.sort(new DateComparator());
		for(Projeto projeto : this.projetos) {
			System.out.println("	Título: "  + projeto.getTitulo());
			System.out.println("	  Status atual: " + projeto.getStatus());
			System.out.println("	  Data de início: " + formato.format(projeto.getDataInicio()));
			System.out.println("	  Data de término: " + formato.format(projeto.getDataTermino()));
		}
		System.out.println("Publicações na qual o colaborador foi autor: ");
		for(Publicação publicacao : this.publicacoes) {
			System.out.println("	Título: "  + publicacao.getTitulo());
			System.out.println("	  Ano de publicação: " + publicacao.getAnoPublicacao());
			System.out.println("	  Conferência: " + publicacao.getConferencia());
		}
	}
	
}
