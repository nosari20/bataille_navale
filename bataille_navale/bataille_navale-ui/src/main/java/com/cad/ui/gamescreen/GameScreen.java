package com.cad.ui.gamescreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;

import com.cad.motor2d.AbstractGamePanel;
import com.cad.motor2d.sprites.SpriteSheet;
import com.cad.ui.sprites_repository.ShipsXX1;
import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;





@SuppressWarnings("serial")
public class GameScreen extends AbstractGamePanel {

	private int ppux;
	private int ppuy;

	private int w_width;
	private int w_height;


	public GameScreen() {
		w_width = 10;
		w_height = 10;
	}

	@Override
	public void initialize() {
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
		


		g.drawImage(ShipsXX1.getInstance().getBateau1().getImage(), 0, 0 * ppuy, 1 * ppux, 1 * ppuy, null);
		
		
		g.drawImage(ShipsXX1.getInstance().getBateau3().getImage(), 0, 1 * ppuy, 3 * ppux, 1 * ppuy, null);
		
		g.drawImage(ShipsXX1.getInstance().getBateau4().getImage(), 0, 2 * ppuy, 4 * ppux, 1 * ppuy, null);
		
		g.drawImage(ShipsXX1.getInstance().getBateau5().getImage(), 0, 3 * ppuy, 5 * ppux, 1 * ppuy, null);
		


		

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
