package com.cad.bataille_navale.actions;

import com.cad.jeu_abstrait.Action;

public class FrappeOrbitale implements Action {
	
	private int posx;
	private int posy;

	

	public static class Builder implements Action.Builder {

		private int posx = 0;
		private int posy = 0;

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
		posx = builder.posx;
		posy = builder.posy;
	}
	
	
	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	
}
