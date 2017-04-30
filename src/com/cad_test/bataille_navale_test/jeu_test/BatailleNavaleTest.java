/**
 * 
 */
package com.cad_test.bataille_navale_test.jeu_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.bataille_navale.joueurs.RandomStrategyComputer;
import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.bataille_navale.mode.ModeTireBateau;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.epoque.Epoque;
import com.cad.jeu_abstrait.Joueur;

/**
 * @author WorkStation
 *
 */
public class BatailleNavaleTest {

	private BatailleNavale jeu;
	private List<Joueur> joueurs;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		joueurs = new ArrayList<Joueur>();
		joueurs.add(
				new JoueurBatailleNavale(com.cad.codesUtils.Joueur.HUMAN, BatailleNavalleJoueurCote.GAUCHE, "Aschmat"));
		joueurs.add(new JoueurBatailleNavale(BatailleNavalleJoueurCote.DROIT, new RandomStrategyComputer()));

		jeu = new BatailleNavale(joueurs);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.cad.bataille_navale.jeu.BatailleNavale#nouvellePartie(java.lang.String)}.
	 */
	@Test
	public void testNouvellePartieString() {
		String nomPartie = "Partie";
		jeu.nouvellePartie(nomPartie);
		PartieBatailleNavale partie = (PartieBatailleNavale) jeu.currentPartie();
		assertEquals(partie.getNom(), nomPartie);
		assertNotNull(partie.getBateauJ1());
		assertNotNull(partie.getBateauJ2());
		assertTrue(partie.getBateauJ1().size() == 5);
		assertTrue(partie.getBateauJ2().size() == 5);

	}

	/**
	 * Test method for
	 * {@link com.cad.bataille_navale.jeu.BatailleNavale#nouvellePartie(java.lang.String, com.cad.codesUtils.epoque.Epoque)}.
	 */
	@Test
	public void testNouvellePartieStringEpoque() {
		String nomPartie = "Partie";
		Epoque epoque = Epoque.XIX;
		jeu.nouvellePartie(nomPartie, epoque);
		PartieBatailleNavale partie = (PartieBatailleNavale) jeu.currentPartie();
		assertEquals(partie.getNom(), nomPartie);
		assertNotNull(partie.getBateauJ1());
		assertNotNull(partie.getBateauJ2());
		assertTrue(partie.getBateauJ1().size() == 5);
		assertTrue(partie.getBateauJ2().size() == 5);
		assertTrue(partie.getEpoque() == epoque);
	}

	/**
	 * Test method for
	 * {@link com.cad.bataille_navale.jeu.BatailleNavale#isFinished()}.
	 */
	@Test
	public void testIsFinishedProjectileGone() {
		String nomPartie = "Partie";
		Epoque epoque = Epoque.XIX;
		jeu.nouvellePartie(nomPartie, epoque);
		PartieBatailleNavale partie = (PartieBatailleNavale) jeu.currentPartie();
		List<Bateau> list = partie.getBateauJ1();
		for (Bateau b : list)
			b.setProjectile(0);
		assertTrue(jeu.isFinished());
	}

	/**
	 * Test method for
	 * {@link com.cad.bataille_navale.jeu.BatailleNavale#isFinished()}.
	 */
	@Test
	public void testIsFinishedAllDestroyed() {
		String nomPartie = "Partie";
		Epoque epoque = Epoque.XIX;
		jeu.nouvellePartie(nomPartie, epoque);
		PartieBatailleNavale partie = (PartieBatailleNavale) jeu.currentPartie();
		List<Bateau> list = partie.getBateauJ1();
		for (Bateau b : list) {
			int[] etat = b.getEtat();
			for (int i = 0; i < etat.length; i++)
				etat[i] = 0;
		}

		assertTrue(jeu.isFinished());
	}

	/**
	 * Test method for
	 * {@link com.cad.bataille_navale.jeu.BatailleNavale#nouvellePartie(java.lang.String, com.cad.codesUtils.epoque.Epoque, com.cad.bataille_navale.mode.Mode)}.
	 */
	@Test
	public void testNouvellePartieStringEpoqueMode() {
		String nomPartie = "Partie";
		Epoque epoque = Epoque.XIX;
		Mode mode = new ModeTireBateau();
		jeu.nouvellePartie(nomPartie, epoque, mode);
		PartieBatailleNavale partie = (PartieBatailleNavale) jeu.currentPartie();
		assertEquals(partie.getNom(), nomPartie);
		assertNotNull(partie.getBateauJ1());
		assertNotNull(partie.getBateauJ2());
		assertTrue(partie.getBateauJ1().size() == 5);
		assertTrue(partie.getBateauJ2().size() == 5);
		assertTrue(partie.getEpoque() == epoque);
		assertTrue(partie.getMode() instanceof ModeTireBateau);
	}

	/**
	 * Test method for {@link
	 * com.cad.bataille_navale.joueurs.JoueurBatailleNavale#tireBateau(int, int,
	 * com.cad.bataille_navale.bateaux)
	 */
	@Test
	public void testBateauTirreur() {
		String nomPartie = "Partie";
		Epoque epoque = Epoque.XIX;
		int x = 0;
		int y = 0;
		Mode mode = new ModeTireBateau();
		BatailleNavalleJoueurCote cote = BatailleNavalleJoueurCote.GAUCHE;
		jeu.nouvellePartie(nomPartie, epoque, mode);
		PartieBatailleNavale partie = (PartieBatailleNavale) jeu.currentPartie();
		List<Bateau> list = partie.getBateauJ1();
		JoueurBatailleNavale joueur = (JoueurBatailleNavale) joueurs.get(0);
		joueur.tireBateau(x, y, list.get(0));
		joueur.jouer();
		BatailleNavalleJoueurCote jCote = joueur.getCote();
		if (jCote == BatailleNavalleJoueurCote.GAUCHE)
			cote = BatailleNavalleJoueurCote.DROIT;
		int[][] grille = partie.getGrille(cote);
		assertTrue(grille[x][y] == BatailleNavale.Code.VIDE);

	}

}
