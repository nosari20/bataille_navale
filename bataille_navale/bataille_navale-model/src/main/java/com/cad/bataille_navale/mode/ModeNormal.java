package com.cad.bataille_navale.mode;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.jeu_abstrait.Action.Builder;

public class ModeNormal implements Mode {

	public ModeNormal() {
		// TODO Auto-generated constructor stub
	}

	public Builder actionBuilder() {
		return new FrappeOrbitale.Builder();
	}


}
