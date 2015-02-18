package Modele;

public class ContrainteSpatial extends Contrainte {
	
	private Monument pointDepart;
	private Monument pointArrivee;
	
	public ContrainteSpatial(String s,Monument m1, Monument m2){
		super(s);
		this.pointDepart = m1;
		this.pointArrivee = m2;
	}
	
	public Monument getPointDepart() {
		return pointDepart;
	}
	public void setPointDepart(Monument pointDepart) {
		this.pointDepart = pointDepart;
	}
	public Monument getPointArrivee() {
		return pointArrivee;
	}
	public void setPointArrivee(Monument pointArrivee) {
		this.pointArrivee = pointArrivee;
	}

	@Override
	public String toString() {
		return "ContrainteSpatial [pointDepart=" + pointDepart
				+ ", pointArrivee=" + pointArrivee + "]";
	}
	
	
	
	
	
	

}
