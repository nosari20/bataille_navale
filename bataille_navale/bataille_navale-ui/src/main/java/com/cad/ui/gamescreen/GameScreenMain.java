package com.cad.ui.gamescreen;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GameScreenMain extends JFrame {
	// GameBoard properties (square dimension)
	private final int SIZE = 10;
	private final int FIELD_SIZE = 34; // = 32px because of the border
	private final String IMAGE_PATH = System.getProperty("user.dir") + "\\assets\\ships\\ship2.png";
	private GridComponent player1Side, player2Side;
	private JPanel rootPanel;
	private JPanel gridPanel;
	private JPanel shipPanel;

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
		player2Side = new GridComponent();
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1, 2));
		gridPanel.add(player1Side);
		gridPanel.add(player2Side);
		
		this.setTitle("Battleship");
		this.setPreferredSize(new Dimension(1000, 700));
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2));
		// this.add(shipPanel);
		this.add(gridPanel);
		this.pack();
		this.setVisible(true);
	}

}
