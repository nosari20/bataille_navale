package com.cad.bataille_navale.bateaux;

public abstract class Bateau {

	// private int nextId;
	protected int id;
	protected String nom;
	protected int resistance;
	protected int posx;
	protected int posy;
	protected int longueur;

	protected int[] etat;
	protected boolean orientation;
	protected int puissance;
	protected int portee;

	public Bateau() {
		// TODO Auto-generated constructor stub
	}

	public void hit(int laCase) {

	}

	public boolean isDestroyed() {
		return false;
	}

	public boolean isReachable(int posx, int posy) {
		return false;
	}

}
