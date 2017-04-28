package com.cad.bataille_navale.jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.codesUtils.BatailleNavaleGrille;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.bateau.BateauOrientation;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;
import com.cad.ui.sprites_repository.ShipsXIX;
import com.cad.ui.sprites_repository.ShipsXX1;
import com.cad.ui.sprites_repository.ShipsXXI;
import com.cad.ui.sprites_repository.SpriteBateauRepository;

import static com.cad.codesUtils.epoque.Epoque.*;
import static com.cad.codesUtils.epoque.Epoque.XIX;

public class PartieBatailleNavale implements Partie {

	private int joueur = 1;
	private String nom;
	private Epoque epoque = Epoque.XIX;
	private SpriteBateauRepository graphiqueEpoque;

	private int[][] grilleJ1;
	private List<Bateau> bateauxJ1;
	private int[][] grilleJ2;
	private List<Bateau> bateauxJ2;
	
	private int status = BatailleNavale.Code.DEBUT;

	public PartieBatailleNavale(Epoque e) {
		grilleJ1 = new int[BatailleNavale.Code.GAME_WIDTH][BatailleNavale.Code.GAME_HEIGHT];
		for (int i = 0; i < grilleJ1.length; i++) {
			for (int j = 0; j < grilleJ1[0].length; j++) {
				grilleJ1[i][j] = BatailleNavale.Code.VIDE;
			}
		}
		bateauxJ1 = new ArrayList<Bateau>();
		grilleJ2 = new int[BatailleNavale.Code.GAME_WIDTH][BatailleNavale.Code.GAME_HEIGHT];
		for (int i = 0; i < grilleJ2.length; i++) {
			for (int j = 0; j < grilleJ2[0].length; j++) {
				grilleJ2[i][j] = BatailleNavale.Code.VIDE;
			}
		}
		bateauxJ2 = new ArrayList<Bateau>();
        System.out.println(e);
		epoque = e;

	}

	public int jouer(Action a) {
		if(status == BatailleNavale.Code.DEBUT) return BatailleNavale.Code.IMPOSSIBLE;

		if (a instanceof TireBateau) {
			int x = ((TireBateau) a).getPosx();
			int y = ((TireBateau) a).getPosy();
			Bateau tirreur = ((TireBateau) a).getTirreur();
			tirreur.hasHit();
			BatailleNavalleJoueurCote cote = ((TireBateau) a).getCote();
			
			return tirer(x, y, tirreur, cote);
		}

		if (a instanceof FrappeOrbitale) {
			int x = ((FrappeOrbitale) a).getPosx();
			int y = ((FrappeOrbitale) a).getPosy();
			BatailleNavalleJoueurCote cote = ((FrappeOrbitale) a).getCote();
			return tirer(x, y, cote);

		}

		return BatailleNavale.Code.IMPOSSIBLE;
	}

	public boolean isPartieFinished() {

		if (isAllProjectileGone(bateauxJ1) || isAllProjectileGone(bateauxJ2))
			return true;
		return false;
	}

	private boolean isAllProjectileGone(List<Bateau> list) {
		for (Bateau b : list) {
			if (b.isStillProjectileLeft())
				return false;
		}
		return true;
	}

	private boolean isAllBateauDestroyed(List<Bateau> list) {
		for (Bateau b : list) {
			if (!b.isDestroyed())
				return false;
		}
		return true;
	}

	public int tirer(int x, int y, Bateau tirreur, BatailleNavalleJoueurCote cote) {
		// return tirer(x,y,tirreur.degats());
		if (cote == BatailleNavalleJoueurCote.GAUCHE) {

			return traiteTir(x, y, tirreur, bateauxJ2, grilleJ2);
		}
		return traiteTir(x, y, tirreur, bateauxJ1, grilleJ1);
	}

	public int tirer(int x, int y, BatailleNavalleJoueurCote cote) {
		
		System.out.println("************************");
		System.out.println(cote);
		// return tirer(x,y,tirreur.degats());
		if (cote == BatailleNavalleJoueurCote.GAUCHE) {
			System.out.println("gauche ->  droite");
			return traiteTir(x, y, bateauxJ2, grilleJ2);
		}
		System.out.println("droite ->  gauche");
		return traiteTir(x, y, bateauxJ1, grilleJ1);
	}

	private Bateau getBateauWithNbCases(List<Bateau> list, int nbCases) {
		for (Bateau b : list) {
			if (b.getLongueur() == nbCases)
				return b;
		}
		return null;
	}

