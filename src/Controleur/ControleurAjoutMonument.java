 package Controleur;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

import APIJson.APIGeocod;
import APIJson.MonumentJson;
import Modele.Coordonnees;
import Modele.Monument;
import Vue.CheckBox;
import Vue.Fenetre;

/**
 * Controleur qui ajoute un monument en cr�ant un fichier json � partir du nom du monument
 * @author Groupe L
 *
 */
public class ControleurAjoutMonument implements ActionListener {
	private Fenetre fen;
	private JSONObject jso;
	
	public ControleurAjoutMonument(Fenetre fen) {
		this.fen = fen;
		jso = new JSONObject();
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if(fen.verificationValidite()) {
				String nomMonument = fen.getMonumentAjoute();
				try {	
					jso = APIGeocod.getJSON(nomMonument); // On recupere le fichier json � partir du nom du monument
					
					System.out.println("Before check if POI");
					System.out.println("POI result :"+APIGeocod.checkIfPOI(jso));
					
					if (APIGeocod.checkIfPOI(jso)) {
						String nom = APIGeocod.getNom(jso);
						String adresse = APIGeocod.getAdresse(jso);
						Coordonnees coord = APIGeocod.getCoordones(jso);
						Monument mon = new Monument(nom, adresse,fen.getTypeCategorie(), coord, null, null);
						MonumentJson.toJson(mon); // creation du fichier json pars� contenant les info sur le monument
						// Message de dialogue
						JOptionPane.showMessageDialog(fen, "Le monument a a �t� ajout� � la carte", "Monument ajout�",JOptionPane.INFORMATION_MESSAGE);
						//ajoute un marqueur de ce monument sur la map
						fen.setMarkers(mon);
						// Ajout d'une checkbox correspondant au monument dans le bon panel
						fen.ajoutMonumentPanel(new CheckBox(mon.getNom(), mon));
					}
					else
					{
						JOptionPane.showMessageDialog(fen, "Le monument n'existe pas � Paris","Erreur monument",JOptionPane.ERROR_MESSAGE);
					}
				} catch (JSONException | IOException e1) {
					JOptionPane.showMessageDialog(fen, "Le monument n'existe pas � Paris","Erreur monument",JOptionPane.ERROR_MESSAGE);
				} 	
			}
		} catch (HeadlessException e1) {
			JOptionPane.showMessageDialog(fen, "Le monument n'existe pas � Paris","Erreur monument",JOptionPane.ERROR_MESSAGE);
		}
	}
}
