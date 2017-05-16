package com.cad.bataille_navale.cli;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.joueurs.HeartStrategyComputer;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.bataille_navale.joueurs.RandomStrategyComputer;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class Main {
	public static void main(String[] args) {
		new Main();
	}

	private Jeu jeu;
	private List<Joueur> joueurs;
	private Joueur joueur;
	private Scanner sc;

	public Main() {
		sc = new Scanner(System.in);
		solo();
		jeu = new BatailleNavale(joueurs);
		startGame();
	}

	private void startGame() {

		System.out.println("Choisisez une option");
		System.out.println("1 - nouvelle partie");
		System.out.println("2 - charger partie");
		int res = sc.nextInt();
		sc.nextLine();
		if (res == 1) {
			setUp();

		} else {
			System.out.println("Nom de la partie a charger");
			String partie = sc.nextLine();
			jeu.chargerPartie(partie);
		}

		play();
		sc.close();
	}

	private void play() {
		System.out.println("BIENVENUE DANS LE JEU BATAILLE NAVALE");
		int turn = 0;
		int choix = 0;
		start: while (!jeu.isFinished()) {
			// On demande au joueur de selectionner un bateau tirreur
			JoueurBatailleNavale currentJoueur = (JoueurBatailleNavale) joueurs.get(turn);
			List<Bateau> list = ((BatailleNavale) jeu).getListOfBateau(currentJoueur.getCote());
			currentPlay: while (true) {
				if (jeu.isFinished())
					break currentPlay;
				if (currentJoueur.getRole() == com.cad.codesUtils.Joueur.HUMAN) {
					int i = 0;
					System.out.println("Joueur courant : " + currentJoueur.getCote().toString());
					for (Bateau b : list) {
						if (b.isStillProjectileLeft())
							System.out.println(i + " : " + b);
						i++;
					}
					// On demande au joueur de selecltionner l'endroit ou il
					// souhaite
					// tirer
					System.out.println(
							"Selectionner le bateau avec lequel vous voulez tirer ou 7 pour sauvegarder");
					choix = sc.nextInt();
					if (choix == 7)
						jeu.sauvegarder();
					if (choix >= (list.size()) && choix < 0)
						continue start;
					// Le joueurs selectionne l'endroit ou il veut tirer
					int x, y;
					position: while (true) {
						System.out.println("Veuillez choisir la position");
						System.out.println("posx : ");
						x = sc.nextInt();
						if (x > 12 && x < 0)
							continue position;
						System.out.println("posy : ");
						y = sc.nextInt();
						if (y > 12 && y < 0)
							continue position;
						break;
					}
					currentJoueur.tireBateau(x, y, list.get(choix));
				}
				// Le jeu execute le tire
				int jouerResult = currentJoueur.jouer();
				// on affiche le tableau
				((BatailleNavale) jeu).show();
				if (jouerResult != BatailleNavale.Code.TOUCHE && jouerResult != BatailleNavale.Code.DETRUIT) {
					break currentPlay;
				}
			}
			turn = (turn + 1) % 2;
		}
		int gameResult = ((BatailleNavale) jeu).getResult();
		showFinalMessage(gameResult);
		redemarrer: while (true) {
			System.out.println("Voulez vous recommencer ? \n 1 - oui \n 2 - non ");
			choix = sc.nextInt();
			if (choix != 1 && choix != 2)
				continue redemarrer;
			else
				break redemarrer;
		}

		if (choix == 1)
			startGame();
		sc.close();
	}

	private void showFinalMessage(int gameResult) {
		if (gameResult == BatailleNavale.Code.DRAW)
			System.out.println("MATCH NULL BOOOOOO");
		else if (gameResult == BatailleNavale.Code.VICTOIRE_J1)
			System.out.println(joueurs.get(1).getNom() + " a gagn�");
	}

	private void setUp() {
		jeu.nouvellePartie("partie1");
		((BatailleNavale) jeu).show();
	}

	private void solo() {
		joueurs = new ArrayList<Joueur>();
		joueurs.add(
				new JoueurBatailleNavale(com.cad.codesUtils.Joueur.HUMAN, BatailleNavalleJoueurCote.GAUCHE, "Aschmat"));
		joueurs.add(new JoueurBatailleNavale(BatailleNavalleJoueurCote.DROIT, new HeartStrategyComputer()));
	}
}
