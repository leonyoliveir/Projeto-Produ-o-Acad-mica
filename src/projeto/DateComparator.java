package projeto;

import java.util.Comparator;

public class DateComparator implements Comparator<Projeto> {

	@Override
	public int compare(Projeto p1, Projeto p2) {
		return p1.getDataTermino().compareTo(p2.getDataTermino());
	}

}
