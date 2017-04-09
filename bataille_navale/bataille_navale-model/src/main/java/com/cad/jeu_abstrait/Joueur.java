package com.cad.jeu_abstrait;

public abstract class Joueur {

	protected Jeu jeu;
	
	public void setJeu(Jeu j){
		jeu = j;
	}

	public abstract int jouer();

}
