package com.cad.bataille_navale.joueurs;

import java.util.List;
import java.util.Random;

import javax.swing.LayoutStyle;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;

public class CrossStrategyComputer implements StrategyComputer {

	private boolean rand = true;
	private Coord target;
	private Coord lastShot;

	private int[] cross;
	private Coord[] crossDIr = {
			new Coord(1,0),
			new Coord(-1,0),
			new Coord(0,1),
			new Coord(0,-1),
	};
	private int crossIndex;
	
	private boolean searching = false;
	
	private boolean reverse = false;
	
	private boolean finded = false;
	
	private Coord lastTouched;
	
	private Coord checkLastShot;
	
	
	private int loop = 0;

	public CrossStrategyComputer() {
		initCross();
	}

	@Override
	public Coord choosePoint(final int[][] grille) {	
		Coord res;
		
		// prevent infinite loop
		if(lastShot != null)
		if(checkLastShot !=null){
			if(checkLastShot.equals(lastShot)){
				
					loop++;
					if(loop > 10){
						loop = 0;
						return use(rand(grille), grille);
					}
				
			}else{
				loop = 0;
				checkLastShot = new Coord(lastShot.x, lastShot.y);
			}
		}else{
			checkLastShot = new Coord(lastShot.x, lastShot.y);
		}
		System.out.println("Loop : "+loop);
		
		
		System.out.println("********************************");
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				System.out.print("_"+grille[i][j]);
			}
			System.out.println("");
		}
		
		if(lastShot == null){
			// si on a pa tirr�
			System.out.println("premier tir");
			lastShot = rand(grille);
			return lastShot;
		}else{	
			System.out.println("else");
			res = new Coord(lastShot.x, lastShot.y);
		}
		
		int c = grille[lastShot.x][lastShot.y];
		if (c == BatailleNavale.Code.TOUCHE_VIDE) {
			System.out.println("Vide");
		} else if (c == BatailleNavale.Code.TOUCHE) {
			System.out.println("Touche");
		} else if (c == BatailleNavale.Code.DETRUIT) {
			System.out.println("D�truit");
		} else if (c == BatailleNavale.Code.CASE_DETRUITE) {
			System.out.println("Case D�truite");
		} else if (c == BatailleNavale.Code.TROP_LOIN) {
			System.out.println("Trop loin");
		} else if (c == BatailleNavale.Code.VIDE) {
			System.out.println("Vide");
		} else if (c == BatailleNavale.Code.VIDE) {
			System.out.println("Vide");
		} else if (c == BatailleNavale.Code.IMPOSSIBLE) {
			System.out.println("Impossible");
		} else{
			System.out.println(BatailleNavale.Code.TOUCHE);
			System.out.println(res);
			System.out.println("Error");
		}
		
		if(
				((grille[lastShot.x][lastShot.y] == BatailleNavale.Code.TOUCHE_VIDE ||
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.VIDE ||
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.IMPOSSIBLE) &
				!finded &
				!searching) ||
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.DETRUIT||
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.VIDE
				
		){
			
			System.out.println("random");
			return use(rand(grille), grille);
		}
		
		if(
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.TOUCHE &
				!searching &
				!finded
		){
			System.out.println("start search");
			lastTouched = new Coord(lastShot.x, lastShot.y);
			return use(searching(grille), grille);
		}
		
		if(
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.TOUCHE_VIDE &
				searching
		){
			System.out.println("keep searching");
			return use(searching(grille), grille);
			
		}
		
		if(
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.TOUCHE &
				searching
		){
			System.out.println("start destroying");
			return use(destroying(grille), grille);
		}
		
		if(
				finded
		){
			System.out.println("keep destroying");
			return use(destroying(grille), grille);
		}
	
		
		
		
		
		return res;
	}

	public Coord use(Coord res, int[][] grille){
		if(res.x < 0) res.x = 0;
		if(res.y < 0) res.y = 0;
		if(res.x >= grille.length) res.x = grille.length-1;
		if(res.y >= grille[0].length) res.y = grille[0].length-1;
		
		lastShot = res;
		
		System.out.println(res.x+" _ "+res.y);
		return res;
	}

	public void initCross(){
		crossIndex = -1;
		cross = new int[4];	
		searching = false;
		reverse = false;
		finded = false;
		
	}
	
	private Coord rand(int[][] grille){
		Random r = new Random();
		initCross();
		rand = true;
		int x = r.nextInt(grille.length);
		int y = r.nextInt(grille[0].length);
		return new Coord(x, y);

	}
	
	private Coord searching(int[][] grille){
		searching = true;
		if(crossIndex < crossDIr.length){
			crossIndex++;
			return new Coord(lastTouched.x+crossDIr[crossIndex].x, lastTouched.y+crossDIr[crossIndex].y);
		}else{
			return rand(grille);
		}
	}
	
	private Coord destroying(int[][] grille){
		finded = true;
		searching = false;
		if(
				grille[lastShot.x][lastShot.y] != BatailleNavale.Code.CASE_DETRUITE
		){
			System.out.println("KEEP");
			return lastShot;
		}
		
		if(
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.CASE_DETRUITE
		){
			System.out.println("CHANGE");
			if(reverse){
				
				if(
						lastShot.x-crossDIr[crossIndex].x < 0 ||
						lastShot.x-crossDIr[crossIndex].x >= grille.length ||
						lastShot.y-crossDIr[crossIndex].y < 0 ||
						lastShot.y-crossDIr[crossIndex].y >= grille[0].length
						
				){
					return rand(grille);
				}else{
					return new Coord(lastShot.x-crossDIr[crossIndex].x, lastShot.y-crossDIr[crossIndex].y);
				}
				
			}else{
				if(
						lastShot.x+crossDIr[crossIndex].x < 0 ||
						lastShot.x+crossDIr[crossIndex].x >= grille.length ||
						lastShot.y+crossDIr[crossIndex].y < 0 ||
						lastShot.y+crossDIr[crossIndex].y >= grille[0].length
						
				){
					reverse = true;
					return new Coord(lastShot.x-crossDIr[crossIndex].x, lastShot.y-crossDIr[crossIndex].y);
				}else{
					return new Coord(lastShot.x+crossDIr[crossIndex].x, lastShot.y+crossDIr[crossIndex].y);
				}
			}
		}
		if(
				grille[lastShot.x][lastShot.y] == BatailleNavale.Code.VIDE
		){
			
			System.out.println("END");
			if(reverse){
				return rand(grille);
			}else{
				reverse = true;
				return new Coord(lastShot.x-crossDIr[crossIndex].x, lastShot.y-crossDIr[crossIndex].y);
			}
		}
		return rand(grille);
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