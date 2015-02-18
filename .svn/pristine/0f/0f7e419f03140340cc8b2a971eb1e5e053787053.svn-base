package Modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Group L
 * This class put together different handler for Monument Object. Those Objects represents places in Paris, with a multitude of attributes.
 */
public class Monument {
	private String nom;
	private String adresse;
	private String categorie;
	private Coordonnees coordonnee;
	private Date heureO;
	private Date heureF;
	
	/* Categorie possible */
	public static final String principaux = "Lieux principaux";
	public static final String secondaire = "Lieux secondaires";
	public static final String musee = "Mus\u00E9es";
	public static final String eglise = "Eglises";
	public static final String publiques = "Lieux publiques";
	public static final String autres = "Autres";
	
	public Monument(String nom, String adresse, String categorie, Coordonnees coordonnee,String heureO,String heureF) {
		this.nom = nom;                
		this.adresse = adresse;
		this.coordonnee = coordonnee;
		this.categorie = categorie;
		DateFormat f  = new SimpleDateFormat("HH'h'mm");
		try {
			if (heureO != null){
				this.heureO = f.parse(heureO);
			}
			if (heureF != null){
				this.heureO = f.parse(heureF);
			}			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Monument(String nom, String categorie) {
		this.nom = nom;
		this.categorie = categorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAdresse(String adresse){
		this.adresse = adresse;
	}
	
	public String getAdresse(){
		return adresse;
	}
	
	public Coordonnees getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Coordonnees coordonnee) {
		this.coordonnee = coordonnee;
	}

	public Date getHeureO() {
		return heureO;
	}

	public void setHeureO(Date heureO) {
		this.heureO = heureO;
	}

	public Date getHeureF() {
		return heureF;
	}

	public void setHeureF(Date heureF) {
		this.heureF = heureF;
	}

	@Override
	public String toString() {
		return "Monument [nom=" + nom + "]";
	}


	
	
	
	
	
	
	
	

}
