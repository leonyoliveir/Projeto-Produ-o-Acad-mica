package projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Professor extends Pesquisador {

	private ArrayList<String> orientacoes = new ArrayList<>();

	@Override
	public void addPesquisador(Scanner entrada) {
		super.addPesquisador(entrada);
	}

	@Override
	public boolean isProfessor() {
		return true;
	}
	
	public void addOrientacao (String orientacao) {
		this.orientacoes.add(orientacao);
	}

	@Override
	public boolean isMaxProjetos() {
		return super.isMaxProjetos();
	}

	@Override
	public void colaborador() {
		super.colaborador();
		System.out.println("Orientações do professor: ");
		for(String orientacao: orientacoes) {
			System.out.println("	" + orientacao);
		}
	}
	
	
}
