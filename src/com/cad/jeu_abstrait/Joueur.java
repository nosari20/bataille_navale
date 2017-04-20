package com.cad.jeu_abstrait;

public interface Joueur {

	public abstract void setJeu(Jeu j);

	public abstract int jouer();

	public abstract String getNom();

}
