package com.cad.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.bataille_navale.joueurs.RandomStrategyComputer;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;
import com.cad.ui.gamescreen.GameScreen;
import com.cad.ui.sprites_repository.SpriteFontRepository;

public class MainGUI {
	
	private Scanner sc;
	private Jeu jeu;
	private List<Joueur> joueurs;
	
	
	private GameUI gameui;
	
	public static void main(String[] args) {
		SpriteFontRepository.getInstance().get('A');
		new MainGUI();
	}
	
	
	public MainGUI(){
		String currentDir = System.getProperty("user.dir") + "\\bataille_navale-motor";
		

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
        System.out.println("Current dir using System:" +currentDir);
        
        
        
        
        createGame(); // TODO
        gameui = new GameUI((BatailleNavale) jeu);       
        
         
		JFrame f = new JFrame("BattleShip");
		f.setSize(800, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setContentPane(gameui);
		f.setVisible(true);	
		
		gameui.lauchGame();
		
		
	}
	
	
	public void createGame(){
		joueurs = new ArrayList<Joueur>();
		joueurs.add(
				new JoueurBatailleNavale(com.cad.codesUtils.Joueur.HUMAN, BatailleNavalleJoueurCote.GAUCHE, "Aschmat"));
		joueurs.add(new JoueurBatailleNavale(BatailleNavalleJoueurCote.DROIT, new RandomStrategyComputer()));
		jeu = new BatailleNavale(joueurs);
		jeu.nouvellePartie("p1");
		jeu = new BatailleNavale(joueurs);
		jeu.nouvellePartie("p1");
	}
	
	


}
