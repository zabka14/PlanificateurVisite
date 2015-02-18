package Vue.Map;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;


public class CtrlPopup extends MouseAdapter{
	
	private PopUpMap popup;
	private JMapViewer map;

	public CtrlPopup(PopUpMap popup,JMapViewer map){
		this.popup = popup;
		this.map = map;
	}
	
	
	public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
        
       Coordinate coord = map.getPosition(e.getLocationOnScreen());
       popup.actuAction(coord);
       
    }

}
