package projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Pesquisador {

	private String nome;					// Armazena o nome do participante
	private String email;					// Armazena o email do participante
	private ArrayList<Projeto> projetos = new ArrayList<Projeto>();	// Armazena os projetos que o participante faz parte
	
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
	
}
