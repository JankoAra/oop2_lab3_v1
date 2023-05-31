package zadatak1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	private int redovi, kolone;
	private int kapacitetBaterije;
	private Baterija baterija;
	private Plac plac;
	private Button dugmeDodajProizvodjaca = new Button("Dodaj");

	public void populateWindow() {
		Panel buttonPanel = new Panel();
		buttonPanel.add(dugmeDodajProizvodjaca);
		add(buttonPanel, BorderLayout.NORTH);
		add(plac, BorderLayout.CENTER);

	}

	public EnergetskiSistem(int r, int k, int kap) {
		redovi = r;
		kolone = k;
		kapacitetBaterije = kap;
		baterija = new Baterija(kapacitetBaterije);
		plac = new Plac(r, k);
		dugmeDodajProizvodjaca.addActionListener(ae -> plac.dodajProizvodjaca(baterija));

		setSize(new Dimension(500, 500));
		setResizable(false);
		setTitle("Energetski sistem");

		populateWindow();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustaviSveProizvodjace();
				dispose();
			}

		});

		setVisible(true);
	}

}
