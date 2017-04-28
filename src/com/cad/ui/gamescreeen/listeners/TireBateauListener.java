package com.cad.ui.gamescreeen.listeners;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.sound.midi.Soundbank;

import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.bataille_navale.actions.FrappeOrbitale;
import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Joueur;
import com.cad.ui.gamescreen.GameScreen;

public class TireBateauListener implements UIListener {

	private GameScreen gs;
	private BatailleNavale jeu;

	private int[] currentCase;

	private List<Joueur> joueurs;

	private Bateau tireur;


	List<Bateau> listBateaux;

	public TireBateauListener(GameScreen g, BatailleNavale j) {
		gs = g;
		jeu = j;
		joueurs = jeu.getJoueurs();
		listBateaux = ((BatailleNavale) jeu).getListOfBateau(BatailleNavalleJoueurCote.GAUCHE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());	
		if(tab[0] == 2){
			if(tireur!=null){
				((JoueurBatailleNavale) joueurs.get(0)).tireBateau(tab[1],tab[2], tireur);
			}else{
				((JoueurBatailleNavale) joueurs.get(0)).frappeOrbitale(tab[1],tab[2]);
			}			
			int res = ((JoueurBatailleNavale) joueurs.get(0)).jouer();
			if(res == BatailleNavale.Code.IMPOSSIBLE) return;		
			if(res != BatailleNavale.Code.TOUCHE_VIDE){
				gs.explode(new Point(tab[1], tab[2]), 2);
			}
			gs.untarget();	
			gs.unselect();
			
			// IA
			res = ((JoueurBatailleNavale) joueurs.get(1)).jouer();
			while(res == BatailleNavale.Code.IMPOSSIBLE)
				res = ((JoueurBatailleNavale) joueurs.get(1)).jouer();
			
			Action last = ((JoueurBatailleNavale) joueurs.get(1)).getLastAction();
			int iax, iay;
			if(tireur != null){
				iax = ((TireBateau) (last)).getPosx();
				iay = ((TireBateau) (last)).getPosy();
			}else{				
				iax = ((FrappeOrbitale) (last)).getPosx();
				iay = ((FrappeOrbitale) (last)).getPosy();
			}
			
			if(res != BatailleNavale.Code.TOUCHE_VIDE){
				gs.explode(new Point(iax, iay), 1);
			}
			tireur=null;
		}else{
			int res;
			Coord c = new Coord(tab[1], tab[2]);
			
			listeBateaux :for (Bateau b : listBateaux) {
				res = b.contientCoord(c);
				if(!b.isDestroyed() && res != -1){
					tireur = b;
					break listeBateaux;
				}

			}
			if(tireur!=null){
				gs.select(new Point(tab[1], tab[2]), 1);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int[] tab = gs.screen2Case(e.getX(), e.getY());	
		gs.target(new Point(tab[1], tab[2]), tab[0]);

	}
	
	

}
