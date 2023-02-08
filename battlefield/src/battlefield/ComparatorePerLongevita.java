package battlefield;

import java.util.Comparator;

public class ComparatorePerLongevita implements Comparator<Robot> {

	@Override
	public int compare(Robot r1, Robot r2) {
		return r1.getLongevita()-r2.getLongevita();
	}
}
