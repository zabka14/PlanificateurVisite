package Vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JRadioButton;

import Modele.Chemin;
import Modele.Monument;
import Modele.Parcours;

public class RadioParcours extends JRadioButton {

	private static final long serialVersionUID = 1L;
    private ArrayList<Monument> sesMonums;
    private Parcours parcours;
    private String path;
    
    public RadioParcours(String nom){
    	super(nom);
    }
    
	public RadioParcours(String nom,String path) {
		super(nom);
		this.path=path;
		this.setBackground(Color.WHITE);
	}
	
	public RadioParcours(String nom, String path, Parcours p){
		super(nom);
		this.path=path;
		this.parcours=p;
		this.sesMonums = this.parcours.getChemin_monument();
		this.setBackground(Color.WHITE);
	}
	
	public void setParcours(Parcours p){
		this.parcours = p;
		this.sesMonums = this.parcours.getChemin_monument();
	}
	
	public String getPath(){
		return this.path;
	}
	
	public ArrayList<Monument> getMonuments(){
		return this.sesMonums;
	}
	
	public Parcours getParcours(){
		return this.parcours;
	}

	
}
