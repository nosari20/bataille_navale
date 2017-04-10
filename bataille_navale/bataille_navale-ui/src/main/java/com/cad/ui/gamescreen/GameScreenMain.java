package com.cad.ui.gamescreen;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class GameScreenMain extends JFrame {
	// GameBoard properties (square dimension)
	private final int SIZE = 10;
	private final int FIELD_SIZE = 34; // = 32px because of the border
	private final String IMAGE_PATH = System.getProperty("user.dir") + "\\assets\\ships\\ship2.png";
	// contains all JButtons of the grid
	private final JButton gameFieldGUI[][] = new JButton[10][10];
	private GridComponent player1Side;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String currentDir = System.getProperty("user.dir") + "\\assets\\ships\\ship2.png";
		System.out.println("Current dir using System:" + currentDir);

		new GameScreenMain();
	}

	public GameScreenMain() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}
		player1Side = new GridComponent();

		this.setTitle("Battleship");
		this.setPreferredSize(new Dimension(1000, 700));
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2));
		this.add(player1Side);
		this.add(new GridComponent());
		this.pack();
		this.setVisible(true);
	}

}
