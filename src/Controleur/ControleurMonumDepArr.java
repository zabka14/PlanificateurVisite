package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Fenetre;

/**
 * Controleur qui permet de rendre selectionnable le monument de depart et d'arrive en fonction de la checkbox
 * @author Groupe L
 *
 */
public class ControleurMonumDepArr implements ActionListener {

	private Fenetre fen;
	
	public ControleurMonumDepArr(Fenetre f){
		this.fen = f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fen.updateMonumDep();
		fen.updateMonumArr();
		
	}
}
