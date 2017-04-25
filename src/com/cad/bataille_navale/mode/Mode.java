package com.cad.bataille_navale.mode;

import java.util.List;

import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Action.Builder;

public interface Mode {

	public Builder actionBuilder();
	
	public List<Builder> actionBuilders();
	
	public boolean allow(Action a);
}
