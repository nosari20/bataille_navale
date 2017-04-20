package com.cad.codesUtils.bateau;

public class Bateau3CodeUtils implements BateauCodeUtils {
	private final int BATEAU_PUISSANCE = 1;
	private final int BATEAU_RESISTANCE = 3;
	private final int BATEAU_LONGUEUR = 3;
	private final String IMAGE = "bateau3.png";
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

	public String getImage() {
		return IMAGE;
	}

	@Override
	public int getPuissance() {
		return BATEAU_PUISSANCE;
	}
}
