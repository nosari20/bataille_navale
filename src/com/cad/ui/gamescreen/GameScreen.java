package com.cad.ui.gamescreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.security.KeyStore.Entry;
import java.util.ArrayList;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.bateau.BateauOrientation;
import com.cad.jeu_abstrait.Jeu;
import com.cad.motor2d.AbstractGamePanel;
import com.cad.motor2d.sprites.SpriteSheetAtlas;
import com.cad.ui.sprites_repository.ShipsXX1;
import com.cad.ui.sprites_repository.SpriteExplostionRepository;
import com.cad.ui.sprites_repository.SpriteFontRepository;
import com.cad.ui.sprites_repository.SpriteLandscapeRepository;
import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;





@SuppressWarnings("serial")
public class GameScreen extends AbstractGamePanel {

	private int ppux;
	private int ppuy;

	private int w_width;
	private int w_height;



	private BatailleNavale jeu;
	private PartieBatailleNavale partie;

	private PlacementListener placementListener;

	private TireListener tireListener;

	private Point selected;

	char[] text;


	public GameScreen(BatailleNavale j) {
		jeu =  j;
		w_width = jeu.WIDTH*2;
		w_height = jeu.HEIGHT;
		partie = (PartieBatailleNavale) j.currentPartie();
	}

	@Override
	public void initialize() {
		ppux = getWidth() / w_width;
		ppuy = getHeight() / w_height;

		placementListener = new PlacementListener(this);
		tireListener = new TireListener(this, jeu);
		

		write("PLACER LES BATEAUX");

		this.setBackground(Color.white);
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                clear();
		                addPlacementListener();
		                
		            }
		        }, 
		        1500 
		);

	}

	@Override
	public void update(long time) {

	}

	@Override
	public void draw(Graphics g) {
		//Draw lanscape
		for(int i = 0; i < jeu.WIDTH*2; i++)
			for(int j = 0; j < jeu.HEIGHT; j++)
				g.drawImage(SpriteLandscapeRepository.getInstance().water().getImage(), i*ppux, j*ppuy, ppux, ppuy, null);



		// Draw grid
		g.setColor(Color.GRAY);
		for (int i = 0; i <= jeu.WIDTH; i++) {
			g.drawLine(i * ppux, 0, i * ppux, jeu.HEIGHT * ppuy);	
		}
		for (int i = 0; i <= jeu.HEIGHT; i++) {
			g.drawLine(0, i * ppuy, jeu.WIDTH * ppux, i * ppuy);
		}
		for (int i = jeu.WIDTH; i <= jeu.WIDTH*2; i++) {
			g.drawLine(i * ppux, 0, i * ppux, jeu.HEIGHT * ppuy);
		}
		for (int i = 0; i <= jeu.HEIGHT; i++) {
			g.drawLine(jeu.WIDTH * ppux, i * ppuy, jeu.WIDTH*2 * ppux, i * ppuy);
		}
		g.setColor(Color.RED);
		g.drawLine(jeu.WIDTH * ppux, 0, jeu.WIDTH * ppux, jeu.HEIGHT * ppuy);




		// Draw j1
		for (Bateau b : partie.getBateauJ1()){
			drawBateau(g, b, BatailleNavalleJoueurCote.GAUCHE);
		}
		//Draw j2
		for (Bateau b : partie.getBateauJ2()){
			//if(b.isDestroyed())
			drawBateau(g, b, BatailleNavalleJoueurCote.DROIT);
		}

		int[][] grille =  partie.getGrille(BatailleNavalleJoueurCote.DROIT);		
		for(int i = 0; i < grille.length; i++ ){
			for(int j = 0; j < grille[0].length; j++ ){

				if (grille[i][j] == BatailleNavale.Code.TOUCHE_VIDE) {
					g.setColor(new Color(0,0,0,120));
					g.fillRect((2*jeu.WIDTH - i -1)*ppux, j*ppuy, ppux, ppuy);
				} else if (grille[i][j] == BatailleNavale.Code.TOUCHE) {
					g.setColor(new Color(255,0,0,120));
					g.fillRect((2*jeu.WIDTH - i -1)*ppux, j*ppuy, ppux, ppuy);
				} else if (grille[i][j] == BatailleNavale.Code.CASE_DETRUITE) {
					g.setColor(new Color(0,255,0,120));
					g.fillRect((2*jeu.WIDTH - i -1)*ppux, j*ppuy, ppux, ppuy);
				}

			}
		}

		grille =  partie.getGrille(BatailleNavalleJoueurCote.GAUCHE);		
		for(int i = 0; i < grille.length; i++ ){
			for(int j = 0; j < grille[0].length; j++ ){

				if (grille[i][j] == BatailleNavale.Code.TOUCHE_VIDE) {
					g.setColor(new Color(0,0,0,120));
					g.fillRect(i*ppux, j*ppuy, ppux, ppuy);
				} else if (grille[i][j] == BatailleNavale.Code.TOUCHE) {
					g.setColor(new Color(255,0,0,120));
					g.fillRect(i*ppux, j*ppuy, ppux, ppuy);
				} else if (grille[i][j] == BatailleNavale.Code.CASE_DETRUITE) {
					g.setColor(new Color(0,255,0,120));
					g.fillRect(i*ppux, j*ppuy, ppux, ppuy);
				}

			}
		}


		//g.drawImage(SpriteExplostionRepository.getInstance().getExplosion().getImage(), 0 * ppux, 0*ppuy, ppux, ppuy, null);

		if(selected != null){
			g.drawImage(SpriteLandscapeRepository.getInstance().crosshair().getImage(), (int)selected.getX()*ppux, (int)selected.getY()*ppuy, ppux, ppuy, null);
		}


		if(text!=null){
			drawText(g);
		}
	}

	public void drawBateau(Graphics g, Bateau b, BatailleNavalleJoueurCote j){

		if(j == BatailleNavalleJoueurCote.GAUCHE){

			if(b.getOrientation() == BateauOrientation.HORIZONTAL){
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), false).getImage(), b.getPosx() * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy, null);


			}else{
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), true).getImage(), b.getPosx() * ppux, b.getPosy()*ppuy, ppux, b.getLongueur()*ppuy, null);				
			}

		}else if(j == BatailleNavalleJoueurCote.DROIT){

			if(b.getOrientation() == BateauOrientation.HORIZONTAL){
				//g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), true).getImage(), (2*jeu.WIDTH - b.getPosx()) * ppux, (b.getPosy() - b.getLongueur())*ppuy, ppux, b.getLongueur()*ppuy, null);
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), false).flipH().getImage(), (2*jeu.WIDTH - b.getPosx() - b.getLongueur()) * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy, null);

			}else{
				g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), true).flipH().getImage(), ((2*jeu.WIDTH - b.getPosx()-1)) * ppux, (b.getPosy())*ppuy, ppux, b.getLongueur()*ppuy, null);

			}
		}
	}

	public void drawText(Graphics g){

		double size = 1.5;
		double xpos = (w_width/(double)2) - (text.length/(double)2);
		double ypos = (w_height/(double)2) - size;
		
		for (int i = 0; i < text.length; i++) {
			Sprite s = SpriteFontRepository.getInstance().get(text[i]);
			if(s!=null){
				g.drawImage(s.getImage(),(int)((xpos+i)*ppux),(int)((ypos)*ppux),(int)(size*ppux),(int)(size*ppuy), null);
			}
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


	public PartieBatailleNavale getPartie(){
		return partie;
	}

	public BatailleNavale getJeu(){
		return jeu;
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

		if(rx < jeu.WIDTH){
			//j1
			int[] tab = new int[3];
			tab[0] = 1;
			tab[1] = rx;
			tab[2] = ry;
			return tab;
		}else{
			//j2
			int[] tab = new int[3];
			tab[0] = 2;
			tab[1] = jeu.WIDTH - (rx-jeu.WIDTH)-1; // on inverse les coordonnées en x
			tab[2] = ry;
			return tab;
		}
	}

	public void addPlacementListener(){
		this.addMouseListener(placementListener);
		this.addMouseMotionListener(placementListener);
	}

	public void removePlacementListener(){
		this.removeMouseListener(placementListener);
		this.removeMouseMotionListener(placementListener);
	}

	public void addTireListener(){
		this.addMouseListener(tireListener);
		this.addMouseMotionListener(tireListener);
	}

	public void removeTireListener(){
		this.removeMouseListener(tireListener);
		this.removeMouseMotionListener(tireListener);
	}

	public void select(Point p, int j){
		if(j == 1){
			selected = p;
		}else{
			selected = new Point((int)(jeu.WIDTH - (p.getX()-jeu.WIDTH)-1), (int)p.getY());
		}
	}

	public void deselect(){
		selected = null;
	}


	@Override
	public void stop(){	
		super.stop();
		removeTireListener();
		deselect();
		write("FIN");
		repaint();

	}

	public void write(String s){

		text = s.toCharArray();
	}
	
	public void write(String s, int ms){
		write(s);
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                clear();		                
		            }
		        }, 
		        ms 
		);
	}

	public void clear(){
		text = null;
	}



}
