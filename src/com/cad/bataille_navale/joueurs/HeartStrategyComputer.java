package com.cad.bataille_navale.joueurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;

/**
 * Stratégie de tir esthétique qui tir en dessinant un coeur sur la mer.
 * Une fois le coeur dessiné, elle tir au hasard.
 * @author Alexis, Jofrey, Quentin
 */
public class HeartStrategyComputer implements StrategyComputer {
	
	private Random rand = new Random();
	private int[][] heart = new int[][]{
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,1,1,0,0,0,0,1,1,0,0},
		{0,1,1,1,1,0,0,1,1,1,1,0},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{0,1,1,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,1,1,0},
		{0,0,1,1,1,1,1,1,1,1,0,0},
		{0,0,0,1,1,1,1,1,1,0,0,0},
		{0,0,0,0,1,1,1,1,0,0,0,0},
		{0,0,0,0,0,1,1,0,0,0,0,0}
		};
	
	@Override
	public Coord choosePoint(final int[][] grille) {
		
		// Listing heart points
		ArrayList<Coord> targets = new ArrayList<>();		
		for (int y = 0; y < BatailleNavale.Code.GAME_HEIGHT; y++){
			for (int x = 0; x < BatailleNavale.Code.GAME_WIDTH; x++){
				if (heart[y][x] == 1 && (grille[x][y] == BatailleNavale.Code.TOUCHE || grille[x][y] == BatailleNavale.Code.VIDE)){
					targets.add(new Coord(x, y));
				}
			}
		}
		
		// If there's at least one heart point : shooting at it (randomly)
		if (!targets.isEmpty()) return pickTarget(targets);
			
		// Else, listing and shooting at a random point outside the heart
		for (int y = 0; y < BatailleNavale.Code.GAME_HEIGHT; y++){
			for (int x = 0; x < BatailleNavale.Code.GAME_WIDTH; x++){
				if (heart[y][x] == 0 && (grille[x][y] == BatailleNavale.Code.TOUCHE || grille[x][y] == BatailleNavale.Code.VIDE)){
					targets.add(new Coord(x, y));
				}
			}
		}
		
		return pickTarget(targets);
	}

	private Coord pickTarget(ArrayList<Coord> targets){
		return targets.get(rand.nextInt(targets.size()));
	}
	
	@Override
	public Bateau chooseBateauTirreur(List<Bateau> list) {
		result: while (true) {
			int x = rand.nextInt(list.size());

			return list.get(x);
		}

	}

}
