package zadatak1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {

	private char oznaka;
	static final Font standardniFont = new Font("Serif", Font.BOLD, 14);

	public Parcela(char o, Color b) {
		oznaka = o;
		setText(o + "");
		setBackground(b);
		setForeground(Color.WHITE);
		setFont(standardniFont);
		setAlignment(CENTER);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component source = (Component) e.getSource();
				Plac mojPlac = (Plac) source.getParent();
				mojPlac.selektovanaParcela(Parcela.this);
			}
		});

	}

	public void promeniBojuPozadine(Color b) {
		setBackground(b);
	}

}
