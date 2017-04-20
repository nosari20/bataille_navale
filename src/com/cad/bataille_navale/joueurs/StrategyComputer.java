package com.cad.bataille_navale.joueurs;

import java.util.List;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;

public interface StrategyComputer {
	public Coord choosePoint(int[][] grille);

	public Bateau chooseBateauTirreur(List<Bateau> list);
}
