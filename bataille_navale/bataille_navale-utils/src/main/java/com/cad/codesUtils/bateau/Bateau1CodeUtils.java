package com.cad.codesUtils.bateau;

public class Bateau1CodeUtils implements BateauCodeUtils {
	private final int BATEAU_RESISTANCE = 1;
	private final int BATEAU_LONGUEUR = 1;
	private final String BATEAU_NOM = "B1";

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
