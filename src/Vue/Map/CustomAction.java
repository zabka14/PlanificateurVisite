package Vue.Map;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

import org.json.JSONException;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import APIJson.APIGeocod;

@SuppressWarnings("serial")
public class CustomAction extends AbstractAction {
	
	private Coordinate coord;

	public CustomAction(Coordinate coord){
		super();
		this.coord = coord;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//creation d'un monument à partir des coordonnées
		System.out.println(coord);
		try {
			System.out.println(APIGeocod.getAdresse(coord));
		} catch (IOException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}

}
