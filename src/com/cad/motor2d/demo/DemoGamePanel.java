package com.cad.motor2d.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;

import com.cad.motor2d.AbstractGamePanel;
import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheet;



@SuppressWarnings("serial")
public class DemoGamePanel extends AbstractGamePanel {

	private SpriteSheet p = new SpriteSheet("./assets/pacman.png", "./assets/pacman.atlas");

	private Sprite[] sprites = { p.getSprite("pacmanRight"), p.getSprite("pacmanRight-2") };
	private Animation animation = new Animation(sprites, 5);

	public Sprite go = p.getSprite("ghost1");
	public Sprite go90 = p.getSprite("ghost1").rotate(-90);
	public int gx = 0;
	public int gy = 0;
	public boolean g90 = false;

	private int ppux;
	private int ppuy;

	private int w_width;
	private int w_height;

	public int x = 0;

	public DemoGamePanel() {
		w_width = 10;
		w_height = 10;
	}

	@Override
	public void initialize() {
		animation.start();
		ppux = getWidth() / w_width;
		ppuy = getHeight() / w_height;
		this.addMouseListener(new Listener(this, ppux, ppuy));
		this.setBackground(Color.white);
	}

	@Override
	public void update(long time) {

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i <= 10; i++) {
			g.drawLine(i * ppux, 0, i * ppux, w_height * ppuy);
			g.drawLine(0, i * ppuy, w_width * ppux, i * ppuy);
		}

		g.drawImage(animation.getSprite().getImage(), 0, 0, 1 * ppux, 1 * ppuy, null);
		g.drawString("x = " + x, 0, w_height * ppuy);

		if (g90) {
			g.drawImage(go.getImage(), gx * ppux, gy * ppuy, 1 * ppux, 3 * ppuy, null);
		} else {
			g.drawImage(go90.getImage(), gx * ppux, gy * ppuy, 3 * ppux, 1 * ppuy, null);
		}

	}

	@Override
	public void resized(ComponentEvent e) {
		ppux = getWidth() / w_width;
		ppuy = getHeight() / w_height;
	}

	@Override
	public void hidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
