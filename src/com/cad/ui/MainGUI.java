package com.cad.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.joueurs.CrossStrategyComputer;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.bataille_navale.joueurs.RandomStrategyComputer;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;
import com.cad.ui.gamescreen.GameScreen;
import com.cad.ui.gamescreen.GameStartScreen;
import com.cad.ui.sprites_repository.SpriteFontRepository;

public class MainGUI {
	
	private Scanner sc;
	private Jeu jeu;
	private List<Joueur> joueurs;

	
	private GameUI gameui;
	
	public static void main(String[] args) {
		new GameStartScreen();
	}
	
	
	public MainGUI(String namePartie,Epoque epoque){
		SpriteFontRepository.getInstance().get('A');
		String currentDir = System.getProperty("user.dir") + "\\bataille_navale-motor";


		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}



        System.out.println("Current dir using System:" +currentDir);

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Fichier");
		JMenuItem menu = new JMenuItem("Menu");
		JMenuItem leave = new JMenuItem("Quitter");


        createGame(namePartie,epoque); // TODO
        gameui = new GameUI((BatailleNavale) jeu);


		JFrame f = new JFrame(namePartie);
		f.setSize(800, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.getContentPane().add(gameui, BorderLayout.CENTER);

		file.add(menu);
		menu.addActionListener(event ->{
			new GameStartScreen();
			f.dispose();
		});
		file.addSeparator();

		leave.addActionListener(event ->{
			System.exit(0);
		});
		file.add(leave);

		menuBar.add(file);
		f.setJMenuBar(menuBar);

		f.setVisible(true);

		gameui.lauchGame();


	}


	public void createGame(String namePartie,Epoque epoque){
		joueurs = new ArrayList<Joueur>();
		joueurs.add(
				new JoueurBatailleNavale(com.cad.codesUtils.Joueur.HUMAN, BatailleNavalleJoueurCote.GAUCHE, "Aschmat")
				);
		joueurs.add(
				new JoueurBatailleNavale(BatailleNavalleJoueurCote.DROIT, new CrossStrategyComputer())
				);
		jeu = new BatailleNavale(joueurs);

		jeu.nouvellePartie(namePartie,epoque);


	}

}
