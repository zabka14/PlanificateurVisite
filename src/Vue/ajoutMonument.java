package Vue;

import java.io.IOException;

import APIJson.MonumentJson;
import Modele.Coordonnees;
import Modele.Monument;

public class ajoutMonument {

	public static void main(String[] args) throws IOException {
		//monument lieu publique 
		Monument m = new Monument("Palais-Royal", "Palais-Royal, Paris, France", "Lieux publiques", new Coordonnees(48.865221, 2.3353643), null,null);
		Monument m2 = new Monument("Pont Alexandre III", "Pont Alexandre III, Paris, France", "Lieux publiques", new Coordonnees(48.8637103,2.313560400000001), null,null);
		Monument m3 = new Monument("Pont des Arts", "Pont des Arts, Pont des Arts, 75006 Paris, France", "Lieux publiques", new Coordonnees(48.85834240000001,2.3375084), null,null);
		Monument m4 = new Monument("Pont Neuf", "Pont Neuf, 75001 Paris, France", "Lieux publiques", new Coordonnees(48.8573471,2.3415311), null,null);
		Monument m5 = new Monument("Le Jardin du Luxembourg", "Le Jardin du Luxembourg, 75006 Paris, France", "Lieux publiques", new Coordonnees(48.8462217,2.3371605), null,null);
		Monument m6 = new Monument("Parc de la Villette", "Parc de la Villette, 211 Avenue Jean Jaurès, 75019 Paris, France", "Lieux publiques", new Coordonnees(48.8938489,2.39026), null,null);
		Monument m7 = new Monument("Parc des Buttes-Chaumont", "Parc des Buttes-Chaumont, 1 Rue Botzaris, 75019 Paris, France", "Lieux publiques", new Coordonnees(48.8809496,2.3827609), null,null);
		Monument m8 = new Monument("Tuileries", "Tuileries, 75001 Paris, France", "Lieux publiques", new Coordonnees(48.864318,2.3302), null,null);
		Monument m9 = new Monument("Place de la Concorde", "Place de la Concorde, 75008 Paris, France","Lieux publiques", new Coordonnees(48.8656331,2.3212357), null,null);
		Monument m10 = new Monument("Place des Vosges", "Place des Vosges, Paris, France","Lieux publiques", new Coordonnees(48.85618179999999,2.3657583), null,null);
		Monument m11 = new Monument("Place de la Bastille", "Place de la Bastille, Paris, France","Lieux publiques", new Coordonnees(48.8534525,2.3692985), null,null);
		Monument m12 = new Monument("Opera Garnier", "Opera Garnier, Rue Scribe, 75009 Paris, France","Lieux publiques", new Coordonnees(48.87165599999999,2.3305356), null,null);
		Monument m13 = new Monument("Hôtel des Invalides", "Invalides, 75007 Paris, France", Monument.secondaire, new Coordonnees(48.8593608,2.3138127),null,null);
		Monument m14 = new Monument("Cimetière Père-Lachaise", "Cimetière Père-Lachaise, 16 Rue du Repos, 75020 Paris, France", Monument.secondaire, new Coordonnees(48.861393,2.3933276),null,null);
		Monument m15 = new Monument("Cimetière de Montmartre", "Cimetière de Montmartre, 20 Avenue Rachel, 75018 Paris, France", Monument.secondaire, new Coordonnees(48.8879078,2.3298844),null,null);
		Monument m16 = new Monument("Panthéon", "Panthéon, Place du Panthéon, 75005 Paris, France", Monument.secondaire, new Coordonnees(48.8462218,2.3464138),null,null);
		Monument m17 = new Monument("Les Catacombes de Paris", "Les Catacombes de Paris, Inspection Générale des Carrières, 1 Avenue du Colonel Henri Rol-Tanguy, 75014 Paris, France", Monument.secondaire, new Coordonnees(48.8338325,2.3324222),null,null);
		Monument m18 = new Monument("Île Saint-Louis", "Île Saint-Louis, 75004 Paris, France", Monument.secondaire, new Coordonnees(48.85189279999999,2.3563946),null,null);
		Monument m19 = new Monument("Arènes de Lutèce", "Arènes de Lutèce, 49 Rue Monge, 75005 Paris, France", Monument.secondaire, new Coordonnees(48.845116,2.3528366),null,null);
		Monument m20 = new Monument("Musée de Cluny - Musée national du Moyen Âge", "Musée de Cluny - Musée national du Moyen Âge, 6 Place Paul Painlevé, 75005 Paris, France", Monument.secondaire, new Coordonnees(48.8504833,2.3440808),null,null);
		Monument m21 = new Monument("Musée du Louvre","Musée du Louvre, 75001 Paris, France",Monument.musee,new Coordonnees(48.8606111,2.337644),null,null);
		Monument m22 = new Monument("Le Centre Pompidou","Le Centre Pompidou, 75004 Paris, France",Monument.musee,new Coordonnees(48.860642,2.352245),null,null);
		Monument m23 = new Monument("Cité des Sciences et de l'Industrie","Cité des Sciences et de l'Industrie, 30 Avenue Corentin Cariou, 75019 Paris, France",Monument.musee,new Coordonnees(48.8955948,2.3878996),null,null);
		Monument m24 = new Monument("Musée d'Orsay","Musée d'Orsay, 1 Rue de la Légion d'Honneur, 75007 Paris, France",Monument.musee,new Coordonnees(48.8599614,2.3265614),null,null);
		Monument m25 = new Monument("Musée de l'Orangerie","Jardin Tuileries, Musée de l'Orangerie, 75001 Paris, France",Monument.musee,new Coordonnees(48.8637884,2.3226724),null,null);
		Monument m26 = new Monument("Musée du quai Branly","Musée du quai Branly, 37 Quai Branly, 75007 Paris, France",Monument.musee,new Coordonnees(48.8608889,2.297894),null,null);
		Monument m27 = new Monument("Musée Rodin","Musée Rodin, 79 Rue de Varenne, 75007 Paris, France",Monument.musee,new Coordonnees(48.8553118,2.3157737),null,null);
		Monument m28 = new Monument("Musée Carnavalet","Musée Carnavalet, 16 Rue des Francs Bourgeois, 75003 Paris, France",Monument.musee,new Coordonnees(48.8587152,2.3638224),null,null);
		Monument m29 = new Monument("Musée de Cluny - Musée national du Moyen Âge","Musée de Cluny - Musée national du Moyen Âge, 6 Place Paul Painlevé, 75005 Paris, France",Monument.musee,new Coordonnees(48.8504833,2.3440808),null,null);
		Monument m30 = new Monument("Musée Grévin","Musée Grévin, 10 Boulevard Montmartre, 75009 Paris, France",Monument.musee,new Coordonnees(48.87183779999999,2.3422204),null,null);
		Monument m31 = new Monument("Grand Palais","Grand Palais, 3 Avenue du Général Eisenhower, 75008 Paris, France",Monument.musee,new Coordonnees(48.8661091,2.3124544),null,null);
		Monument m32 = new Monument("Petit Palais","Petit Palais, Avenue Winston Churchill, 75008 Paris, France",Monument.musee,new Coordonnees(48.8660479,2.3145896),null,null);
		Monument m33 = new Monument("Île de la Cité","Ile de la Cité, 75004 Paris, France", Monument.principaux,	new Coordonnees(48.8544595, 2.347621),null,null);
		Monument m34 = new Monument("Basilique Sacré-Cœur","Sacré-Cœur, 35 Rue du Chevalier de la Barre, 75018 Paris, France", Monument.principaux,new Coordonnees(48.88670459999999, 2.3431043),null,null);
		Monument m35 = new Monument("Sainte Chapelle","Sainte Chapelle, 8 Boulevard du Palais, 75001 Paris, France", Monument.eglise,new Coordonnees(48.88670459999999, 2.3431043),null,null);
		Monument m36 = new Monument("Église de la Madeleine","L'église de la Madeleine, Place de la Madeleine, 75008 Paris, France", Monument.eglise,new Coordonnees(48.8700435, 2.3245502),null,null);
		Monument m37 = new Monument("Champs-Élysées","Champs-Élysées, Paris, France",Monument.principaux,new Coordonnees(48.8657844,2.3073141),null,null);

		
	}

}
