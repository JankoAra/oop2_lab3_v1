package zadatak1;

public class Baterija {

	private int trenutnaEnergija;
	private int kapacitet;

	public Baterija(int kap) {
		kapacitet = kap;
		trenutnaEnergija = kap;
	}

	public synchronized void dodajEnergiju(int e) {
		if (trenutnaEnergija + e >= kapacitet) {
			trenutnaEnergija = kapacitet;
		} else {
			trenutnaEnergija += e;
		}
	}

	public synchronized void isprazniBateriju() {
		trenutnaEnergija = 0;
	}

	public synchronized boolean jePuna() {
		return trenutnaEnergija == kapacitet;
	}

}
