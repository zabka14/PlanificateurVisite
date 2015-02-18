package Modele;
/**
 * @author Group L
 * This class contains different method to handle Coordonnees Object (coordinates), used to represent geographicaly monuments.
 */
public class Coordonnees {
	Double x;
	Double y;
	
	public Coordonnees(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coordonnees [x=" + x + ", y=" + y + "]";
	}
	
	
	
	

}
