package com.cad.bataille_navale.jeu;

import java.awt.Frame;
import java.io.IOException;
import java.util.List;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.factory.AncienPartieBatailleNavaleFactory;
import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.mode.Mode;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.DAOUtils;
import com.cad.codesUtils.bateau.BateauOrientation;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.dao.AbstractDAOFactory;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class BatailleNavale extends Jeu {

	public final int WIDTH = 12;
	public final int HEIGHT = 12;


	public static class Code extends Jeu.Code {
		public static final int TOUCHE = nextCode();
		public static final int TOUCHE_VIDE = nextCode();
		public static final int VIDE = nextCode();
		public static final int TROP_LOIN = nextCode();
		public final static int CASE_DETRUITE = nextCode();
		public static final int DETRUIT = nextCode();
		public static final int IMPOSSIBLE = nextCode();
		public static final int VICTOIRE_J1 = nextCode();
		public static final int VICTOIRE_J2 = nextCode();
		public static final int DRAW = nextCode();
		public final static int GAME_HEIGHT = 12;
		public final static int GAME_WIDTH = 12;
	}

	public BatailleNavale(List<Joueur> joueurs) {
		super(joueurs);
	}

	@Override
	public void chargerPartie(String nomPartie) {
		partie = AbstractDAOFactory.getAbstractDAOFactory(DAOUtils.XML).getPartieBatailleNavaleDao()
				.load(nomPartie + ".xml");
	}

	@Override
	public void nouvellePartie(String nomPartie) {
		partie = new ModernePartieBatailleNavaleFactory().CreatePartie();
		((PartieBatailleNavale) partie).setNom(nomPartie);
	}

	@Override
	public void setEpoque(String e) {
		this.epoque = e;
	}

	@Override
	public String getEpoque() {
		return this.epoque;
	}

	public void nouvellePartie(String nomPartie, Epoque epoque, Mode m) {
		if (epoque == Epoque.XXI) {
			partie = new ModernePartieBatailleNavaleFactory().CreatePartie();
		} else {
			// Epoque ancien
			partie = new AncienPartieBatailleNavaleFactory().CreatePartie();
		}
		((PartieBatailleNavale) partie).setNom(nomPartie);
	}

	public boolean isFinished() {
		return partie.isPartieFinished();
	}

	public int jouer(Joueur joueur, Action action) {
		int res = ((PartieBatailleNavale) partie).jouer(action);
		this.notifyObservers();
		this.setChanged();
		return res;
	}

	public Action.Builder actionBuilder() {
		//return new TireBateau.Builder().partie(partie);
		return new FrappeOrbitale.Builder().partie(partie);
	}

	public void show() {
		((PartieBatailleNavale) partie).show();
	}

	public List<Bateau> getListOfBateau(BatailleNavalleJoueurCote cote) {
		return ((PartieBatailleNavale) partie).getListOfBateau(cote);
	}

	public int[][] getGrille(BatailleNavalleJoueurCote cote) {
		return ((PartieBatailleNavale) partie).getGrille(cote);
	}
	
	public int getScore(Joueur j){
		if(listeJoueurs.indexOf(j) == 0){
			return ((PartieBatailleNavale) partie).getScoreJ1();
		}else{
			return ((PartieBatailleNavale) partie).getScoreJ2();
		}
	}

	public int getResult() {
		return ((PartieBatailleNavale) partie).getResult();
	}

	public int getStatus(){
		return status;
	}

	@Override
	public void sauvegarder() {
		AbstractDAOFactory.getAbstractDAOFactory(DAOUtils.XML).getPartieBatailleNavaleDao()
		.save(((PartieBatailleNavale) partie));
	}


	public boolean placeBateau(Bateau b, int x, int y){
		int ox = b.getPosx();
		int oy = b.getPosy();		
		b.setPosx(x);
		b.setPosy(y);
		if(((PartieBatailleNavale) partie).placementOk(b)){
			b.update();
			return true;
		}else{
			b.setPosx(ox);
			b.setPosy(oy);
			return false;
		}
	}

	public boolean turnBateau(Bateau b, BateauOrientation o){
		BateauOrientation oo = b.getOrientation();		
		b.setOrientation(o);
		if(((PartieBatailleNavale) partie).placementOk(b)){
			b.update();
			return true;
		}else{
			b.setOrientation(oo);
			return false;
		}
	}

	public boolean validePlacement() {
		((PartieBatailleNavale) partie).play();
		return true;
	}

}
