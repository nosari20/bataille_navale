package com.cad.bataille_navale.bateaux;

import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.codesUtils.bateau.BateauCodeUtils;
import com.cad.codesUtils.bateau.BateauOrientation;

public abstract class Bateau implements Cloneable {

	// private int nextId;
	protected int id;
	protected String nom;
	protected int resistance;
	protected int posx;
	protected int posy;
	protected int longueur;
	protected int nbProjectile;
	protected BateauCodeUtils bateauCode;
	protected int[] etat;
	protected Coord[] localisation;
	protected BateauOrientation orientation;
	protected int puissance;
	protected int portee;

	private static int nextId = 0;

	public Bateau() {
	}

	public int hit(int laCase, int puissance) {
		if(etat[laCase] <= 0)
			return BatailleNavale.Code.IMPOSSIBLE;
		// la case a été touché donc on reduit sa resistance de sa puissance
		etat[laCase] -= puissance;
		// Maitenant on essaie de verifier si la case à été detruite
		if (etat[laCase] > 0) {
			System.out.println("TOUCHE");
			return BatailleNavale.Code.TOUCHE;
		}
		
		if(isDestroyed()){
			return BatailleNavale.Code.DETRUIT;
		}

		return BatailleNavale.Code.CASE_DETRUITE;
		
	}

	public int getNbPointDegat() {
		int res = 0;
		for (int i = 0; i < etat.length; i++) {
			res += (etat[i] <= 0) ? 1 : 0;
		}
		return res;
	}

	public boolean isDestroyed() {
		for (int i = 0; i < etat.length; i++) {
			// si il existe une case qui n'est pas detruite alors on retourne
			// faux
			if (etat[i] > 0)
				return false;
		}
		return true;
	}

	public boolean isReachable(int posx, int posy) {
		return false;
	}

	protected void init() {
		this.resistance = bateauCode.getBateauResistance();
		this.longueur = bateauCode.getBateauLongueur();
		this.puissance = bateauCode.getPuissance();
		this.localisation = new Coord[longueur];
		this.etat = new int[bateauCode.getBateauLongueur()];
		this.portee = 3;
		this.nbProjectile = bateauCode.NB_PROJECTILE;
		for (int i = 0; i < this.etat.length; i++) {
			etat[i] = resistance;
		}
		this.nom = "b" + nextId;
		nextId++;
	}

	
	public void setUp(int x, int y, BateauOrientation orientation) {
		this.posx = x;
		this.posy = y;
		this.orientation = orientation;
		if (orientation == BateauOrientation.HORIZONTAL) {
			for (int i = 0; i < longueur; i++) {
				localisation[i] = new Coord(posx + i, posy);
			}
		} else {
			for (int i = 0; i < longueur; i++) {
				localisation[i] = new Coord(posx, posy + i);
			}
		}
	}
	
	public void update(){
		setUp(this.posx,this.posy,this.orientation);
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

	public int getPuissance() {
		return puissance;
	}

	public int[] getEtat() {
		return etat;
	}

	public BateauOrientation getOrientation() {
		return orientation;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public void setOrientation(BateauOrientation orientation) {
		this.orientation = orientation;
	}

	public int contientCoord(Coord c) {
		for (int i = 0; i < localisation.length; i++) {
			if (localisation[i].equals(c))
				return i;
		}
		return -1;
	}

	public void hasHit() {
		nbProjectile--;
	}

	@Override
	public String toString() {
		return "Bateau [id=" + id + ", nom=" + nom + ", resistance=" + resistance + ", longueur=" + longueur
				+ ", nbProjectile=" + nbProjectile + ", puissance=" + puissance + ", position=(" + posx + ", " + posy + ")]";
	}

	public boolean isStillProjectileLeft() {
		return (nbProjectile > 0) ? true : false;
	}

	public int getNbProjectile() {
		return nbProjectile;
	}

	public Coord[] getLocalisation() {
		return localisation;
	}

	public void setEtat(int[] etat) {
		this.etat = etat;
	}

	public void setLocalistion(Coord[] localisation) {
		this.localisation = localisation;
	}

	public void setProjectile(int nbProjectile) {
		this.nbProjectile = nbProjectile;
	}
	
	public int getPortee() {
		return portee;
	}


}
