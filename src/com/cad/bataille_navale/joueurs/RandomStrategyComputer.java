package com.cad.bataille_navale.joueurs;

import java.util.List;
import java.util.Random;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;

public class RandomStrategyComputer implements StrategyComputer {

	@Override
	public Coord choosePoint(int[][] grille) {
		Random r = new Random();
		result: while (true) {
			int x = r.nextInt(BatailleNavale.Code.GAME_WIDTH);
			int y = r.nextInt(BatailleNavale.Code.GAME_HEIGHT);
			if (grille[x][y] == BatailleNavale.Code.TOUCHE || grille[x][y] == BatailleNavale.Code.VIDE)
				return new Coord(x, y);
		}
	}

	@Override
	public Bateau chooseBateauTirreur(List<Bateau> list) {
		Random r = new Random();
		result: while (true) {
			int x = r.nextInt(list.size());

			return list.get(x);
		}

	}

}
