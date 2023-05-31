package zadatak1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Plac extends Panel {
	private int redovi, kolone;
	private Parcela[][] parcele;
	private Parcela selektovana;
	private static final Font selektovanaFont = new Font("Serif", Font.BOLD, 20);
	private int cellWidth;
	private int cellHeight;
	private ArrayList<Proizvodjac> listaProizvodjaca = new ArrayList<>();

	Plac(int r, int k) {
		redovi = r;
		kolone = k;
		parcele = new Parcela[r][k];
		GridLayout grid = new GridLayout(r, k, 5, 5);
		setLayout(grid);
		setBackground(Color.WHITE);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < k; j++) {
				Parcela parcela = (new Random().nextDouble() < 0.7 ? new Travnata() : new Vodena());
				add(parcela);
				parcele[i][j] = parcela;
			}
		}
	}

	void selektujParcelu(Parcela p) {
		if (p == null)
			return;
		if (selektovana != null) {
			selektovana.setFont(Parcela.standardniFont);
		}
		selektovana = p;
		selektovana.setFont(selektovanaFont);
	}

	public void dodajProizvodjaca(Baterija baterija) {
		if (selektovana == null)
			return;
		// ne mora da bude hidroelektrana
		Proizvodjac proizvodjac = new Hidroelektrana(baterija);

		loop0: for (int i = 0; i < redovi; i++) {
			for (int j = 0; j < kolone; j++) {
				if (parcele[i][j] != selektovana)
					continue;
				if (selektovana instanceof Proizvodjac) {
					listaProizvodjaca.remove(selektovana);
					((Proizvodjac) selektovana).zaustavi();
				}
				selektujParcelu(proizvodjac);
				this.remove(i * kolone + j);
				this.add(proizvodjac, i * kolone + j);
				parcele[i][j] = proizvodjac;
				listaProizvodjaca.add(proizvodjac);
				break loop0;
			}
		}
		for (int i = 0; i < redovi; i++) {
			for (int j = 0; j < kolone; j++) {
				if (parcele[i][j] instanceof Hidroelektrana) {
					((Hidroelektrana) parcele[i][j]).postaviBrojVodenihPovrsi(prebrojVodenePovrsi(i, j));
				}
			}
		}
		revalidate();
		repaint();
	}

	public void zaustaviSveProizvodjace() {
		for (Proizvodjac p : listaProizvodjaca) {
			p.zaustavi();
		}
	}

	private int prebrojVodenePovrsi(int red, int kol) {
		int rezultat = 0;
		for (int i = red - 1; i <= red + 1; i++) {
			for (int j = kol - 1; j <= kol + 1; j++) {
				if (i == red && j == kol)
					continue;
				if (i >= 0 && i < redovi && j >= 0 && j < kolone) {
					if (parcele[i][j] instanceof Vodena) {
						rezultat++;
					}
				}
			}
		}
		return rezultat;
	}
}
