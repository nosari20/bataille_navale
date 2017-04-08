import java.util.ArrayList;
import java.util.List;

import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.joueurs.JoueurBatailleNavale;
import com.cad.jeu_abstrait.Action;
import com.cad.jeu_abstrait.Jeu;
import com.cad.jeu_abstrait.Joueur;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Joueur j1 = new JoueurBatailleNavale();		
		Joueur j2 = new JoueurBatailleNavale();
		
		List<Joueur> joueurs = new ArrayList<Joueur>();
		
		
		Jeu jeu = new BatailleNavale(joueurs);
		
		
		while(jeu.status() != Jeu.Code.FIN){			
			Action a = j1.action();			
			jeu.jouer(j1, a);
			a = j2.jouer();
			jeu.jouer(j2, a);
		}

	}

}
