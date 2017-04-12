package com.cad.motor2d;

import javax.swing.JFrame;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);

		final DemoGamePanel panel = new DemoGamePanel();
		JFrame f = new JFrame("Demo");
		f.setSize(300, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(panel);
		f.setVisible(true);
		
		
		

		panel.run();

	}

}
