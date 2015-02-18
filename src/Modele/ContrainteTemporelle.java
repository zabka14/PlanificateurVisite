package Modele;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ContrainteTemporelle extends Contrainte{
	
	@SuppressWarnings("unused")
	private Date heureDepart;
	@SuppressWarnings("unused")
	private Date heureArrivee;
	
	public ContrainteTemporelle(String nom, String heureDepart, String heureArrivee){
		super(nom);
		try{
		DateFormat f = new SimpleDateFormat("HH'h'mm");
		this.heureDepart = f.parse(heureDepart);
		this.heureArrivee = f.parse(heureArrivee);
		}
		catch(ParseException e){
			System.out.println(e);
		}
	}

}
