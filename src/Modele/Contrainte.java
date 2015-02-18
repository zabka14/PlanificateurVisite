package Modele;

public abstract class Contrainte {
	
	private String name;
	
	public Contrainte(String s){
		this.setName(s);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
