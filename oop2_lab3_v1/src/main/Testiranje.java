package main;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Button;

public class Testiranje {
    public static void main(String[] args) {
        Frame frame = new Frame("GridLayout Example");
        frame.setSize(500,500);

        // Create GridLayout with 3 rows and 2 columns
        GridLayout gridLayout = new GridLayout(3, 2);
        frame.setLayout(gridLayout);

        // Add buttons to the container
        frame.add(new Button("Button 1"));
        frame.add(new Button("Button 2"));
        frame.add(new Button("Button 3"));
        frame.add(new Button("Button 4"));
        frame.add(new Button("Button 5"));

        frame.pack();
        frame.setVisible(true);
    }
}