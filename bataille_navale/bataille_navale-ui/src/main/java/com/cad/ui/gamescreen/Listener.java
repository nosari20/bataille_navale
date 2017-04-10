package com.cad.ui.gamescreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements MouseListener {

	private GameScreen model;

	private int ppux;
	private int ppuy;

	public Listener(GameScreen demoGamePanel, int x, int y) {
		// TODO Auto-generated constructor stub
		model = demoGamePanel;
		ppux = x;
		ppuy = y;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
