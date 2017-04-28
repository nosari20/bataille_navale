package com.cad.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cad.bataille_navale.actions.TireBateau;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;
import com.cad.motor2d.sprites.Animation;
import com.cad.ui.gamescreen.GameScreen;
import com.cad.ui.sprites_repository.SpriteExplostionRepository;

@SuppressWarnings("serial")
public class GameUI extends JPanel implements Observer{

	private BatailleNavale jeu;
	
	private GameScreen gs;
	
	private JLabel score;
	
	private Joueur j1;
	
	
	
	public GameUI(BatailleNavale j) {
		jeu = j;
		
		j1 = jeu.getJoueurs().get(0);
		
		this.setLayout(new BorderLayout());
		this.initGame();

		this.add(gs, BorderLayout.CENTER);
			
		
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(1, 2));
		this.add(board ,BorderLayout.SOUTH);
		
		
		JButton placeOK = new JButton("Valider le placement");
		board.add(placeOK);
		placeOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jeu.validePlacement()){
					gs.removePlacementListener();
					gs.addTireListener();
					gs.write("JOUEZ", 1500);
					((JButton) e.getSource()).setEnabled(false);
				}
				
			}
		});
		
		score = new JLabel("Score : 0");
		board.add(score);
		
		jeu.addObserver(this);
		
		
		
		
	}	
	
	
	
	public void lauchGame() {			
			gs.run();	
	}
	
	
	
	public void initGame(){
		gs = new GameScreen(jeu);			
	}



	@Override
	public void update(Observable o, Object arg) {
		this.score.setText("Score : " + ((PartieBatailleNavale) jeu.currentPartie()).getScoreJ1());
		
		if(jeu.currentPartie().getStatus() == BatailleNavale.Code.FIN){
			System.out.println("FIN");
		}
		if(jeu.currentPartie().getStatus() == BatailleNavale.Code.DEBUT){
			System.out.println("DEBUT");
		}
		if(jeu.currentPartie().getStatus() == BatailleNavale.Code.EN_COURS){
			System.out.println("EN_COURS");
		}
		/*
		if(jeu.getStatus() == BatailleNavale.Code.FIN){
			System.out.println("FIN");
		}
		if(jeu.getStatus() == BatailleNavale.Code.FIN){
			System.out.println("FIN");
		}
		*/
		

	
		if(jeu.currentPartie().getStatus() == BatailleNavale.Code.FIN){
			fin();
		}
		

		
	}
	
	private void fin(){
		gs.stop();
		int result = jeu.getResult();		
		if(result == BatailleNavale.Code.VICTOIRE_J1){
			this.score.setText("Victoire Score : " + jeu.getScore(j1));
		}else if(result == BatailleNavale.Code.VICTOIRE_J2){
			this.score.setText("Défaite Score : " + jeu.getScore(j1));
		}else{
			this.score.setText("Égalité Score : " + jeu.getScore(j1));
		}
		
		
	}

}
