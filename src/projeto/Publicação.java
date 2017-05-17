package projeto;

import java.util.ArrayList;

public class Publicação {

	private String titulo;														// Armazena o título da publicação
	private String conferencia;													// Armazena a conferência onde ocorreu a publicação
	private int anoPublicacao;													// Armazena o ano de publicação
	private String projetoAssociado;											// Armazena o projeto ao qual a publicação foi associada
	private ArrayList<Pesquisador> autores = new ArrayList<Pesquisador>();		// Armazena os autores da publicação
	private Professor orientador;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getConferencia() {
		return conferencia;
	}
	
	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}
	
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public String getProjetoAssociado() {
		return projetoAssociado;
	}
	
	public void setProjetoAssociado(String projetoAssociado) {
		this.projetoAssociado = projetoAssociado;
	}
	
	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}
	
	public Professor getOrientador() {
		return orientador;
	}
	
	public void addAutor(Pesquisador autor) {
		this.autores.add(autor);
	}
	
	public boolean isAutorAdicionado(Pesquisador pesquisador) {
		return this.autores.contains(pesquisador);
	}
}
