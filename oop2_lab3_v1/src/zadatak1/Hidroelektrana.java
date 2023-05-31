package zadatak1;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {

	private int brojVodenihPovrsi = 0;

	public Hidroelektrana(Baterija bat) {
		super('H', Color.BLUE, 1500, bat);
	}

	public void postaviBrojVodenihPovrsi(int broj) {
		brojVodenihPovrsi = broj;
	}

	@Override
	public boolean uspesnaProizvodnja() {
		return brojVodenihPovrsi > 0;
	}

	@Override
	public int brojJedinicaEnergije() {
		return brojVodenihPovrsi;
	}

}
