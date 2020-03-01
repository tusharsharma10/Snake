package game;
import java.awt.Color;

import javax.swing.JFrame;

import game.Gameplay;

public class Main {

	public static void main(String[] args) {
		JFrame ob = new JFrame();
		
		
		Gameplay gamePlay = new Gameplay();
		
		ob.setBounds(10,10,900,700);
		ob.setBackground(Color.DARK_GRAY);
		ob.setResizable(false);
		ob.setVisible(true);
		ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ob.add(gamePlay);
	}

}
