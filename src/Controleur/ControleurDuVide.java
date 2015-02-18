package Controleur;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Vue.Fenetre;

/**
 * Controleur qui permet de supprimer le texte present
 * dans le champs de texte de l'ajout de monument lors 
 * du clic de la souris
 * @author Groupe L
 *
 */
public class ControleurDuVide implements MouseListener {

	private Fenetre fen;
	
	public ControleurDuVide(Fenetre fen) {
		this.fen = fen;
	}

	public void mouseClicked(MouseEvent arg0) {
		fen.viderMonumentAjoute();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

