package com.cad.bataille_navale.factory;

import com.cad.bataille_navale.bateaux.*;
import com.cad.codesUtils.epoque.Epoque;

public class AncienPartieBatailleNavaleFactory extends AbstractPartieBatailleNavaleFactory {

	@Override
	public Bateau case5() {
		// TODO Auto-generated method stub
		return new Bateau5Cases();
	}

	@Override
	public Bateau case4() {
		// TODO Auto-generated method stub
		return new Bateau4Cases();
	}

	@Override
	public Bateau case3() {
		// TODO Auto-generated method stub
		return new Bateau3Cases();
	}

	@Override
	public Bateau case2() {
		// TODO Auto-generated method stub
		return new Bateau2Cases();
	}

	@Override
	public Bateau case1() {
		// TODO Auto-generated method stub
		return new Bateau1Cases();
	}
	
	@Override
	public Epoque epoque() {
		// TODO Auto-generated method stub
		return Epoque.XIX;
	}

}
