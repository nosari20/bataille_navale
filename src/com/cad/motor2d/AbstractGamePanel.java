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
		/*
		while (isRunning) {
			long time = System.currentTimeMillis();
			update(time);
			repaint();
			System.out.println("repaint");
			//isRunning = false;
			time = (1000 / fps) - (System.currentTimeMillis() - time);
			if (time > 0) {
				try {
					Thread.sleep(1000);
					System.gc();
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		*/
	}

	public void routine(){
		update(1000);
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
