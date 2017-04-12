package com.cad.ui.gamescreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.cad.bataille_navale.bateaux.Bateau;

public class Listener implements MouseListener, MouseMotionListener {

	private GameScreen gs;

	private int[] currentCase;

	private int ppux; private int ppuy;

	public Listener(GameScreen g) {
		gs = g;
	}

	public void mouseClicked(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());		
		String c = gs.getPartie().getCaseJ1(tab[1], tab[2]).split(";")[0];

		System.out.println(gs.getPartie().getCaseJ1(tab[1], tab[2]));
		if(!c.equals("")){	
			
			Bateau b = gs.getPartie().getBateauJ1(c);
			gs.getPartie().updateBateauOrientaion(b, !b.isOrientation());
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
				
				
				String c = gs.getPartie().getCaseJ1(currentCase[1], currentCase[2]).split(";")[0];


				if(!c.equals("")){
					int[] tab = gs.screen2Case(e.getX(), e.getY());					
					if(tab[0] == currentCase[0]){
						Bateau b = gs.getPartie().getBateauJ1(c);
						gs.getPartie().updateBateauPos(b,tab[1], tab[2]);

						currentCase[1] = b.getPosx();
						currentCase[2] = b.getPosy();

					}

				}
			}else{
				String c = gs.getPartie().getCaseJ2(currentCase[1], currentCase[2]);
			}




		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
