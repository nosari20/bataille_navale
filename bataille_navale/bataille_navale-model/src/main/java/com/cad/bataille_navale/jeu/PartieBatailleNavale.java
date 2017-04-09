package com.cad.bataille_navale.jeu;

import java.util.HashMap;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class PartieBatailleNavale implements Partie {

	private final static int GAME_HEIGHT = 12;
	private final static int GAME_WIDTH = 12;

	private int joueur = 1;
	private String nom;
	private Mode mode = new ModeNormal();
	private Epoque epoque = Epoque.XXI;

	private String[][] grilleJ1;
	private HashMap<String, Bateau> bateauxJ1;
	private String[][] grilleJ2;
	private HashMap<String, Bateau> bateauxJ2;

	public PartieBatailleNavale(Epoque e) {
		grilleJ1 = new String[GAME_WIDTH][GAME_HEIGHT];
		bateauxJ1 = new HashMap<String, Bateau>();
		grilleJ2 = new String[GAME_WIDTH][GAME_HEIGHT];
		bateauxJ2 = new HashMap<String, Bateau>();
		epoque = e;		
	}

	public int jouer(Action a) {
		if (mode instanceof ModeNormal && a instanceof TireBateau)
			return BatailleNavale.Code.IMPOSSIBLE;

		if (a instanceof TireBateau) {
			int x = ((TireBateau) a).getPosx();
			int y = ((TireBateau) a).getPosy();
			Bateau tirreur = ((TireBateau) a).getTirreur();
			if (tirreur.isReachable(x, y))
				return tirer(x, y, tirreur);
		}

		if (a instanceof FrappeOrbitale) {
			int x = ((FrappeOrbitale) a).getPosx();
			int y = ((FrappeOrbitale) a).getPosy();
			return tirer(x, y, 1);
		}

		return BatailleNavale.Code.IMPOSSIBLE;
	}

	public int tirer(int x, int y, Bateau tirreur) {
		// return tirer(x,y,tirreur.degats());
		return tirer(x, y, 1);
	}

	public int tirer(int x, int y, int degat) {
		String k = grilleCourante()[x][y];
		if (x < 0 || x > grilleCourante().length || y < 0 || y > grilleCourante()[0].length)
			return BatailleNavale.Code.IMPOSSIBLE;
		if (k.equals("")) {
			return BatailleNavale.Code.VIDE;
		} else {
			Bateau cible = bateauxCourants().get(k);
			// cible.hit();
			return BatailleNavale.Code.TOUCHE;
		}
	}

	public void joueurSuivant() {
		joueur = (joueur + 1) % 2 + 1;
	}

	private String[][] grilleCourante() {
		if (joueur == 1)
			return grilleJ1;
		return grilleJ2;
	}

	private HashMap<String, Bateau> bateauxCourants() {
		if (joueur == 1)
			return bateauxJ1;
		return bateauxJ2;
	}

	public Mode getMode() {
		return mode;
	}
	
	public Epoque getEpoque() {
		return epoque;
	}

	public void setBateauJ1(Bateau bateauCase1J1, Bateau bateauCase2J2, Bateau bateauCase3J3, Bateau bateauCase4J4,
			Bateau bateauCase5J5) {
		bateauxJ1.put(bateauCase1J1.getNom(), bateauCase1J1);
		bateauxJ1.put(bateauCase2J2.getNom(), bateauCase2J2);
		bateauxJ1.put(bateauCase3J3.getNom(), bateauCase3J3);
		bateauxJ1.put(bateauCase4J4.getNom(), bateauCase4J4);
		bateauxJ1.put(bateauCase5J5.getNom(), bateauCase5J5);
	}

	public void setBateauJ2(Bateau bateauCase1J1, Bateau bateauCase2J2, Bateau bateauCase3J3, Bateau bateauCase4J4,
			Bateau bateauCase5J5) {
		bateauxJ2.put(bateauCase1J1.getNom(), bateauCase1J1);
		bateauxJ2.put(bateauCase2J2.getNom(), bateauCase2J2);
		bateauxJ2.put(bateauCase3J3.getNom(), bateauCase3J3);
		bateauxJ2.put(bateauCase4J4.getNom(), bateauCase4J4);
		bateauxJ2.put(bateauCase5J5.getNom(), bateauCase5J5);
	}
}
