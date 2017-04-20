package com.cad.bataille_navale.actions;

import com.cad.bataille_navale.actions.TireBateau.Builder;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class FrappeOrbitale extends Action {
	
	private int posx;
	private int posy;
	private Bateau tirreur;
	private BatailleNavalleJoueurCote cote;
	private int nbCases;

	public static class Builder extends Action.Builder {

		private int posx = 0;
		private int posy = 0;
		private int nbCases = 0;
		private BatailleNavalleJoueurCote cote;

		public Builder() {
			super();
		}

		public Action build() {
			return new FrappeOrbitale(this);
		}

		public Builder pos(int x, int y) {
			posx = x;
			posy = y;
			return this;
		}

		public Builder cote(BatailleNavalleJoueurCote cote) {
			this.cote = cote;
			return this;
		}

		public Builder bateauTirreurNbCases(int nbCases) {
			this.nbCases = nbCases;
			return this;
		}
	}

	private FrappeOrbitale(Builder builder) {
		super(builder.partie);
		posx = builder.posx;
		posy = builder.posy;
		// nbCases = builder.nbCases;
		cote = builder.cote;
	}

	public int getPosx() {
		return posx;
	}

	public BatailleNavalleJoueurCote getCote() {
		return cote;
	}

	public int getPosy() {
		return posy;
	}

	public Bateau getTirreur() {
		return tirreur;
	}

	public int getNbCases() {
		// TODO Auto-generated method stub
		return this.nbCases;
	}
}
