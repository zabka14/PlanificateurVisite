package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONException;

import APIJson.APIDirectionJson;
import Modele.Chemin;
import Modele.Monument;
import Modele.Parcours;
import Vue.Fenetre;

/**
 * Controleur qui permet de generer un parcours à partir des monuments que l'utilisateur aura choisit
 * @author Groupe L
 */
public class CtrlGenererParcours implements ActionListener {

	private Fenetre fenetre;
	private Parcours parcours;
	
	public CtrlGenererParcours(Fenetre f) {
		this.fenetre = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		parcours = new Parcours(fenetre.getNomParcours());
		ArrayList<Monument> list = fenetre.getMonumentsSelected();
		if (list.size() < 2){
			JOptionPane.showMessageDialog(fenetre, "Veullez sélectionner au moins 2 monuments", "Nb monuments tp petit", JOptionPane.ERROR_MESSAGE);
		}
		else if (fenetre.getNomParcours().equals("")) {
			JOptionPane.showMessageDialog(fenetre, "Veullez mettre un nom de parcours", "Pas de nom de parcours", JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				parcours.creationChemin(list);
				genererParcoursJson();
				fenetre.setOnglet(0);			
				fenetre.ajoutParcours(parcours);
				fenetre.supprimeEtAjoute(parcours.getChemin_monument());
				//supprime les lignes
				fenetre.removeLine();
				//crée les lignes à partir de la liste de monument
				fenetre.drawLine(parcours.getChemin_monument());
			} catch (IOException | JSONException e1) {
				e1.printStackTrace();
			}
		}

	}
	/**
	 * M�thode qui cr�� un repertoire pour le parcours et qui stock dans ce repertoire tout les fichiers json des chemins du parcours
	 */
	public void genererParcoursJson() {
		new File("data/chemin/"+parcours.getNom()).mkdir(); 
		for (int i = 0; i<parcours.getChemin_a_prendre().size();i++) {
			Chemin cheminCourant = parcours.getChemin_a_prendre().get(i);
			System.out.println(cheminCourant);
			try {
				APIDirectionJson.createCheminJson(parcours.getNom(), APIDirectionJson.getJson(cheminCourant.getMonumentDep(), cheminCourant.getMonumentArr()));
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}