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
		bateauCase1J1.setUp(0, 0, BateauOrientation.VERTICAL);
		Bateau bateauCase2J1 = case2();
		bateauCase2J1.setUp(2, 8, BateauOrientation.VERTICAL);
		Bateau bateauCase3J1 = case3();
		bateauCase3J1.setUp(3, 5, BateauOrientation.VERTICAL);
		Bateau bateauCase4J1 = case4();
		bateauCase4J1.setUp(1, 3, BateauOrientation.HORIZONTAL);
		Bateau bateauCase5J1 = case5();
		bateauCase5J1.setUp(7, 1, BateauOrientation.HORIZONTAL);

		partie.setBateauJ1(bateauCase1J1, bateauCase2J1, bateauCase3J1, bateauCase4J1, bateauCase5J1);
		
		
		Bateau bateauCase1J2 = case1();
		bateauCase1J2.setUp(0, 0, BateauOrientation.VERTICAL);
		Bateau bateauCase2J2 = case2();
		bateauCase2J2.setUp(2, 8, BateauOrientation.VERTICAL);
		Bateau bateauCase3J2 = case3();
		bateauCase3J2.setUp(3, 5, BateauOrientation.VERTICAL);
		Bateau bateauCase4J2 = case4();
		bateauCase4J2.setUp(1, 3, BateauOrientation.HORIZONTAL);
		Bateau bateauCase5J2 = case5();
		bateauCase5J2.setUp(7, 1, BateauOrientation.HORIZONTAL);

		partie.setBateauJ2(bateauCase1J2, bateauCase2J2, bateauCase3J2, bateauCase4J2, bateauCase5J2);
		

		return partie;
	}

	public abstract Bateau case5();

	public abstract Bateau case4();

	public abstract Bateau case3();

	public abstract Bateau case2();

	public abstract Bateau case1();

	public abstract Epoque epoque();

}