	private int traiteTir(int x, int y, Bateau tirreur, List<Bateau> list, int[][] grille) {
		// Si aucun tire n'a encore atteri dans cette case
		if (grille[x][y] == BatailleNavale.Code.VIDE || grille[x][y] == BatailleNavale.Code.TOUCHE) {
			// alors on tire dessus
			Coord c = new Coord(x, y);
			int res = -1;

			
			listBateau: for (Bateau b : list) {
				
				res = b.contientCoord(c);
				if (res != -1 && !b.isDestroyed()) {
					int h =  b.hit(res, tirreur.getPuissance());
					grille[x][y] = h;
					if(h == BatailleNavale.Code.DETRUIT){

						detruit(b, grille);

					}
					break listBateau;
				}
			}
			// si il n'y pas de bateau sur cette cordonn� alors on le marque
			// comme touche_vide
			if (res == -1)
				grille[x][y] = BatailleNavale.Code.TOUCHE_VIDE;

			return grille[x][y];
		}
		// Sinon on ne pas tirer
		else
			return BatailleNavale.Code.IMPOSSIBLE;
	}

	private int traiteTir(int x, int y, List<Bateau> list, int[][] grille) {
		// Si aucun tire n'a encore atteri dans cette case
		if (grille[x][y] == BatailleNavale.Code.VIDE || grille[x][y] == BatailleNavale.Code.TOUCHE) {
			// alors on tire dessus
			Coord c = new Coord(x, y);
			int res = -1;
			int count = 0;
			for (Bateau b : list) {
			
				res = b.contientCoord(c);
				if (res != -1 && !b.isDestroyed()) {
					count++;
					int h =  b.hit(res, 1);
					grille[x][y] = h;
					if(h == BatailleNavale.Code.DETRUIT){
						detruit(b, grille);
					}
				}
			}
			// si il n'y pas de bateau sur cette cordonn� alors on le marque
			// comme touche_vide
			if (count == 0){
				grille[x][y] = BatailleNavale.Code.TOUCHE_VIDE;
			}else{
				if(isAllBateauDestroyed(list)){
					this.status = BatailleNavale.Code.FIN;
				}

			}
			

			return grille[x][y];
		}
		// Sinon on ne pas tirer
		else
			return BatailleNavale.Code.IMPOSSIBLE;
	}


	private void detruit(Bateau b, int[][] grille){

		if(b.getOrientation() == BateauOrientation.HORIZONTAL){
			for(int i = 0; i < b.getLongueur(); i++ ){
				grille[b.getPosx()+i][b.getPosy()] = BatailleNavale.Code.DETRUIT;
			}
		}

		if(b.getOrientation() == BateauOrientation.VERTICAL){
			for(int i = 0; i < b.getLongueur(); i++ ){
				grille[b.getPosx()][b.getPosy()+i] = BatailleNavale.Code.DETRUIT;
			}
		}
		
	}

	public void joueurSuivant() {
		joueur = (joueur + 1) % 2 + 1;
		//this.notifyAll();
	}

	private int[][] grilleCourante() {
		if (joueur == 1)
			return grilleJ1;
		return grilleJ2;
	}

	public Epoque getEpoque() {
		return epoque;
	}

	public void setBateauJ1(Bateau bateauCase1, Bateau bateauCase2, Bateau bateauCase3, Bateau bateauCase4,
			Bateau bateauCase5) {
		addBateau(this.bateauxJ1, bateauCase1);
		addBateau(this.bateauxJ1, bateauCase2);
		addBateau(this.bateauxJ1, bateauCase3);
		addBateau(this.bateauxJ1, bateauCase4);
		addBateau(this.bateauxJ1, bateauCase5);
	}

	public void setBateauJ2(Bateau bateauCase1, Bateau bateauCase2, Bateau bateauCase3, Bateau bateauCase4,
			Bateau bateauCase5) {
		addBateau(this.bateauxJ2, bateauCase1);
		addBateau(this.bateauxJ2, bateauCase2);
		addBateau(this.bateauxJ2, bateauCase3);
		addBateau(this.bateauxJ2, bateauCase4);
		addBateau(this.bateauxJ2, bateauCase5);
	}

	public void addBateau(List<Bateau> list, Bateau b) {
		list.add(b);
	}

	public List<Bateau> getBateauJ1() {
		return bateauxJ1;
	}

