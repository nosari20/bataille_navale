package com.cad.bataille_navale.actions;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class TireBateau extends Action {
	
	private int posx;
	private int posy;
	private Bateau tirreur;

	

	public static class Builder extends Action.Builder {

		private int posx = 0;
		private int posy = 0;
		private Bateau tirreur = null;
		
		public Builder(){
			super();
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
		super(builder.partie);
		posx = builder.posx;
		posy = builder.posy;
		tirreur = builder.tirreur;
	}
	
	
	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}
	
	public Bateau getTirreur() {
		return tirreur;
	}

	public int execute() {
		// TODO
		return 0;
	}

}

