package com.cad.bataille_navale.joueurs;

import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.FrappeOrbitale.Builder;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class JoueurBatailleNavale extends Joueur {

	public JoueurBatailleNavale() {
	}


	public int frappeOrbitale(int x, int y) {
		return -1;
	}

	public int tireBateau(int x, int y) {
		return -1;
	}


	public int jouer() {
		BatailleNavale j = (BatailleNavale)jeu;
		
		if(j.mode() instanceof ModeNormal){
			return jouerNomal();
		}

		return 0;
	}


	private int jouerNomal() {
		BatailleNavale j = (BatailleNavale)jeu;
		FrappeOrbitale.Builder b = (Builder) j.actionBuilder();
		return b.pos(5, 5).build().execute();
	}

}
