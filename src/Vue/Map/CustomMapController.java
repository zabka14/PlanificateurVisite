package Vue.Map;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;


public class CustomMapController extends JMapController implements MouseWheelListener {

	private int minZoom;

	public CustomMapController(JMapViewer map)  {
		super(map);
		this.minZoom = 12;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		//si le mouvement de la roulette de la souris est vers l'avant, on zoom vers le curseur
		if ((e.getWheelRotation() < 0)){
			map.setZoom(map.getZoom()+1, e.getPoint());
		}
		//on peut dezoomer que si le zoom actuelle est superieur au minimum
		else if (map.getZoom() > this.minZoom) {
			//si le zoom est juste d'une unité superieur au minimum, on amene la vue de la carte à un emplacement précis
			if (map.getZoom() == this.minZoom + 1) {
				map.setDisplayPosition(new Coordinate(48.8584100, 2.3488000), this.minZoom);
			}
			else {
				map.setZoom(map.getZoom() -1);
			}
			
	
		}
		
	}
	
	

}
