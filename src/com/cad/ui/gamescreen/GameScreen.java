package com.cad.ui.gamescreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.security.KeyStore.Entry;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.motor2d.AbstractGamePanel;
import com.cad.motor2d.sprites.SpriteSheetAtlas;
import com.cad.ui.sprites_repository.ShipsXX1;
import com.cad.ui.sprites_repository.SpriteExplostionRepository;
import com.cad.ui.sprites_repository.SpriteLandscapeRepository;
import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;





@SuppressWarnings("serial")
public class GameScreen extends AbstractGamePanel {

	private int ppux;
	private int ppuy;

	private int w_width;
	private int w_height;


	private PartieBatailleNavale partie;


	public GameScreen(PartieBatailleNavale p) {
		partie = p;
		w_width = p.GAME_WIDTH*2;
		w_height = p.GAME_HEIGHT;
	}

	@Override
	public void initialize() {
		ppux = getWidth() / w_width;
		ppuy = getHeight() / w_height;
		Listener l = new Listener(this);
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		this.setBackground(Color.white);
	}

	@Override
	public void update(long time) {

	}

	@Override
	public void draw(Graphics g) {


		//Draw lanscape
		for(int i = 0; i < partie.GAME_WIDTH*2; i++)
			for(int j = 0; j < partie.GAME_HEIGHT; j++)
				g.drawImage(SpriteLandscapeRepository.getInstance().water().getImage(), i*ppux, j*ppuy, ppux, ppuy, null);


		// Draw J1
		g.setColor(Color.RED);
		for (int i = 0; i <= partie.GAME_WIDTH; i++) {
			g.drawLine(i * ppux, 0, i * ppux, partie.GAME_HEIGHT * ppuy);	
		}
		for (int i = 0; i <= partie.GAME_HEIGHT; i++) {
			g.drawLine(0, i * ppuy, partie.GAME_WIDTH * ppux, i * ppuy);
		}

		for (Bateau b : partie.getBateauJ1()){
			if(b.isOrientation()){
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), b.isOrientation()).getImage(), b.getPosx() * ppux, b.getPosy()*ppuy, ppux, b.getLongueur()*ppuy, null);

			}else{
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), b.isOrientation()).getImage(), b.getPosx() * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy, null);
			}
		}

		// Draw J2
		g.setColor(Color.BLUE);
		for (int i = partie.GAME_WIDTH; i <= partie.GAME_WIDTH*2; i++) {
			g.drawLine(i * ppux, 0, i * ppux, partie.GAME_HEIGHT * ppuy);
		}
		for (int i = 0; i <= partie.GAME_HEIGHT; i++) {
			g.drawLine(partie.GAME_WIDTH * ppux, i * ppuy, partie.GAME_WIDTH*2 * ppux, i * ppuy);
		}

		for (Bateau b : partie.getBateauJ2()){
			if(b.isOrientation()){
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), b.isOrientation()).getImage(), (2*partie.GAME_WIDTH - b.getPosx() - b.getLongueur()) * ppux, b.getPosy()*ppuy, ppux, b.getLongueur()*ppuy, null);

			}else{
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), b.isOrientation()).rotate(180).getImage(), (2*partie.GAME_WIDTH - b.getPosx() - b.getLongueur()) * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy, null);

			}
		}
		
		
		g.drawImage(SpriteExplostionRepository.getInstance().getExplosion().getImage(), 0 * ppux, 0*ppuy, ppux, ppuy, null);


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


	public PartieBatailleNavale getPartie(){
		return partie;
	}

	public int getPpux() {
		return ppux;
	}

	public void setPpux(int ppux) {
		this.ppux = ppux;
	}

	public int getPpuy() {
		return ppuy;
	}

	public void setPpuy(int ppuy) {
		this.ppuy = ppuy;
	}

	public int getW_width() {
		return w_width;
	}

	public void setW_width(int w_width) {
		this.w_width = w_width;
	}

	public int getW_height() {
		return w_height;
	}

	public void setW_height(int w_height) {
		this.w_height = w_height;
	}

	public int[] screen2Case(int x, int y){
		int rx = x / ppux;
		int ry = y / ppuy;		

		if(rx < partie.GAME_WIDTH){
			//j1
			int[] tab = new int[3];
			tab[0] = 1;
			tab[1] = rx;
			tab[2] = ry;
			return tab;
		}else{
			//j2
			int[] tab = new int[3];
			tab[0] = 1;
			tab[1] = partie.GAME_WIDTH-rx; // on inverse les coordonnées en x
			tab[2] = ry;
			return tab;
		}
	}

}
