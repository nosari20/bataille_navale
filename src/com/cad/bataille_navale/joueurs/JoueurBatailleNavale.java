package com.cad.bataille_navale.joueurs;


import java.util.List;
import java.util.Iterator;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class JoueurBatailleNavale implements Joueur {
	private BatailleNavale jeu;
	private com.cad.codesUtils.Joueur role;
	private Action.Builder b;
	private BatailleNavalleJoueurCote cote;
	private int joueurResult;
	private StrategyComputer strategy;

	public JoueurBatailleNavale(com.cad.codesUtils.Joueur role, BatailleNavalleJoueurCote cote, String nom) {
		this.role = role;
		this.cote = cote;
	}

	public JoueurBatailleNavale(BatailleNavalleJoueurCote cote, StrategyComputer strategy) {
		this(com.cad.codesUtils.Joueur.COMPUTER, cote, com.cad.codesUtils.Joueur.COMPUTER.toString());
		this.strategy = strategy;
	}

	public int jouer() {
		if (role == com.cad.codesUtils.Joueur.COMPUTER) {
			Coord c = strategy.choosePoint(jeu.getGrille(BatailleNavalleJoueurCote.GAUCHE));
			if(jeu.getMode() instanceof ModeNormal){
				frappeOrbitale(c.x, c.y);//TODO
			}else{
				
				Bateau tirreur = strategy.chooseBateauTirreur(jeu.getListOfBateau(cote));
				tireBateau(c.x, c.y, tirreur);
			}
		}
		return jeu.jouer(this, b.build());

	}

	public void setStrategy(StrategyComputer strategy) {
		this.strategy = strategy;
	}

	public void tireBateau(int x, int y, Bateau tirreur) {
		b = jeu.actionBuilder();
		if(b instanceof TireBateau.Builder)
		(((TireBateau.Builder) b).pos(x, y)).cote(this.cote).tirreur(tirreur);
	}
	
	public void frappeOrbitale(int x, int y) {
		b = jeu.actionBuilder();
		if(b instanceof FrappeOrbitale.Builder)
		((FrappeOrbitale.Builder) b).pos(x, y).cote(this.cote);
	}

	@Override
	public void setJeu(Jeu j) {
		this.jeu = (BatailleNavale) j;
	}

	public BatailleNavalleJoueurCote getCote() {
		return cote;
	}

	public com.cad.codesUtils.Joueur getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Action getLastAction(){
		return b.build();
	}

}
