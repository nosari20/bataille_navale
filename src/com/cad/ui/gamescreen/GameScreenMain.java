package com.cad.ui.gamescreen;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.motor2d.demo.DemoGamePanel;

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

String currentDir = System.getProperty("user.dir") + "\\bataille_navale-motor";
		

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}
        System.out.println("Current dir using System:" +currentDir);

        
        PartieBatailleNavale p = (PartieBatailleNavale) new ModernePartieBatailleNavaleFactory().CreatePartie();
        
		GameScreen panel = new GameScreen(p);
		JFrame f = new JFrame("Demo");
		f.setSize(800, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(panel);
		f.setVisible(true);
		
		
		panel.run();
	}

	public GameScreenMain() {
		
		

		
	}

}
