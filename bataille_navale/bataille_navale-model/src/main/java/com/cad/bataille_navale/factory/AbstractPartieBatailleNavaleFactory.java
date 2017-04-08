package com.cad.bataille_navale.factory;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.jeu_abstrait.Partie;

public abstract class AbstractPartieBatailleNavaleFactory {

	public Partie CreatePartie() {
		return null;
	}

	public abstract Bateau case5();

	public abstract Bateau case4();

	public abstract Bateau case3();

	public abstract Bateau case2();

	public abstract Bateau case1();

}
