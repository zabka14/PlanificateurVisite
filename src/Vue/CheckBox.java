package Vue;

import java.awt.Color;

import javax.swing.JCheckBox;

import Controleur.ControlDescription;
import Modele.Monument;
import Modele.Parcours;

public class CheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;
	private Monument sonMonument;
	
	public CheckBox(String s, Monument sonMonument) {
		super(s);
		this.sonMonument = sonMonument;
		this.setBackground(Color.WHITE);
	}

	public Monument getSonMonument() {
		return sonMonument;
	}
	public void setSonMonument(Monument sonMonument) {
		this.sonMonument = sonMonument;
	}
	
	public String toString() {
		return "CheckBox [sonMonument=" + sonMonument + "]";
	}	
}

