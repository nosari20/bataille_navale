package com.cad.bataille_navale.jeu;

import java.util.List;

import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;
import com.cad.jeu_abstrait.Partie;

public class BatailleNavale extends Jeu {
	
	
	public static class Code extends Jeu.Code{
		public static final int TOUCHE = nextCode();
		public static final int VIDE = nextCode();
		public static final int TROP_LOIN = nextCode();
		public static final int DETRUIT = nextCode();	
		public static final int IMPOSSIBLE = nextCode();	
	}
	
	
	private Epoque epoque = Epoque.XXI;
	
	

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
	public int jouer(Joueur joueur, Action action) {		
		return ((PartieBatailleNavale) partie).jouer(action);
	}

	@Override
	public Action.Builder actionBuilder() {
		// TODO Auto-generated method stub
		return ((PartieBatailleNavale) partie).getMode().getActionBuilder();
	}
	
	public Mode mode() {
		// TODO Auto-generated method stub
		return ((PartieBatailleNavale) partie).getMode();
	}

}
