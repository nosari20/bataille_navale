package com.cad.ui.gamescreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.codesUtils.bateau.BateauOrientation;

public class PlacementListener implements MouseListener, MouseMotionListener {

	private GameScreen gs;

	private int[] currentCase;

	public PlacementListener(GameScreen g) {
		gs = g;
	}

	public void mouseClicked(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());	
		if(tab[0] == 1){
			Bateau b = gs.getPartie().getBateauJ1(tab[1], tab[2]);
			if(b!=null){
				if(b.getOrientation() == BateauOrientation.HORIZONTAL){
					gs.getJeu().turnBateau(b, BateauOrientation.VERTICAL);
				}else{
					gs.getJeu().turnBateau(b, BateauOrientation.HORIZONTAL);
				}
				b.update();
			}
		}
	}

	public void mouseEntered(MouseEvent e) { // TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) { // TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) { // TODO Auto-generated method stub
		currentCase = gs.screen2Case(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent arg0) { // TODO Auto-generated method
		currentCase = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if(currentCase != null){
			if(currentCase[0] == 1){
				Bateau b = gs.getPartie().getBateauJ1(currentCase[1], currentCase[2]);

				int[] tab = gs.screen2Case(e.getX(), e.getY());	
				if(tab[0] == 1){

					if(b!=null){
						gs.getJeu().placeBateau(b, tab[1], tab[2]);
						currentCase = tab;
					}
				}
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
