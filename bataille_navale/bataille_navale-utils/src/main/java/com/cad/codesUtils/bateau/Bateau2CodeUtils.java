package com.cad.codesUtils.bateau;

public class Bateau2CodeUtils implements BateauCodeUtils {

	private final int BATEAU_RESISTANCE = 2;
	private final int BATEAU_LONGUEUR = 2;
	private final String BATEAU_NOM = "B2";

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
