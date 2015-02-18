package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Modele.Monument;
import Vue.Fenetre;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Controleur qui permet de remplir les combobox avec la bonne cat�gorie
 * @author Groupe L
 *
 */
public class ControleurCategorieContrainte implements ActionListener {

	private Fenetre fen;
	
	public ControleurCategorieContrainte(Fenetre f) {
		fen = f;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String categorieSelect="";
		JComboBox<String> jcb = (JComboBox<String>)arg0.getSource();
		if (jcb.equals(fen.getBoxType2())) {
			categorieSelect = fen.getTypeCategorie2();
		}
		else if (jcb.equals(fen.getBoxType3())) {
			categorieSelect = fen.getTypeCategorie3();
		}
		System.out.println(categorieSelect);
		ArrayList<Monument> monuments;
		try {
			monuments = fen.monumentParCategorie().get(categorieSelect);
			//si la catégorie a des monuments
			if (monuments != null){
				ControleurMonumentSelected.checkColor(fen);
				fen.supprimeEtAjoute(monuments);
				
			}
		} catch (JsonIOException | JsonSyntaxException | IOException e1) {
			e1.printStackTrace();
		}
		if (jcb.equals(fen.getBoxType2())) {
			System.out.println("hello");
			fen.remplirComboBoxContrainte(1,categorieSelect);
		}
		else if (jcb.equals(fen.getBoxType3())) {
			System.out.println("hello22");
			fen.remplirComboBoxContrainte(2,categorieSelect);
		}
		
		fen.removeMarkers();
		ControleurDebutEtFin.checkColorDebArr(fen);
	}
}
