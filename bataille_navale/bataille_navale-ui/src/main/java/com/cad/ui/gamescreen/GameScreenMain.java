package com.cad.ui.gamescreen;

import javax.swing.JFrame;

public class GameScreenMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);

		final GameScreen panel = new GameScreen();
		JFrame f = new JFrame("Demo");
		f.setSize(300, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(panel);
		f.setVisible(true);
		
		
		

		panel.run();

	}

}
