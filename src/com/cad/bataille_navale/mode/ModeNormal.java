package com.cad.bataille_navale.mode;

import java.util.ArrayList;
import java.util.List;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Action.Builder;

public class ModeNormal implements Mode {

	public ModeNormal() {
		// TODO Auto-generated constructor stub
	}

	public Builder actionBuilder() {
		return new FrappeOrbitale.Builder();
	}

	@Override
	public List<Builder> actionBuilders() {
		ArrayList<Builder> l = new ArrayList<>();
		l.add(new FrappeOrbitale.Builder());
		return l;
	}

	@Override
	public boolean allow(Action a) {
		return a instanceof FrappeOrbitale;
	}


}
