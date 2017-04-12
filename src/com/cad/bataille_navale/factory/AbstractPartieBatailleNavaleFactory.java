package com.cad.bataille_navale.factory;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.mode.Mode;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Partie;

public abstract class AbstractPartieBatailleNavaleFactory {

	public Partie CreatePartie() {
		PartieBatailleNavale partie = new PartieBatailleNavale(epoque());

		Bateau bateauCase1J1 = case1();
		Bateau bateauCase2J2 = case2();
		Bateau bateauCase3J3 = case3();
		Bateau bateauCase4J4 = case4();
		Bateau bateauCase5J5 = case5();

		partie.setBateauJ1(bateauCase1J1, bateauCase2J2, bateauCase3J3, bateauCase4J4, bateauCase5J5);
		partie.setBateauJ2((Bateau) bateauCase1J1.clone(), (Bateau) bateauCase2J2.clone(),
				(Bateau) bateauCase3J3.clone(), (Bateau) bateauCase4J4.clone(), (Bateau) bateauCase5J5.clone());

		return partie;
	}

	public abstract Bateau case5();

	public abstract Bateau case4();

	public abstract Bateau case3();

	public abstract Bateau case2();

	public abstract Bateau case1();
	
	public abstract Epoque epoque();
	
}
