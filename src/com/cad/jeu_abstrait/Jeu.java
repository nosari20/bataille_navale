package com.cad.jeu_abstrait;

import java.util.List;
import java.util.Observable;


public abstract class Jeu extends Observable{

	protected List<Joueur> listeJoueurs;
	protected Partie partie;
	protected int status;

	public Jeu(List<Joueur> joueurs) {
		this.listeJoueurs = joueurs;
		for (Joueur joueur : joueurs) {
			joueur.setJeu(this);
		}
	}

	public static class Code {
		public static final int OK = nextCode();
		public static final int DEBUT = nextCode();
		public static final int EN_COURS = nextCode();
		public static final int FIN = nextCode();
		public static final int PAUSE = nextCode();

		private static int nextCode = 1000000;

		protected static int nextCode() {
			return nextCode++;
		}

	}

	public int status() {
		return status;
	}

	public abstract void chargerPartie(String nomPartie);

	public abstract void nouvellePartie(String nomPartie);

	public abstract int jouer(Joueur joueur, Action action);

	public abstract Action.Builder actionBuilder();

	public abstract boolean isFinished();

	public abstract void sauvegarder();

	public Partie currentPartie() {
		return partie;
	}
	
	public List<Joueur> getJoueurs(){
		return listeJoueurs;
	}

}
