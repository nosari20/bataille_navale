package com.cad.jeu_abstrait;

import java.util.List;

public abstract class Jeu {
	
	List<Joueur> listeJoueurs;
	Partie partie;
	public Jeu( List<Joueur> joueurs){
		this.listeJoueurs = joueurs;
	}
	
	public static class Code{
		public static final int OK = nextCode();
		public static final int DEBUT = nextCode();
		public static final int EN_COURS = nextCode();
		public static final int FIN = nextCode();
		public static final int PAUSE = nextCode();
		
		private static int nextCode = 0;		
		protected static int nextCode(){
			return nextCode++;
		}
			
	}
	
	public abstract void chargerPartie(String nomPartie);
	public abstract void nouvellePartie(String nomPartie);
	public abstract void joueur(Joueur joueur, Action action);
	public abstract Action createAction();
	public abstract Action actionBuilder();
	

	
	
}
