package Vue.Map;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.openstreetmap.gui.jmapviewer.Coordinate;

@SuppressWarnings("serial")
public class PopUpMap extends JPopupMenu {
	
	JMenuItem ajoutMonument;
	JMenuItem suppMonument;
	
	public PopUpMap() {
		super();
		this.setPopupSize(130, 40);
		
		ajoutMonument = new JMenuItem();
		ajoutMonument.add(new JLabel("Ajout monument"));
		this.add(ajoutMonument); 
		
		
		//suppMonument = new JMenuItem();
		//suppMonument.add(new JLabel("Supp monument"));
		//this.add(suppMonument); 
	}
	
	public void actuAction(Coordinate coord){
		ajoutMonument.setAction(new CustomAction(coord));
	}
	

}
