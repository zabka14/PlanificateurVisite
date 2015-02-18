package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Modele.Chemin;
import Modele.Parcours;
import Vue.Fenetre;
import Vue.RadioParcours;

/**
 * Controleur qui permet d'afficher la bonne description en bas de la map en fonction du choix du parcours
 * @author Groupe L
 */
public class ControlDescription implements ActionListener  {

	private Fenetre fen;
	
	public ControlDescription(Fenetre f){
		fen = f;
	}
	 
	public void actionPerformed(ActionEvent arg0) {
		RadioParcours radioParcours = (RadioParcours) arg0.getSource();
		fen.removeLine();
		
		if (radioParcours.getMonuments() != null){
			fen.supprimeEtAjoute(radioParcours.getMonuments());
			fen.drawLine(radioParcours.getMonuments());
		}
		Parcours parcours = radioParcours.getParcours();
		String s2="";
		
		for(Chemin ch : parcours.getChemin_a_prendre()){	
			ch.remplirChemin("data/chemin/" + parcours.getNom() + "/" + ch.getMonumentDep().getNom().replace(" ", "_") + " " + ch.getMonumentArr().getNom().replace(" ", "_") + ".json");
			for (String s : ch.getChemin_a_prendre()) {	
				s = s.replace("<b>"," ");
				s = s.replace("</b>"," ");
				s = s.replace("<div style:\"font-size:0.9em\">", " ");
				s = s.replace("</div>", " ");
				s = s.replace("à","\u00E0");
				s = s.replace("ç","\u00E7");
				s = s.replace("â","\u00E2");
				s = s.replace("é","\u00E9");
				s = s.replace("è","\u00E8");
				s = s.replace("ô","\u00F4");
				s = s.replace("ó","\u00F3");
				s = s.replace("ò","\u00F2");
				s = s.replace("ù","\u00F9");
				s = s.replace("ú","\u00FA");
				s = s.replace("û","\u00Fb");
				s = s.replace("î","\u00EE");
				s2 = s2 + s + "<br/>";
			}
		}
		s2 = "<html>" + s2 +"</html>";
		

		try {
			fen.updateDescription(s2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}