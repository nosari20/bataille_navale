package com.cad.codesUtils;

public class DAOXmlUtils {

	public static final String BATAILLE_NAVALE = "bataille_navale";
	public static final String JOUEURS = "joueurs";
	public static final String JOUEUR1 = "joueur1";
	public static final String JOUEUR2 = "joueur2";

	public static final String JOUEUR = "joueur";
	public static final String BATEAUX = "bateaux";
	public static final String BATEAU = "bateau";
	public static final String BATEAU1CASE = "bateau1case";
	public static final String BATEAU2CASE = "bateau2case";
	public static final String BATEAU3CASE = "bateau3case";
	public static final String BATEAU4CASE = "bateau4case";
	public static final String BATEAU5CASE = "bateau5case";
	public static final String ETATS = "etats";
	public static final String ETAT = "etat";
	public static final String ORIENTATION = "orientation";
	public static final String LOCALISATIONS = "localisation";
	public static final String LONGUEUR = "longeur";
	public static final String RESISTANCE = "resistance";
	public static final String PROJECTILE = "projectile";
	public static final String GRILLES = "grilles";
	public static final String GRILLE = "grille";
	public static final String CASE = "case";
	public static final String POSX = "posx";
	public static final String POSY = "posy";
	public static final String SEPARATEUR = "-";
	public static final String SEPARATEUR_COORD = ";";
	public static final String COORDONNEE = "coord";

	public static String getBateauCasesLabel(int longueur) {
		switch (longueur) {
		case 1:
			return BATEAU1CASE;
		case 2:
			return BATEAU2CASE;
		case 3:
			return BATEAU3CASE;
		case 4:
			return BATEAU4CASE;
		case 5:
			return BATEAU5CASE;
		default:
			return "";
		}
	}
}
