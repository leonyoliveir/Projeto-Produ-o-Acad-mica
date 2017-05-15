package projeto;

import java.util.Scanner;

public class Graduando extends Pesquisador{

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
		return (this.numeroProjetos() >= 2);
	}

	
}
