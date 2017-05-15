package projeto;

import java.util.Scanner;

public class Doutorando extends Pesquisador{

	@Override
	public void addPesquisador(Scanner entrada) {
		super.addPesquisador(entrada);
	}

	@Override
	public boolean isProfessor() {
		return super.isProfessor();
	}

	@Override
	public boolean isMaxProjetos() {
		return super.isMaxProjetos();
	}

}
