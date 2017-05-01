package com.cad.motor2d;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public abstract class AbstractGamePanel extends JPanel implements ComponentListener {

	private boolean isRunning = true;
	private int fps = 60;
	
	private Timer timer;
	
	private long lastRefreshed = System.currentTimeMillis();

	public AbstractGamePanel() {
		super();
		this.addComponentListener(this);
	}

	public void  run() {
		initialize();
		isRunning = true;


		int delay = (1000 / fps); //milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				routine();      
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
	}

	public void routine(){
		long current = System.currentTimeMillis();
		update(current - lastRefreshed);
		lastRefreshed = current;
		repaint();
		timer.restart();
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
