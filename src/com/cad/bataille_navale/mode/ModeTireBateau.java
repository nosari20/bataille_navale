package com.cad.bataille_navale.mode;

import java.util.ArrayList;
import java.util.List;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Action.Builder;

public class ModeTireBateau implements Mode {

	public ModeTireBateau() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Builder actionBuilder() {
		// TODO Auto-generated method stub
		return new TireBateau.Builder();
	}

	@Override
	public List<Builder> actionBuilders() {
		ArrayList<Builder> l = new ArrayList<>();
		l.add(new TireBateau.Builder());
		l.add(new FrappeOrbitale.Builder());
		return l;
	}

	@Override
	public boolean allow(Action a) {
		return (a instanceof TireBateau) || (a instanceof FrappeOrbitale);
	}

}
