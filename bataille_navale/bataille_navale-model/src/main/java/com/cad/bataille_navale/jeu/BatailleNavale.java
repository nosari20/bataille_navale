package com.cad.bataille_navale.jeu;

import java.util.List;

import com.cad.bataille_navale.factory.AncienPartieBatailleNavaleFactory;
import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.mode.Mode;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class BatailleNavale extends Jeu {

	public static class Code extends Jeu.Code {
		public static final int TOUCHE = nextCode();
		public static final int VIDE = nextCode();
		public static final int TROP_LOIN = nextCode();
		public static final int DETRUIT = nextCode();
		public static final int IMPOSSIBLE = nextCode();
	}

	private Epoque epoque = Epoque.XXI;

	public BatailleNavale(List<Joueur> joueurs) {
		super(joueurs);
	}

	@Override
	public void chargerPartie(String nomPartie) {

	}

	@Override
	public void nouvellePartie(String nomPartie) {
		if (epoque == Epoque.XXI) {
			partie = new ModernePartieBatailleNavaleFactory().CreatePartie();
		} else {
			// Epoque ancien
			partie = new AncienPartieBatailleNavaleFactory().CreatePartie();
		}
	}

	public int jouer(Joueur joueur, Action action) {
		return ((PartieBatailleNavale) partie).jouer(action);
	}

	public Action.Builder actionBuilder() {
		return ((PartieBatailleNavale) partie).getMode().getActionBuilder();
	}

	public Mode mode() {
		return ((PartieBatailleNavale) partie).getMode();
	}

}
