package com.cad.bataille_navale.bateaux;

import com.cad.codesUtils.bateau.Bateau1CodeUtils;
import com.cad.codesUtils.bateau.BateauCodeUtils;

public class Bateau1Cases extends Bateau {

	public Bateau1Cases() {
		super();
		bateauCode = new Bateau1CodeUtils();
		init();
	}
}
