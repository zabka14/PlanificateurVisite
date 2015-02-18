package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Modele.Monument;
import Vue.CheckBox;
import Vue.Fenetre;

/**
 * Controleur qui permet d'afficher les monuments correspondant � la cat�gorie s�l�ctionn� dans l'onglet choix monument et garder la couleur rouge des monuments choisis
 * @author Groupe L
 *
 */
public class CategorieSelectListener implements ActionListener{

	private Fenetre fen;
	
	public CategorieSelectListener(Fenetre f) {
		this.fen = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		    String categorieSelect = fen.getTypeCategorie();
			//recupere les monuments de la catégorie, affiche un marqueur pour seulement ces monuments
			ArrayList<Monument> monuments;
			try {
				monuments = fen.monumentParCategorie().get(categorieSelect);
				//si la catégorie a des monuments
				if (monuments != null){
					fen.supprimeEtAjoute(monuments);
					
				}
			} catch (JsonIOException | JsonSyntaxException | IOException e1) {
				e1.printStackTrace();
			}
			fen.showCategorie(categorieSelect);	
			
			/* Couleur des monuments choisis */
			ControleurMonumentSelected.checkColor(fen);
	}
}