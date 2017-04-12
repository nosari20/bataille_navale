package com.cad.bataille_navale.jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class PartieBatailleNavale implements Partie {

	public final int GAME_HEIGHT = 12;
	public final int GAME_WIDTH = 12;

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
		for (int i = 0; i < grilleJ1.length; i++) {
			for (int j = 0; j < grilleJ1[0].length; j++) {
				grilleJ1[i][j] = "";
			}
		}
		bateauxJ1 = new HashMap<String, Bateau>();
		grilleJ2 = new String[GAME_WIDTH][GAME_HEIGHT];
		for (int i = 0; i < grilleJ2.length; i++) {
			for (int j = 0; j < grilleJ2[0].length; j++) {
				grilleJ2[i][j] = "";
			}
		}
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

	public void setBateauJ1(Bateau bateauCase1, Bateau bateauCase2, Bateau bateauCase3, Bateau bateauCase4,
			Bateau bateauCase5) {
		addBateauJ1(bateauCase1);
		addBateauJ1(bateauCase2);
		addBateauJ1(bateauCase3);
		addBateauJ1(bateauCase4);
		addBateauJ1(bateauCase5);
	}

	public void addBateauJ1(Bateau b){
		bateauxJ1.put(b.getNom(), b);
		if(grilleJ1[b.getPosx()][b.getPosy()].equals("")){
			grilleJ1[b.getPosx()][b.getPosy()] = b.getNom();
		}else{
			grilleJ1[b.getPosx()][b.getPosy()] = b.getNom() + ";" + grilleJ1[b.getPosx()][b.getPosy()];
		}
	}

	public void setBateauJ2(Bateau bateauCase1, Bateau bateauCase2, Bateau bateauCase3, Bateau bateauCase4,
			Bateau bateauCase5) {
		addBateauJ2(bateauCase1);
		addBateauJ2(bateauCase2);
		addBateauJ2(bateauCase3);
		addBateauJ2(bateauCase4);
		addBateauJ2(bateauCase5);
	}

	public void addBateauJ2(Bateau b){
		bateauxJ2.put(b.getNom(), b);
		if(grilleJ2[b.getPosx()][b.getPosy()].equals("")){
			grilleJ2[b.getPosx()][b.getPosy()] = b.getNom();
		}else{
			grilleJ2[b.getPosx()][b.getPosy()] = b.getNom() + ";" + grilleJ2[b.getPosx()][b.getPosy()];
		}		
	}

	public List<Bateau> getBateauJ1(){
		return new ArrayList<Bateau>(bateauxJ1.values());
	}

	public List<Bateau> getBateauJ2(){
		return new ArrayList<Bateau>(bateauxJ2.values());
	}

	public String getCaseJ1(int x, int y){
		return grilleJ1[x][y];
	}

	public String getCaseJ2(int x, int y){
		return grilleJ2[x][y];
	}

	public Bateau getBateauJ1(String id){
		return bateauxJ1.get(id);
	}

	public Bateau getBateauJ2(String id){
		return bateauxJ2.get(id);
	}

	public void updateBateauOrientaion(Bateau b, boolean o) {
		updateBateau(b, b.getPosx(), b.getPosy(), o);
	}



	public void updateBateau(Bateau b, int x, int y, boolean orientaion){
		// TODO Auto-generated method stub
		if(x >= 0 && y >=0 && x < GAME_WIDTH && y < GAME_HEIGHT && ((orientaion && (y + b.getLongueur() < GAME_HEIGHT)) || (!orientaion && (x + b.getLongueur() < GAME_WIDTH)))){
			
			String[][] grille;
			if(bateauxJ1.containsValue(b)){
				grille = grilleJ1;
			}else{
				grille = grilleJ2;
			}


			// suprimer
			if(!b.isOrientation()){						
				for(int i = b.getPosx(); i < b.getPosx() + b.getLongueur(); i++){							
					String c =  grille[i][b.getPosy()];
					String result = c.replaceFirst(Pattern.quote(b.getNom()), "");
					if(result.startsWith(";")){
						result = result.replaceFirst(";", "");
					}
					grille[i][b.getPosy()] = result;
				}
			}else{
				for(int i = b.getPosy(); i < b.getPosy() + b.getLongueur(); i++){						
					String c =  grille[b.getPosx()][i];
					String result = c.replaceFirst(Pattern.quote(b.getNom()), "");
					if(result.startsWith(";")){
						result = result.replaceFirst(";", "");
					}
					grille[b.getPosx()][i] = result;
				}
			}


			// ajouter
			if(!orientaion){
				for(int i = x; i < x + b.getLongueur(); i++){
					grille[i][y] = b.getNom()+";"+grille[i][y];
				}
			}else{
				for(int i = y; i < y + b.getLongueur(); i++){
					grille[x][i] = b.getNom()+";"+grille[x][i];
				}
			}


			b.setOrientation(orientaion);
			b.setPosx(x);
			b.setPosy(y);
		}
	}


	public void updateBateauPos(Bateau b, int x, int y) {
		updateBateau(b, x, y, b.isOrientation());
	}
}
