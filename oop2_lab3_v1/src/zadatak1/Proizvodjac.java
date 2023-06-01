package zadatak1;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable {

	private Baterija baterija;
	private int osnovnoVreme;
	private int ukupnoVreme;
	private boolean aktivan;
	private Thread mojaNit;

	public Proizvodjac(char o, Color b, int v, Baterija bat) {
		super(o, b);
		osnovnoVreme = v;
		baterija = bat;
		ukupnoVreme = v + new Random().nextInt(301);
		mojaNit = new Thread(this);
		aktivan = true;
		mojaNit.start();
	}

	public abstract boolean uspesnaProizvodnja();

	public abstract int brojJedinicaEnergije();

	public void zaustavi() {
		aktivan = false;
		mojaNit.interrupt();
	}

	@Override
	public void run() {
		while (aktivan) {
			try {
				Thread.sleep(ukupnoVreme);
				if (mojaNit.interrupted())
					continue;
				Color old = getForeground();
				if (uspesnaProizvodnja()) {
					baterija.dodajEnergiju(brojJedinicaEnergije());
					setForeground(Color.RED);
					// System.out.println("Generisano " + brojJedinicaEnergije() + " energije");
				}
				Thread.sleep(300);
				if (mojaNit.interrupted())
					continue;
				setForeground(old);

			} catch (InterruptedException e) {
			}
		}
		// System.out.println("Prekinuta nit");
	}
}
