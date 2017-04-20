package com.cad.bataille_navale.factory;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.mode.Mode;
import com.cad.codesUtils.bateau.BateauOrientation;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Partie;

public abstract class AbstractPartieBatailleNavaleFactory {

	public Partie CreatePartie() {
		PartieBatailleNavale partie = new PartieBatailleNavale(epoque());

		Bateau bateauCase1J1 = case1();
		bateauCase1J1.setUp(0, 0, BateauOrientation.HORIZONTAL);
		Bateau bateauCase2J2 = case2();
		bateauCase2J2.setUp(2, 8, BateauOrientation.VERTICAL);
		Bateau bateauCase3J3 = case3();
		bateauCase3J3.setUp(3, 5, BateauOrientation.VERTICAL);
		Bateau bateauCase4J4 = case4();
		bateauCase4J4.setUp(1, 3, BateauOrientation.HORIZONTAL);
		Bateau bateauCase5J5 = case5();
		bateauCase5J5.setUp(7, 1, BateauOrientation.HORIZONTAL);

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
