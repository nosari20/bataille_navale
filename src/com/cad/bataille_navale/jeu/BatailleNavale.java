package com.cad.bataille_navale.jeu;

import java.awt.Frame;
import java.io.IOException;
import java.util.List;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.factory.AncienPartieBatailleNavaleFactory;
import com.cad.bataille_navale.factory.FuturPartieBatailleNavaleFactory;
import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.bataille_navale.mode.ModeTireBateau;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.DAOUtils;
import com.cad.codesUtils.ModePartie;
import com.cad.codesUtils.bateau.BateauOrientation;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.dao.AbstractDAOFactory;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;
import com.cad.jeu_abstrait.Action.Builder;

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
		partie = AbstractDAOFactory.getAbstractDAOFactory(DAOUtils.XML).getPartieBatailleNavaleDao().load(nomPartie);
	}

	@Override
	public void nouvellePartie(String nomPartie) {
		partie = new ModernePartieBatailleNavaleFactory().CreatePartie();
		((PartieBatailleNavale) partie).setNom(nomPartie);
		((PartieBatailleNavale) partie).placementBateauRandom();
	}

	@Override
	public void nouvellePartie(String nomPartie, Epoque e) {
		Mode mode = new ModeNormal();
		if (e == Epoque.XX) {
			partie = new ModernePartieBatailleNavaleFactory().CreatePartie(mode);
		} else if (e == Epoque.XIX) {
			// Epoque ancien
			partie = new AncienPartieBatailleNavaleFactory().CreatePartie(mode);
		} else {
			partie = new FuturPartieBatailleNavaleFactory().CreatePartie(mode);
		}
		((PartieBatailleNavale) partie).placementBateauRandom();
		((PartieBatailleNavale) partie).setNom(nomPartie);
	}

	@Override
	public void nouvellePartie(String nomPartie, Epoque epoque, ModePartie mp) {
		Mode m;
		if(ModePartie.MODE_NORMAL == mp)
			 m = new ModeNormal();
		else m = new ModeTireBateau();

		if (epoque == Epoque.XX) {
			partie = new ModernePartieBatailleNavaleFactory().CreatePartie(m);
		} else if (epoque == Epoque.XIX) {
			// Epoque ancien
			partie = new AncienPartieBatailleNavaleFactory().CreatePartie(m);
		} else {
			partie = new FuturPartieBatailleNavaleFactory().CreatePartie(m);
		}
		((PartieBatailleNavale) partie).placementBateauRandom();
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
		// return new TireBateau.Builder().partie(partie);
		// return new FrappeOrbitale.Builder().partie(partie);
		return ((PartieBatailleNavale) partie).getMode().actionBuilder();
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

	public int getScore(Joueur j) {
		if (listeJoueurs.indexOf(j) == 0) {
			return ((PartieBatailleNavale) partie).getScoreJ1();
		} else {
			return ((PartieBatailleNavale) partie).getScoreJ2();
		}
	}

	public int getResult() {
		return ((PartieBatailleNavale) partie).getResult();
	}

	public int getStatus() {
		return status;
	}

	@Override
	public void sauvegarder() {
		AbstractDAOFactory.getAbstractDAOFactory(DAOUtils.XML).getPartieBatailleNavaleDao()
				.save(((PartieBatailleNavale) partie));
	}

	public boolean placeBateau(Bateau b, int x, int y) {
		int ox = b.getPosx();
		int oy = b.getPosy();
		b.setPosx(x);
		b.setPosy(y);
		if (((PartieBatailleNavale) partie).placementOk(b)) {
			b.update();
			return true;
		} else {
			b.setPosx(ox);
			b.setPosy(oy);
			return false;
		}
	}

	public boolean turnBateau(Bateau b, BateauOrientation o) {
		BateauOrientation oo = b.getOrientation();
		b.setOrientation(o);
		if (((PartieBatailleNavale) partie).placementOk(b)) {
			b.update();
			return true;
		} else {
			b.setOrientation(oo);
			return false;
		}
	}

	public boolean validePlacement() {
		if (!((PartieBatailleNavale) partie).placementsOk())
			return false;
		((PartieBatailleNavale) partie).play();
		return true;
	}

	public Mode getMode() {
		return ((PartieBatailleNavale) partie).getMode();
	}

}
