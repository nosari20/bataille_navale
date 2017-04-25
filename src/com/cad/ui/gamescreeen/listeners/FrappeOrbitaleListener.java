package com.cad.ui.gamescreeen.listeners;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.sound.midi.Soundbank;

import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.jeu_abstrait.Joueur;
import com.cad.ui.gamescreen.GameScreen;

public class FrappeOrbitaleListener implements UIListener {

	private GameScreen gs;
	private BatailleNavale jeu;

	private int[] currentCase;

	private List<Joueur> joueurs;

	public FrappeOrbitaleListener(GameScreen g, BatailleNavale j) {
		gs = g;
		jeu = j;
		joueurs = jeu.getJoueurs();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());	
		if(tab[0] == 2){

			System.out.println("*******");
			((JoueurBatailleNavale) joueurs.get(0)).frappeOrbitale(tab[1],tab[2]);



			int res = ((JoueurBatailleNavale) joueurs.get(0)).jouer();

			if(res == BatailleNavale.Code.IMPOSSIBLE) return;
			/*
				if (res == BatailleNavale.Code.TOUCHE_VIDE) {
					System.out.println("Vide");
				} else if (res == BatailleNavale.Code.TOUCHE) {
					System.out.println("Touche");
				} else if (res == BatailleNavale.Code.DETRUIT) {
					System.out.println("Détruit");
				}
			 */
			gs.untarget();
			
			
			res = ((JoueurBatailleNavale) joueurs.get(1)).jouer();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());	
		if(tab[0] == 2){
			gs.target(new Point(tab[1], tab[2]), 2);
		}

	}

}
