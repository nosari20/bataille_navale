package com.cad.bataille_navale.actions;

import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Partie;

public class FrappeOrbitale extends Action {
	
	private int posx;
	private int posy;

	public static class Builder extends Action.Builder {

		private int posx = 0;
		private int posy = 0;
		
		public Builder(){
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
	}

	private FrappeOrbitale(Builder builder) {
		super(builder.partie);
		posx = builder.posx;
		posy = builder.posy;
	}
	
	
	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}


	public int execute() {
		// TODO
		return 0;
	}

}
