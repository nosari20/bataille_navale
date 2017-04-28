package com.cad.bataille_navale.joueurs;

import java.util.List;
import java.util.Random;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;

public class CrossStrategyComputer implements StrategyComputer {

	private boolean rand = true;
	private Coord lastTouched;
	private Coord lastShot;

	private int[] cross;
	private Coord[] crossDIr = {
			new Coord(1,0),
			new Coord(-1,0),
			new Coord(0,1),
			new Coord(0,-1),
	};
	private int crossIndex;
	
	private boolean finded = false;
	
	private boolean reversSearch = false;

	public CrossStrategyComputer() {
		initCross();
	}

	@Override
	public Coord choosePoint(final int[][] grille) {	
		Coord res = new Coord(0, 0);
		
		
		if(lastShot ==null){
			// si on a pa tirré
			res = rand(grille);
		}else{		
			// si on a déja tirré
			
			if(rand){
				//si on est en random				
				if(grille[lastShot.x][lastShot.y] == BatailleNavale.Code.TOUCHE){
					// si on a touché en random
					res = searchOrient(grille);
					
				}else{
					// si on a pas touché radom
					res = rand(grille);					
				}
			}else{
				// si en croix
				
				if(lastTouched != null){
					// si on a trouvé
					if(grille[lastShot.x][lastShot.y] == BatailleNavale.Code.TOUCHE){
						// si on a touché en random
						res = continueOrient(grille, false);
						
					}else{
						// si on a pas touché radom
						if(!reversSearch){
							// si on est dans la direction normal
							continueOrient(grille, true);
						}else{
							// sinon
							res = rand(grille);		
						}
									
					}
				}else{
					// si on a pas trouvé
					res = searchOrient(grille);
				}
				
			}
			
		}
		
		
		
		
		lastShot = res;
		System.out.println("("+res.x+", "+res.y+")");
		return res;
	}


	public void initCross(){
		crossIndex = 0;
		cross = new int[4];	
		finded = false;
		reversSearch = false;
		
	}
	
	private Coord rand(int[][] grille){
		Random r = new Random();
		rand = true;
		while (true) {
			int x = r.nextInt(grille.length);
			int y = r.nextInt(grille[0].length);
			if (grille[x][y] == BatailleNavale.Code.TOUCHE || grille[x][y] == BatailleNavale.Code.VIDE)
				return new Coord(x, y);
		}
	}
	
	private Coord searchOrient(int[][] grille){
		Random r = new Random();
		rand = true;
		while (true) {
			int x = r.nextInt(grille.length);
			int y = r.nextInt(grille[0].length);
			if (grille[x][y] == BatailleNavale.Code.TOUCHE || grille[x][y] == BatailleNavale.Code.VIDE)
				return new Coord(x, y);
		}
	}
	
	private Coord continueOrient(int[][] grille, boolean reverse){
		Random r = new Random();
		rand = true;
		while (true) {
			int x = r.nextInt(grille.length);
			int y = r.nextInt(grille[0].length);
			if (grille[x][y] == BatailleNavale.Code.TOUCHE || grille[x][y] == BatailleNavale.Code.VIDE)
				return new Coord(x, y);
		}
	}




	@Override
	public Bateau chooseBateauTirreur(List<Bateau> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
