package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import Modele.Monument;
import Vue.Fenetre;

/**
 * Controleur qui permet de changer les couleurs du monument de depart/arrive lorsque l'utilisateur choisis un monument
 * @author Groupe L
 *
 */
public class ControleurDebutEtFin implements ItemListener {

	private Fenetre fen;
	
	public ControleurDebutEtFin(Fenetre f) {
		fen = f;
	}
	
	public static void checkColorDebArr(Fenetre fen) {
		// Remettre tout les monuments en jaune
		ArrayList<Monument> al = fen.getMonumentsCategorieContrainte();
		for(Monument m : al) {
			fen.setMarkersColor(m, Color.YELLOW);
		}
		// Mettre monument d'arrivé et de départ en bleu/vert
		Monument dep = fen.getMonumentDepart();
		Monument arr = fen.getMonumentArrive();
		if(dep.getNom().equals(arr.getNom())&&(fen.checkboxDebutSelect())&&(fen.checkboxArrSelect())) {
			fen.setMarkersColor(dep, Color.CYAN);
		}
		else {
			if(fen.checkboxDebutSelect())
				fen.setMarkersColor(dep, Color.BLUE);
			if(fen.checkboxArrSelect())
				fen.setMarkersColor(arr, Color.GREEN);
		}	
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(Fenetre.sauveNousStp)
			ControleurDebutEtFin.checkColorDebArr(fen);		
	}
}