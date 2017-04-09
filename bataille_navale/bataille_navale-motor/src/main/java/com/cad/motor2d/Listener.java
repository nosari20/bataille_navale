package com.cad.motor2d;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements MouseListener {

	private DemoGamePanel model;

	private int ppux;
	private int ppuy;

	public Listener(DemoGamePanel demoGamePanel, int x, int y) {
		// TODO Auto-generated constructor stub
		model = demoGamePanel;
		ppux = x;
		ppuy = y;

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		model.x++;

		if (model.gx == e.getX() / ppux && model.gy == e.getY() / ppuy) {
			model.g90 = !model.g90;
		}

		model.gx = e.getX() / ppux;
		model.gy = e.getY() / ppuy;

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
