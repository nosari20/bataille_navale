package com.cad.codesUtils;

public enum BatailleNavaleGrille {
	VIDE(0), TOUCHE(1), RATE(2);

	private final int value;

	BatailleNavaleGrille(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
