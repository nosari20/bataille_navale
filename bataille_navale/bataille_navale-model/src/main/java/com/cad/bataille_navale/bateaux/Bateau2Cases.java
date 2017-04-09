package com.cad.bataille_navale.bateaux;

import com.cad.codesUtils.bateau.Bateau2CodeUtils;

public class Bateau2Cases extends Bateau {
	public Bateau2Cases() {
		super();
		bateauCode = new Bateau2CodeUtils();
		init();
	}
}
