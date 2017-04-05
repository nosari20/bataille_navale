package com.cad.jeu_abstrait;

import java.util.List;

public abstract class Jeu {
	
	List<Joueur> listeJoueurs;
	Partie partie;
	public Jeu( List<Joueur> joueurs){
		this.listeJoueurs = joueurs;
	}
	
	private class Code{
		public static final int OK = 0;
		public static final int DEBUT = 1;
		public static final int EN_COURS = 2;
		public static final int FIN = 3;
		public static final int PAUSE = 4;
				
			
	}
	
	public abstract void chargerPartie(String nomPartie);
	public abstract void nouvellePartie(String nomPartie);
	public abstract void joueur(Joueur joueur, Action action);
	public abstract Action createAction();
	public abstract Action actionBuilder();
	

	
	
}
