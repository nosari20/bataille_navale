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

import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.jeu_abstrait.Jeu;
import com.cad.ui.gamescreen.GameScreen;

@SuppressWarnings("serial")
public class GameUI extends JPanel implements Observer{

	private BatailleNavale jeu;
	
	private GameScreen gs;
	
	private JLabel score;
	
	public GameUI(BatailleNavale j) {
		jeu = j;
		
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
					((JButton) e.getSource()).setEnabled(false);;
				}
				
			}
		});
		
		score = new JLabel("Score : 0");
		board.add(score);
		
	}	
	
	
	
	public void lauchGame() {			
			gs.run();	
	}
	
	
	
	public void initGame(){
		gs = new GameScreen(jeu);			
	}



	@Override
	public void update(Observable o, Object arg) {
		this.score.setText("Score : " + jeu.getResult());
		
	}

}
