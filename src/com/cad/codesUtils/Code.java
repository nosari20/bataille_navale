package com.cad.codesUtils;

public enum Code {
	OK(0), DEBUT(1), EN_COURS(2), FIN(3), PAUSE(4);

	private final int value;

	Code(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
