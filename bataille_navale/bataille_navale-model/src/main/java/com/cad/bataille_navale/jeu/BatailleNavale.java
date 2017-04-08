package com.cad.bataille_navale.jeu;

import java.util.List;

import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class BatailleNavale extends Jeu {
	
	
	public static class Code extends Jeu.Code{
		public static final int TOUCHE = nextCode();
		public static final int VIDE = nextCode();
		public static final int TROP_LOIN = nextCode();
		public static final int DETRUIT = nextCode();	
	}

	public BatailleNavale(List<Joueur> joueurs) {
		super(joueurs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void chargerPartie(String nomPartie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nouvellePartie(String nomPartie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void joueur(Joueur joueur, Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Action createAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action actionBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

}
