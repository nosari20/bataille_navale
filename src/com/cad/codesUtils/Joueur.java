package com.cad.codesUtils;

public enum Joueur {
	HUMAN(0), COMPUTER(1);

	private final int value;

	Joueur(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
