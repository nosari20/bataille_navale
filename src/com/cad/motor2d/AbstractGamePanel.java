package com.cad.motor2d;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractGamePanel extends JPanel implements Runnable, ComponentListener {

	private boolean isRunning = true;
	private int fps = 30;

	public AbstractGamePanel() {
		super();
		this.addComponentListener(this);
	}

	public void run() {
		initialize();
		isRunning = true;
		while (isRunning) {
			long time = System.currentTimeMillis();
			update(time);
			repaint();
			time = (1000 / fps) - (System.currentTimeMillis() - time);
			if (time > 0) {
				try {
					Thread.sleep(time);
					System.gc();
				} catch (Exception e) {
				}
			}
		}
	}

	public void stop() {
		isRunning = false;
	}

	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}

	public void componentResized(ComponentEvent e) {
		resized(e);
	}

	public void componentHidden(ComponentEvent e) {
		hidden(e);

	}

	public void componentMoved(ComponentEvent e) {
		moved(e);

	}

	public void componentShown(ComponentEvent e) {
		shown(e);

	}

	public abstract void initialize();

	public abstract void update(long time);

	public abstract void draw(Graphics g);

	public abstract void resized(ComponentEvent e);

	public abstract void hidden(ComponentEvent e);

	public abstract void moved(ComponentEvent e);

	public abstract void shown(ComponentEvent e);

}
