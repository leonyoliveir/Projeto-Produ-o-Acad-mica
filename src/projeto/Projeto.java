package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Projeto {

	private String titulo;															// Armazena o t�tulo do projeto
	private String dataInicio;														// Armazenam a data de in�cio do projeto
	private String dataTermino;						 								// Armazenam a data de t�rmino do projeto
	private String agenciaFinanciadora;												// Armazena o nome da ag�ncia financiadora do projeto
	private double valorFinanciado;													// Armazena o valor que foi financiado no projeto
	private String objetivo;														// Armazena o objetivo do projeto
	private String descricao;														// Armazena a descri��o do projeto
	private ArrayList<Pesquisador> participantes = new ArrayList<Pesquisador>();	// Armazena os participantes do projeto
	private String status;															// Armazena o status atual do projeto
	private ArrayList <Publica��o> publicacoes = new ArrayList<Publica��o>();		// Armazena as publica��es feitas
	
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
	
	public void setDataInicio(String data) {
		this.dataInicio = data;
	}
	
	public void setDataTermino(String data) {
		this.dataTermino = data;
	}
	
	public String getDataInicio() {
		return this.dataInicio;
	}
	
	public String getDataTermino() {
		return this.dataTermino;
	}
	
	public void addPesquisador(Pesquisador pesquisador){
		this.participantes.add(pesquisador);
	}
	
	public boolean isPesquisadorAdicionado (Pesquisador pesquisador) {
		return this.participantes.contains(pesquisador);
	}
	
	public void addProjeto(Scanner entrada, ArrayList<Pesquisador> pesquisadores) {
		Calendar calendario = Calendar.getInstance();
		Date dataAtual = calendario.getTime();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("\nDigite o t�tulo do projeto:");
		this.setTitulo(entrada.nextLine());
		System.out.println("\nDigite o nome da ag�ncia financiadora:");
		this.setAgenciaFinanciadora(entrada.nextLine());
		System.out.println("\nQual o objetivo do projeto?");
		this.setObjetivo(entrada.nextLine());
		System.out.println("\nDescreva o projeto de pesquisa:");
		this.setDescricao(entrada.nextLine());
		this.setDataInicio(formato.format(dataAtual));
		boolean valido = false;
		do{
			System.out.println("\nEscolha um professor para orientar o projeto:\n");
			Main.professores(pesquisadores);
			int escolha = entrada.nextInt();
			if (pesquisadores.get(escolha).isProfessor() == true){
				this.addPesquisador(pesquisadores.get(escolha));
				this.setStatus("Em Elabora��o");
				System.out.println("\nProjeto cadastrado com sucesso!");
				valido = true;
			} 
			else System.out.println("\nEste pesquisador n�o � um professor!");
		} while(valido == false);
	}
	
	public void trocarStatus(Scanner entrada) {
		System.out.println("\nO status atual do projeto � " + this.getStatus() + ". Deseja alterar?\n1 - Sim\n2 - N�o");
		int escolha = entrada.nextInt();
		if(escolha == 1){
			if (this.getStatus().equals("Em Elabora��o")) {
				if (this.participantes.isEmpty()) System.out.println("\nProjeto n�o possui participantes ainda!");
				else {
					this.setStatus("Em Andamento");
					System.out.println("\nStatus alterado de 'Em Elabora��o' para 'Em Andamento'");
				}
			} else if (this.getStatus().equals("Em Andamento")){
				if(this.publicacoes.isEmpty()) System.out.println("\nO status desse projeto n�o pode ser alterado para conclu�do pois n�o possui publica��es associadas!");
				else {
					Calendar calendario = Calendar.getInstance();
					Date dataAtual = calendario.getTime();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					this.setStatus("Conclu�do");
					this.setDataTermino(formato.format(dataAtual));
					System.out.println("\nStatus alterado de 'Em Andamento' para 'Conclu�do'!");
				}
			} 
			else System.out.println("\nProjeto j� foi conclu�do!");
		}
	}
	
	public void incluirPublicacoes(Scanner entrada) {
		Publica��o publicacao = new Publica��o();
		publicacao.setProjetoAssociado(this.getTitulo());
		System.out.println("\nDigite o t�tulo da publica��o:");
		publicacao.setTitulo(entrada.nextLine());
		System.out.println("\nDigite o nome da confer�ncia onde foi divulgada a publica��o:");
		publicacao.setConferencia(entrada.nextLine());
		System.out.println("\nDigite o ano de publica��o:");
		publicacao.setAnoPublicacao(entrada.nextInt());
		System.out.println("\nQual o professor orientador da publica��o?");
		Main.professores(this.participantes);
		int escolha = entrada.nextInt();
		boolean valido = false;
		while (!valido) {
			if(this.participantes.get(escolha).isProfessor()) {
				publicacao.addAutor(this.participantes.get(escolha));
				valido = true;
			} 
			else System.out.println("\nColaborador n�o � professor!");
		}
		boolean finalizar = false;
		while (!finalizar) {
			System.out.println("\nAdicione o autor da publica��o:");
			Main.participantes(this.participantes);
			escolha = entrada.nextInt();
			if(!this.participantes.get(escolha).isProfessor()){
				publicacao.addAutor(this.participantes.get(escolha));
				System.out.println("\nAutor adicionado!");
			}
			System.out.println("Deseja adicionar outro autor?\n1 - Sim\n2 - N�o");
			escolha = entrada.nextInt();
			if(escolha == 2) finalizar = true;
		}
		this.publicacoes.add(publicacao);
		System.out.println("Publica��o adicionada com sucesso!");
	}
	
}
