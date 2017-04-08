package com.cad.bataille_navale.actions;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class TireBateau implements Action {

	private int posx;
	private int posy;
	private Bateau tirreur;
	private Partie partie;

	public class Builder {

		private int posx = 0;
		private int posy = 0;
		private Bateau tirreur = null;
		private Partie partie;

		public Builder(Partie p) {
			partie = p;
		}

		public Action build() {

			return new TireBateau(this);
		}

		public Builder pos(int x, int y) {
			posx = x;
			posy = y;
			return this;
		}

		public Builder tirreur(Bateau t) {
			tirreur = t;
			return this;
		}

	}

	private TireBateau(Builder builder) {
		partie = builder.partie;
		posx = builder.posx;
		posy = builder.posy;
		tirreur = builder.tirreur;
	}

	public int execute() {
		return ((PartieBatailleNavale) partie).tirer(posx, posy, tirreur);
	}

}
