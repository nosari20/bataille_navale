package com.cad.ui.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.bataille_navale.mode.ModeTireBateau;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.bateau.BateauOrientation;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Jeu;
import com.cad.motor2d.AbstractGamePanel;
import com.cad.motor2d.sprites.SpriteSheetAtlas;
import com.cad.ui.gamescreeen.listeners.FrappeOrbitaleListener;
import com.cad.ui.gamescreeen.listeners.TireBateauListener;
import com.cad.ui.gamescreeen.listeners.UIListener;
import com.cad.ui.sprites_repository.ShipsXIX;
import com.cad.ui.sprites_repository.ShipsXX1;
import com.cad.ui.sprites_repository.ShipsXXI;
import com.cad.ui.sprites_repository.SpriteBateauRepository;
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
	
	private SpriteBateauRepository bateauRepo;
	private PlacementListener placementListener;

	private UIListener tireListener;

	private Point target;
	
	private Point select;

	private char[] text;
	
	private List<Pair<Point,Animation>> explosions;
	private Animation flames;
	private Animation water;
	long fpr = 0;
	long wpr = 0;

	

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
		
		if(jeu.getMode() instanceof ModeNormal){
			tireListener = new FrappeOrbitaleListener(this, jeu);
		}else if(jeu.getMode() instanceof ModeTireBateau){
			tireListener = new TireBateauListener(this, jeu);
		}
		
		
		explosions = new ArrayList<>();

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
		
		
		flames = SpriteExplostionRepository.getInstance().getFlames();
		flames.loop(true);
		flames.start();
		
		water = SpriteLandscapeRepository.getInstance().waterAnimated();
		water.loop(true);
		water.start();
		
		setGraphiqueEpoque(partie.getEpoque());

	}

	@Override
	public void update(long time) {
		if(fpr > 500){
			flames.update();
			
			fpr = 0;
		}else{
			fpr+=time;
		}
		
		if(wpr > 100){
			water.update();
			
			wpr = 0;
		}else{
			wpr+=time;
		}
		
		
	}

	@Override
	public void draw(Graphics g) {
		//Draw lanscape
		
		for(int i = 0; i < jeu.WIDTH*2; i++)
			for(int j = 0; j < jeu.HEIGHT; j++)
				g.drawImage(water.getSprite().getImage(), i*ppux, j*ppuy, ppux, ppuy, null);
		
		g.setColor(new Color(255, 255, 255, 80));
		//g.setColor(Color.WHITE);
		g.fillRect(0, 0, jeu.WIDTH*2*ppux, jeu.HEIGHT*ppuy);
		/*
		g.drawImage(water.getSprite().getImage(), 0, 0, jeu.WIDTH*ppux, jeu.HEIGHT*ppuy, null);
		g.drawImage(water.getSprite().getImage(), jeu.WIDTH*ppux, 0, jeu.WIDTH*ppux, jeu.HEIGHT*ppuy, null);

		*/

		// Draw grid
		/*
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
		*/
		g.setColor(Color.BLACK);
		g.drawLine(jeu.WIDTH * ppux, 0, jeu.WIDTH * ppux, jeu.HEIGHT * ppuy);

		


		// Draw j1
		for (Bateau b : partie.getBateauJ1()){
			drawBateau(g, b, BatailleNavalleJoueurCote.GAUCHE);
			if(b.isDestroyed()){
				if(b.getOrientation() == BateauOrientation.HORIZONTAL){

					for(int i = 0; i < b.getLongueur(); i++){
						g.drawImage(flames.getSprite().getImage(), (b.getPosx()+i) * ppux, b.getPosy()*ppuy, ppux, ppuy, null);
					}
				}else{
					for(int i = 0; i < b.getLongueur(); i++){
						g.drawImage(flames.getSprite().getImage(), b.getPosx() * ppux, (b.getPosy()+i)*ppuy, ppux, ppuy, null);
					}
				}
					
			}
		}
		//Draw j2
		for (Bateau b : partie.getBateauJ2()){
			if(b.isDestroyed())
			drawBateau(g, b, BatailleNavalleJoueurCote.DROIT);
			if(b.isDestroyed()){
				if(b.getOrientation() == BateauOrientation.HORIZONTAL){

					for(int i = 0; i < b.getLongueur(); i++){
						g.drawImage(flames.getSprite().getImage(), (2*jeu.WIDTH - b.getPosx() - b.getLongueur()+i) * ppux, b.getPosy()*ppuy, ppux, ppuy, null);
					}
				}else{
					for(int i = 0; i < b.getLongueur(); i++){
						g.drawImage(flames.getSprite().getImage(), ((2*jeu.WIDTH - b.getPosx()-1)) * ppux, (b.getPosy()+i)*ppuy, ppux, ppuy, null);
					}
				}
					
			}
		}
		

		
		int[][] grille1 =  partie.getGrille(BatailleNavalleJoueurCote.GAUCHE);		
		for(int i = 0; i < grille1.length; i++ ){
			for(int j = 0; j < grille1[0].length; j++ ){

				if (grille1[i][j] == BatailleNavale.Code.TOUCHE_VIDE) {
					g.setColor(new Color(255,255,255,120));
					g.fillOval(i*ppux, j*ppuy, ppux, ppuy);
				} else if (grille1[i][j] == BatailleNavale.Code.TOUCHE) {
					g.setColor(new Color(255,0,0,120));
					g.fillOval(i*ppux, j*ppuy, ppux, ppuy);
				} else if (grille1[i][j] == BatailleNavale.Code.CASE_DETRUITE) {
					g.setColor(new Color(0,0,0,120));
					g.fillOval(i*ppux, j*ppuy, ppux, ppuy);
				}

			}
		}

		int[][] grille2 =  partie.getGrille(BatailleNavalleJoueurCote.DROIT);
		for(int i = 0; i < grille2.length; i++ ){
			for(int j = 0; j < grille2[0].length; j++ ){

				if (grille2[i][j] == BatailleNavale.Code.TOUCHE_VIDE) {
					g.setColor(new Color(255,255,255,120));
					g.fillOval((2*jeu.WIDTH - i -1)*ppux, j*ppuy, ppux, ppuy);
				} else if (grille2[i][j] == BatailleNavale.Code.TOUCHE) {
					g.setColor(new Color(255,0,0,120));
					g.fillOval((2*jeu.WIDTH - i -1)*ppux, j*ppuy, ppux, ppuy);
				} else if (grille2[i][j] == BatailleNavale.Code.CASE_DETRUITE) {
					g.setColor(new Color(0,0,0,120));
					g.fillOval((2*jeu.WIDTH - i -1)*ppux, j*ppuy, ppux, ppuy);
				}

			}
		}





		//g.drawImage(SpriteExplostionRepository.getInstance().getExplosion().getImage(), 0 * ppux, 0*ppuy, ppux, ppuy, null);

		if(target != null){
			g.drawImage(SpriteLandscapeRepository.getInstance().crosshair().getImage(), (int)target.getX()*ppux, (int)target.getY()*ppuy, ppux, ppuy, null);
		}
		/*
		if(select != null){
			g.drawImage(SpriteLandscapeRepository.getInstance().crosshair().getImage(), (int)select.getX()*ppux, (int)select.getY()*ppuy, ppux, ppuy, null);
		}
		*/


		if(text!=null){
			drawText(g);
		}
		
		
		List<Integer> delete= new ArrayList<Integer>();
		for (Iterator iterator = explosions.iterator(); iterator.hasNext();) {
			Pair a = (Pair) iterator.next();
			
			Point p = (Point) a.first;
			Animation an = (Animation) a.second;
			int x = p.x*ppux;
			int y = p.y*ppuy;
			g.drawImage(an.getSprite().getImage(),x,y,ppux, ppuy, null);
			an.update();
			
			if(an.isFinished()){
				delete.add(explosions.indexOf(a));
			}
		}
		
		for (Iterator iterator = delete.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			explosions.remove(integer);
		}
	}

	public void drawBateau(Graphics g, Bateau b, BatailleNavalleJoueurCote j){

		if(j == BatailleNavalleJoueurCote.GAUCHE){

			if(b.getOrientation() == BateauOrientation.HORIZONTAL){

				g.drawImage(bateauRepo.getBateau(b.getLongueur(), false).getImage(), b.getPosx() * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy, null);
				if(select!=null)
				if(b.contientCoord(new Coord(select.x, select.y)) != -1){
					g.setColor(new Color(0,0,255,120));
					int r = b.getPortee();
					g.fillOval((int) (((double)b.getPosx() + (double)(b.getLongueur()/2) - r/2)*ppux), (b.getPosy() - r/2)*ppuy, r*ppux, r*ppuy);
					//g.fillRect( b.getPosx() * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy);
					g.setColor(Color.WHITE);
					Font font = g.getFont().deriveFont( 30f );
				    g.setFont( font );
					g.drawString(""+b.getNbProjectile(), (int) (((double)b.getPosx() + (double)(b.getLongueur()/2))*ppux), (b.getPosy()+1)*ppuy);
				}

			}else{
				g.drawImage(bateauRepo.getBateau(b.getLongueur(), true).getImage(), b.getPosx() * ppux, b.getPosy()*ppuy, ppux, b.getLongueur()*ppuy, null);		
				if(select!=null)
				if(b.contientCoord(new Coord(select.x, select.y)) != -1){
					g.setColor(new Color(0,0,255,120));
					int r = b.getPortee();
					g.fillOval((int) ((b.getPosx() - r/2)*ppux), (int) (((double)b.getPosy() + (double)(b.getLongueur()/2) - r/2)*ppuy),r*ppux, r*ppuy);
					//g.fillRect(  b.getPosx() * ppux, b.getPosy()*ppuy, ppux, b.getLongueur()*ppuy);
					g.setColor(Color.WHITE);
					Font font = g.getFont().deriveFont( 30f );
				    g.setFont( font );
					g.drawString(""+b.getNbProjectile(), (int) ((b.getPosx())*ppux), (int) (((double)b.getPosy() + (double)(b.getLongueur()/2)+1)*ppuy));

				}


			}

		}else if(j == BatailleNavalleJoueurCote.DROIT){

			if(b.getOrientation() == BateauOrientation.HORIZONTAL){
				//g.drawImage(ShipsXX1.getInstance().getBateau(b.getLongueur(), true).getImage(), (2*jeu.WIDTH - b.getPosx()) * ppux, (b.getPosy() - b.getLongueur())*ppuy, ppux, b.getLongueur()*ppuy, null);
				g.drawImage(bateauRepo.getBateau(b.getLongueur(), false).flipH().getImage(), (2*jeu.WIDTH - b.getPosx() - b.getLongueur()) * ppux, b.getPosy()*ppuy, b.getLongueur()*ppux, ppuy, null);

			}else{
				g.drawImage(bateauRepo.getBateau(b.getLongueur(), true).flipH().getImage(), ((2*jeu.WIDTH - b.getPosx()-1)) * ppux, (b.getPosy())*ppuy, ppux, b.getLongueur()*ppuy, null);

			}
		}
	}

	public void drawText(Graphics g){

		if(!text.equals(null)){
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
			tab[1] = jeu.WIDTH - (rx-jeu.WIDTH)-1; // on inverse les coordonn�es en x
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
	
	public void explode(Point p, int j){
		Point rp;
		if(j == 1){
			rp = p;
		}else{
			
			rp = new Point((int)(jeu.WIDTH - (p.getX()-jeu.WIDTH)-1), (int)p.getY());
		}
		Animation an = SpriteExplostionRepository.getInstance().getExplosion();
		an.loop(false);
		an.start();
		explosions.add(new Pair<Point, Animation>(rp,an));
	}

	public void target(Point p, int j){
		if(j == 1){
			target = p;
		}else{
			
			target = new Point((int)(jeu.WIDTH - (p.getX()-jeu.WIDTH)-1), (int)p.getY());
		}
	}

	public void untarget(){
		target = null;
	}
	
	public void select(Point p, int j){
		if(j == 1){
			select = p;
		}else{
			
			select = new Point((int)(jeu.WIDTH - (p.getX()-jeu.WIDTH)-1), (int)p.getY());
		}
	}

	public void unselect(){
		select = null;
	}


	@Override
	public void stop(){	
		super.stop();
		removeTireListener();
		unselect();
		untarget();
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
	
	
	public void setGraphiqueEpoque(Epoque e) {
		switch (e) {
		case XIX:
			bateauRepo = ShipsXIX.getInstance();
			break;
		case XX:
			bateauRepo = ShipsXX1.getInstance();
			break;
		case XXI:
			bateauRepo = ShipsXXI.getInstance();
			break;
		}
	}



}
