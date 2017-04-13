package com.cad.ui.gamescreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.cad.bataille_navale.bateaux.Bateau;

public class TireListener implements MouseListener {

	private GameScreen gs;

	private int[] currentCase;

	public TireListener(GameScreen g) {
		gs = g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());	
		if(tab[0] == 2){
			String c = gs.getPartie().getCaseJ2(tab[1], tab[2]).split(";")[0];

			if(!c.equals("")){	

				Bateau b = gs.getPartie().getBateauJ2(c);
				System.out.println(b.getNom());
				//TODO
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
