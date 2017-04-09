package com.cad.codesUtils.bateau;

public class Bateau5CodeUtils implements BateauCodeUtils {
	private final int BATEAU_RESISTANCE = 3;
	private final int BATEAU_LONGUEUR = 5;
	private final String BATEAU_NOM = "B5";

	public int getBateauResistance() {
		return BATEAU_RESISTANCE;
	}

	public int getBateauLongueur() {
		return BATEAU_LONGUEUR;
	}

	public String getBateauNom() {
		return BATEAU_NOM;
	}
}
