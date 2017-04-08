package com.cad.bataille_navale.mode;

import com.cad.jeu_abstrait.Action;

public interface Mode {

	public Action.Builder getActionBuilder();
}
