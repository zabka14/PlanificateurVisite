package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Vue.CheckBox;
import Vue.Fenetre;

/**
 * Controleur qui permet d'afficher en rouge les controleurs choisis
 * @author Groupe L
 *
 */
public class ControleurMonumentSelected implements ActionListener {

	private Fenetre fen;
	
	public ControleurMonumentSelected(Fenetre f) {
		this.fen = f;
	}
	
	public static void checkColor(Fenetre fen) {
		ArrayList<CheckBox> al = fen.getCategorie();
		for (int i = 0;i<al.size(); i++) {
			if(al.get(i).isSelected()) {
				System.out.println("Monument select : " + al.get(i));
				fen.setMarkersColor(al.get(i), Color.RED);
			}
			else {
				fen.setMarkersColor(al.get(i), Color.YELLOW);
			}			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		ControleurMonumentSelected.checkColor(fen);
	}

}
