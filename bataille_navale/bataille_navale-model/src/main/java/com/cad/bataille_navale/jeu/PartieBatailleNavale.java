package com.cad.bataille_navale.jeu;

import java.util.HashMap;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class PartieBatailleNavale implements Partie {
	
	private final static int GAME_HEIGHT = 12;
	private final static int GAME_WIDTH = 12;
	
	private int joueur = 1;
	
	private String[][] grilleJ1;
	private HashMap<String, Bateau> bateauxJ1;
	private String[][] grilleJ2;
	private HashMap<String, Bateau> bateauxJ2;
	
	

	public PartieBatailleNavale() {
		grilleJ1 =  new String[GAME_WIDTH][GAME_HEIGHT];
		bateauxJ1 = new HashMap<String, Bateau>();
		grilleJ2 =  new String[GAME_WIDTH][GAME_HEIGHT];
		bateauxJ2 = new HashMap<String, Bateau>();
	}
	
	public int jouer(Action a){
		return a.execute();
	}
	
	public int tirer(int x, int y, Bateau tirreur){
		return 0;		
	}
	
	public int tirer(int x, int y){
		String k = grilleCourante()[x][y];
		if(k.equals("")){
			return BatailleNavale.Code.VIDE;
		}else{
			Bateau cible = bateauxCourants().get(k);
			//cible.hit();
			// TODO
		}
		return 0;		
	}
	
	public void joueurSuivant(){
		joueur = (joueur + 1)%2 + 1;
	}
	
	private String[][] grilleCourante(){
		if(joueur == 1)
			return grilleJ1;
		return grilleJ2;	
	}
	
	private HashMap<String, Bateau> bateauxCourants(){
		if(joueur == 1)
			return bateauxJ1;
		return bateauxJ2;	
	}

}
