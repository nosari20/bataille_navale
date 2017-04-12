package com.cad.bataille_navale.bateaux;

import com.cad.codesUtils.bateau.BateauCodeUtils;

public abstract class Bateau implements Cloneable {

	// private int nextId;
	protected int id;
	protected String nom;
	protected int resistance;
	protected int posx;
	protected int posy;
	protected int longueur;
	protected BateauCodeUtils bateauCode;
	protected int[] etat;
	protected boolean orientation;
	protected int puissance;
	protected int portee;

	public Bateau() {
	}

	public void hit(int laCase) {

	}

	public boolean isDestroyed() {
		return false;
	}

	public boolean isReachable(int posx, int posy) {
		return false;
	}

	protected void init() {
		this.resistance = bateauCode.getBateauResistance();
		this.longueur = bateauCode.getBateauLongueur();
		this.etat = new int[bateauCode.getBateauLongueur()];
		this.nom = bateauCode.getBateauNom();
	}

	public Object clone() {
		Object clone = null;

		try {
			clone = super.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return clone;
	}

	public String getNom() {
		return nom;
	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	public int getLongueur() {
		return longueur;
	}

	public int[] getEtat() {
		return etat;
	}

	public boolean isOrientation() {
		return orientation;
	}
	
	

}