	public List<Bateau> getBateauJ2() {
		return bateauxJ2;
	}


	public void show() {
		System.out.println("GRILLE JOUEUR 1");
		showBoard(this.grilleJ1);
		System.out.println("GRILLE JOUEUR 2");
		showBoard(this.grilleJ2);
	}

	public void showBoard(int[][] board) {
		System.out.println("\t0 \t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10 \t11");
		System.out.println();

		for (int row = 0; row < BatailleNavale.Code.GAME_WIDTH; row++) {
			System.out.print((row) + "");
			for (int column = 0; column < BatailleNavale.Code.GAME_HEIGHT; column++) {
				if (board[row][column] == BatailleNavale.Code.VIDE) {
					System.out.print("\t" + "~");
				} else if (board[row][column] == BatailleNavale.Code.TOUCHE_VIDE) {
					System.out.print("\t" + "*");
				} else if (board[row][column] == BatailleNavale.Code.TOUCHE) {
					System.out.print("\t" + "0");
				} else if (board[row][column] == BatailleNavale.Code.DETRUIT) {
					System.out.print("\t" + "X");
				}

			}
			System.out.println();
		}

	}

	public List<Bateau> getListOfBateau(BatailleNavalleJoueurCote cote) {
		if (cote == BatailleNavalleJoueurCote.GAUCHE)
			return bateauxJ1;
		return bateauxJ2;
	}

	public int[][] getGrille(BatailleNavalleJoueurCote cote) {
		if (cote == BatailleNavalleJoueurCote.GAUCHE)
			return grilleJ1;
		return grilleJ2;
	}
	

	public int getResult() {
		int resultJ1 = getScoreJ1();
		int resultJ2 = getScoreJ2();

		System.out.println(resultJ1);
		System.out.println(resultJ2);
		

		if (resultJ1 < resultJ2)
			return BatailleNavale.Code.VICTOIRE_J2;
		else if (resultJ1 > resultJ2)
			return BatailleNavale.Code.VICTOIRE_J1;

		return BatailleNavale.Code.DRAW;
	}
	
	public int getScoreJ1() {
		int resultJ1 = 0;

		for (Bateau b : bateauxJ2) {
			resultJ1 += b.getNbPointDegat();
		}
		return resultJ1;
	}
	
	public int getScoreJ2() {
		int resultJ2 = 0;

		for (Bateau b : bateauxJ1) {
			resultJ2 += b.getNbPointDegat();
		}
		return resultJ2;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nomPartie) {
		this.nom = nomPartie;
	}

	public Bateau getBateauJ1(int x, int y) {
		int res = -1;
		Coord c = new Coord(x, y);
		l: for (Bateau b : bateauxJ1) {
			res = b.contientCoord(c);
			if (res != -1) {
				return b;
			}
		}
		return null;

	}

	public Bateau getBateauJ2(int x, int y) {
		int res = -1;
		Coord c = new Coord(x, y);
		l: for (Bateau b : bateauxJ2) {
			res = b.contientCoord(c);
			if (res != -1) {
				return b;
			}
		}
		return null;

	}

	public boolean placementOk(Bateau b){	
		Coord c = new Coord(b.getPosx(), b.getPosy());
		if(c.x < 0 || c.x >= grilleJ1.length || c.y < 0 || c.y >= grilleJ1[0].length) return false;

		if(b.getOrientation() == BateauOrientation.HORIZONTAL){
			for(int i = 0; i < b.getLongueur(); i++ ){
				if(c.x + i >= grilleJ1.length ) return false;
			}
		}

		if(b.getOrientation() == BateauOrientation.VERTICAL){
			for(int i = 0; i < b.getLongueur(); i++ ){
				if(c.y + i >= grilleJ1[0].length ) return false;
			}
		}


		return true;

	}
	
	public int getStatus(){
		return status;
	}

	public void play() {
		this.status = BatailleNavale.Code.EN_COURS; 		
	}

	public SpriteBateauRepository getGraphiqueEpoque() {
		return graphiqueEpoque;
	}

	public void setGraphiqueEpoque() {
		switch (epoque){
			case XIX :
                this.graphiqueEpoque = ShipsXIX.getInstance();
				break;
            case XX:
                this.graphiqueEpoque = ShipsXX1.getInstance();
				break;
            case XXI:
                this.graphiqueEpoque = ShipsXXI.getInstance();
                break;
		}
	}



}
