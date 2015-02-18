package Vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;

import APIJson.MonumentJson;
import Controleur.CategorieSelectListener;
import Controleur.ControlDescription;
import Controleur.ControleurAjoutMonument;
import Controleur.ControleurCategorieContrainte;
import Controleur.ControleurCheckBoxContrainte;
import Controleur.ControleurDebutEtFin;
import Controleur.ControleurDuVide;
import Controleur.ControleurMonumDepArr;
import Controleur.ControleurMonumentSelected;
import Controleur.ControleurOnglet;
import Controleur.CtrlGenererParcours;
import Controleur.HeureArrDepListener;
import Modele.Chemin;
import Modele.Monument;
import Modele.Parcours;
import Vue.Map.CtrlPopup;
import Vue.Map.CustomMapController;
import Vue.Map.PopUpMap;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class Fenetre extends JFrame {
	
	/* Description itinéraire */
	
	private JTextPane description;
	
	/* Parcours */
	
	private ArrayList<RadioParcours> parcs;
	private ArrayList<RadioParcours> parcs2;

    private ButtonGroup parcButton;
    JTabbedPane onglets;
    
    /* Choix monuments */
    
    private JButton genereParcours;
    private JTextField nomParcours;
    private JComboBox<String> boxType;
    private JComboBox<String> boxType2;
    private JComboBox<String> boxType3;
    private JButton ajoutMonument; // Bouton qui permet l'ajout d'un monument
    private JTextField monumentAjoute; // Champs de texte ou l'on rentre le monument à ajouté
    
    // Les listes contenant les monuments triés par catégorie
    private ArrayList<CheckBox> lieuxPrincipaux;
    private ArrayList<CheckBox> lieuxSecondaires;
    private ArrayList<CheckBox> musees;
    private ArrayList<CheckBox> eglises;
    private ArrayList<CheckBox> lieuxPubliques;
    private ArrayList<CheckBox> lieuxAutres;
    
    LinkedList<String> types;
    
    private JPanel ChoixMonums;
    
    // Les panel des différentes catégories
	private JPanel principaux;
	private JPanel secondaires;     
	private JPanel musee;   
	private JPanel eglise;    
	private JPanel publiques;
	private JPanel autres;
	
	// JPanel servant à l'affichage des checkbox
	private JPanel courant;
	    
   /* Contraintes */
    
    private JCheckBox checkMonumDep;
    private JCheckBox checkMonumArr;
    private JComboBox<String> monument1;
    private JComboBox<String> monument2;
    private JTextField heure1;
    private JTextField heure2;
    private JCheckBox depart;
    private JCheckBox arrivee;
    private LinkedList<String> monuments;
    private JCheckBox checkRepas;
    private JPanel repas;
   
    /*  ???????????????????   */
    private Parcours parcours;
    
    private JMapViewer map;
    
    JPanel leChoixParcours;
    JPanel leChoixParcours2;
    
    JPanel choixDep;
    
    public static boolean sauveNousStp = true;
    
    
//****************************   Debut de la fenetre  ************************** */   
    
 
    public Fenetre(String a) throws IOException { 
    super(a);
    
    parcours = new Parcours("hello");
    
    //crée un JMapViewer à partir d'une méthode pour les options et affiche les marqueurs
    createJmapViewer();
    //setMarkers();
	
    JMapViewer PanPicLabel = map;
    
    // Panneau principal
    JPanel Principal = new JPanel();
    Principal.setLayout(new BorderLayout());
    
    // Création d'un panel à onglets pour la partie Gauche
    onglets = new JTabbedPane();
    onglets.addChangeListener(new ControleurOnglet(this));
    onglets.setPreferredSize(new Dimension(350,30));
    onglets.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
    
    // Création des trois onglets
    JPanel onglet1 = new JPanel();
    onglet1.setLayout(new GridLayout(2,1));
    onglet1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    
    JPanel onglet2 = new JPanel();
    onglet2.setBorder(BorderFactory.createLineBorder(Color.WHITE));

    JPanel onglet3 = new JPanel();
    onglet3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    
   
    // Background des 3 onglets
    Color fondBlanc = new Color(255,255,255);
    
    onglets.setBackground(fondBlanc);
    onglet1.setBackground(fondBlanc);
    onglet2.setBackground(fondBlanc);
    onglet3.setBackground(fondBlanc);


    
    
    
// ---------------------------  Onglet   n°1  ---------------------------------- 
    
    
    // Layout qui contiendra les parcours prédéfinis
    leChoixParcours = new JPanel();

    leChoixParcours2 = new JPanel();
    
    leChoixParcours.setLayout(new BoxLayout(leChoixParcours,BoxLayout.Y_AXIS));
    leChoixParcours.setBorder(BorderFactory.createTitledBorder("Parcours complets par d\u00E9faut"));
    leChoixParcours.setBackground(fondBlanc);
    
    //Titre de l'onglet
    JPanel titreOnglet1 = new JPanel();
    JLabel titre1 = new JLabel("<html><h2>Parcours pr\u00E9d\u00E9finis</h2></html>");
    titreOnglet1.add(titre1);
    titreOnglet1.setBackground(fondBlanc);

    
    // Définition des JRadioButton qui correspondent aux parcours par défaut
    parcs = new ArrayList<RadioParcours>();

    
    // Création du buttonGroup et ajout des boutons à celui-ci
    this.parcButton = new ButtonGroup();
    if (!parcs.isEmpty()){
        for(RadioParcours radio : parcs){
        	parcButton.add(radio);
        	leChoixParcours.add(radio);
        	radio.addActionListener(new ControlDescription(this));
        }
    }

    // Panel correspondant au premier du grand Panel
    JPanel onglet11 = new JPanel();
    onglet11.setLayout(new BorderLayout());
    onglet11.add(titreOnglet1,BorderLayout.NORTH);
    onglet11.add(leChoixParcours,BorderLayout.CENTER);
    onglet11.setBackground(fondBlanc);

    
    
 // Panel correspondant au second du grand panel
    JPanel onglet12 = new JPanel();
    onglet12.setLayout(new BorderLayout());
    onglet12.setBackground(fondBlanc);
    
    leChoixParcours2.setLayout(new BoxLayout(leChoixParcours2,BoxLayout.Y_AXIS));
    leChoixParcours2.setBorder(BorderFactory.createTitledBorder("Parcours complets cr\u00E9\u00E9s"));
    leChoixParcours2.setBackground(fondBlanc);
    
  //Titre de l'onglet
    JPanel titreOnglet2 = new JPanel();
    JLabel titre2 = new JLabel("<html><h2>Parcours utilisateur</h2></html>");
    titreOnglet2.add(titre2);
    titreOnglet2.setBackground(fondBlanc);

    
    // Définition des JRadioButton qui correspondent aux parcours par défaut
    parcs2 = new ArrayList<RadioParcours>();
    
    // Création du buttonGroup et ajout des boutons à celui-ci
    this.parcButton = new ButtonGroup();
    
    if (!parcs2.isEmpty()){
        for(RadioParcours radio : parcs2){
        	parcButton.add(radio);
        	leChoixParcours2.add(radio);
        	radio.addActionListener(new ControlDescription(this));
        }
    }
    onglet12.add(titreOnglet2,BorderLayout.NORTH);
    onglet12.add(leChoixParcours2,BorderLayout.CENTER);
    
 // Ajout du panneau dans l'onglet des parcours  
    onglet1.add(onglet11);
    onglet1.add(onglet12);
    
    
 // ---------------------------  Onglet   n°2  ----------------------------------     
    
    
    onglet2.setLayout(new BorderLayout());
    
    // Dans la première case du panneau principal création d'un Grid a deux cases
    this.ChoixMonums = new JPanel();
    this.ChoixMonums.setLayout(new BorderLayout());
    ChoixMonums.setBackground(fondBlanc);


    JScrollPane barre = new JScrollPane();
    
    // ajout du scrollPane qui s'occupe de la JTextArea
    this.ChoixMonums.add(barre, BorderLayout.CENTER);
    
    // Premier panel case avec les types de monuments de la ComboBox
    JPanel listeChoix1 = new JPanel();
    listeChoix1.setBorder(BorderFactory.createTitledBorder("Choix de la cat\u00E9gorie"));
    listeChoix1.setPreferredSize(new Dimension(250,80));
    listeChoix1.setBackground(fondBlanc);

    
    // Ajout des types dans la ComboBox
    this.boxType = new JComboBox<String>();
    boxType.addItem(Monument.principaux);
    boxType.addItem(Monument.secondaire);
    boxType.addItem(Monument.musee);
    boxType.addItem(Monument.eglise);
    boxType.addItem(Monument.publiques);
    boxType.addItem(Monument.autres);
    
     // Ajout des types dans la ComboBox2
    this.boxType2 = new JComboBox<String>();
    boxType2.addItem(Monument.principaux);
    boxType2.addItem(Monument.secondaire);
    boxType2.addItem(Monument.musee);
    boxType2.addItem(Monument.eglise);
    boxType2.addItem(Monument.publiques);
    boxType2.addItem(Monument.autres);
    
    // Ajout des types dans la ComboBox3
    this.boxType3 = new JComboBox<String>();
    boxType3.addItem(Monument.principaux);
    boxType3.addItem(Monument.secondaire);
    boxType3.addItem(Monument.musee);
    boxType3.addItem(Monument.eglise);
    boxType3.addItem(Monument.publiques);
    boxType3.addItem(Monument.autres);

	// Ajout de la comboBox
	listeChoix1.add(boxType);
	
	// Couleurs des combobox de choix des catégories
	this.boxType.setBackground(fondBlanc);
	this.boxType2.setBackground(fondBlanc);
	this.boxType3.setBackground(fondBlanc);
	
	
	this.boxType.addActionListener(new CategorieSelectListener(this));
	this.boxType2.addActionListener(new ControleurCategorieContrainte(this));
	this.boxType3.addActionListener(new ControleurCategorieContrainte(this));
	
	
    
/* ---------------------------------------------CheckBoxes-------------------------------------------*/
    
    this.principaux = new JPanel();
    this.principaux.setLayout(new GridLayout(10,1));
    this.principaux.setBorder(BorderFactory.createTitledBorder(Monument.principaux));
    
    this.secondaires = new JPanel();
    this.secondaires.setLayout(new GridLayout(10,1));
    this.secondaires.setBorder(BorderFactory.createTitledBorder(Monument.secondaire));    
    
    this.musee = new JPanel();
    this.musee.setLayout(new GridLayout(12,1));
    this.musee.setBorder(BorderFactory.createTitledBorder(Monument.musee));
    
    this.eglise = new JPanel();
    this.eglise.setLayout(new GridLayout(10,1));
    this.eglise.setBorder(BorderFactory.createTitledBorder(Monument.eglise));
    
    this.publiques = new JPanel();
    this.publiques.setLayout(new GridLayout(13,1));
    this.publiques.setBorder(BorderFactory.createTitledBorder(Monument.publiques));
        
    this.autres = new JPanel();
    this.autres.setLayout(new GridLayout(10,1));
    this.autres.setBorder(BorderFactory.createTitledBorder(Monument.autres));
    
    
    // Couleurs des panels des différentes catégories
    this.principaux.setBackground(fondBlanc);
    this.secondaires.setBackground(fondBlanc);
    this.musee.setBackground(fondBlanc);
    this.eglise.setBackground(fondBlanc);
    this.publiques.setBackground(fondBlanc);
    this.autres.setBackground(fondBlanc);
    
    HashMap<String, ArrayList<Monument>> mapCat = this.monumentParCategorie();
    // Initialisation de la liste des principaux lieux   
    this.lieuxPrincipaux = new ArrayList<CheckBox>();
    if (mapCat.get(Monument.principaux) != null){
    	for (Monument m : mapCat.get(Monument.principaux)) {
    		CheckBox cb = new CheckBox(m.getNom(), m);
			this.principaux.add(cb);
			lieuxPrincipaux.add(cb);
    	}
    }
    
    // Initialisation de la liste des lieux secondaires
    this.lieuxSecondaires = new ArrayList<CheckBox>();
    if (mapCat.get(Monument.secondaire) != null){
    	for (Monument m : mapCat.get(Monument.secondaire)) {
    		CheckBox cb = new CheckBox(m.getNom(), m);
			this.secondaires.add(cb);
			lieuxSecondaires.add(cb);
    	}
    }

    // Initialisation de la liste des musées
    this.musees = new ArrayList<CheckBox>();
    if (mapCat.get(Monument.musee) != null){
        for (Monument m : mapCat.get(Monument.musee)) {
        	CheckBox cb = new CheckBox(m.getNom(), m);
			this.musees.add(cb);
			musee.add(cb);
        }		
    }


    // Initialisation de la liste des églises
    this.eglises = new ArrayList<CheckBox>();
    if (mapCat.get(Monument.eglise) != null){
    	for (Monument m : mapCat.get(Monument.eglise)) {
    		CheckBox cb = new CheckBox(m.getNom(), m);
			this.eglises.add(cb);
			eglise.add(cb);
		}		
    }
 
    // Initialisation de la liste des lieux publiques
    this.lieuxPubliques = new ArrayList<CheckBox>();
    if (mapCat.get(Monument.publiques) != null){
    	for (Monument m : mapCat.get(Monument.publiques)) {
    		CheckBox cb = new CheckBox(m.getNom(), m);
			this.publiques.add(cb);
			lieuxPubliques.add(cb);
		}

    }
	
    this.lieuxAutres = new ArrayList<CheckBox>();
    if (mapCat.get(Monument.autres) != null){
    	for (Monument m : mapCat.get(Monument.autres)) {
    		CheckBox cb = new CheckBox(m.getNom(), m);
			this.lieuxAutres.add(cb);
			autres.add(cb);
		}	
    }
    
	this.checkboxControleur();
    // Initialisation du panel courant
	this.courant = new JPanel();
	this.courant = this.principaux;
    this.courant.setBackground(fondBlanc);

	
    // Formation du premier panel
    this.ChoixMonums.add(listeChoix1, BorderLayout.NORTH);
    this.ChoixMonums.add(courant, BorderLayout.CENTER);
    
    // Dans la seconde partie du panel principal de cet onglet
    JPanel ajoutChoix = new JPanel();
    ajoutChoix.setLayout(new GridLayout(3,1));
    ajoutChoix.setBackground(fondBlanc);

    
    // Ajout d'un monument
    JLabel Warning = new JLabel("<html><Font size=3> Attention \u00E0 faire correspondre le type de la liste avec votre monument</font></html>");
    monumentAjoute = new JTextField("Entrer le nom d'un monument");
    
    ajoutMonument = new JButton("Ajouter le monument !");
    ajoutMonument.addActionListener(new ControleurAjoutMonument(this));
    monumentAjoute.addMouseListener(new ControleurDuVide(this));
    
    // Formation du second panneau
    ajoutChoix.add(Warning);
    ajoutChoix.add(monumentAjoute);
    ajoutChoix.add(ajoutMonument);

    
    // Panneau qui va contenir les composents relatifs à la génération
    JPanel genereParcoursPanel = new JPanel();
    genereParcoursPanel.setLayout(new GridLayout(3,1));
    genereParcoursPanel.setBackground(fondBlanc);

    
    // Boutons de generation de parcours
    genereParcours = new JButton("Generer le parcours");
    genereParcours.setHorizontalAlignment(SwingConstants.CENTER);
    genereParcours.addActionListener(new CtrlGenererParcours(this));
    
 
    // Couleurs boutons onglet 2
    Color fondButtonOnglet2 = new Color(154,57,68);
    ajoutMonument.setBackground(fondButtonOnglet2);
    genereParcours.setBackground(fondButtonOnglet2);
    
    // Recuperation du nom de parcours
    JLabel lab = new JLabel("Votre nom de parcours : ");
    lab.setHorizontalAlignment(SwingConstants.CENTER);
    nomParcours = new JTextField(10);
    nomParcours.setPreferredSize(new Dimension (10,20));
    
    // Formation du troisième panel
    genereParcoursPanel.add(lab);
    genereParcoursPanel.add(this.nomParcours);
    genereParcoursPanel.add(genereParcours);
    
    // Création d'un panel vide pour la présentation
    JPanel vide = new JPanel();
    vide.setPreferredSize(new Dimension(10,35));
    vide.setBackground(fondBlanc);

    
    
    // Panel placé au sud de l'onglet contenant ajoutChoix et genereParcoursPanel
    JPanel sud = new JPanel();
    sud.setLayout(new BorderLayout());
    sud.setBackground(fondBlanc);

    
    sud.add(ajoutChoix,BorderLayout.NORTH);
    sud.add(vide,BorderLayout.CENTER);
    sud.add(genereParcoursPanel,BorderLayout.SOUTH);

    // Rassemblement onglet n°2 
    onglet2.add(ChoixMonums, BorderLayout.CENTER);
    onglet2.add(sud, BorderLayout.SOUTH);
    
    
   
/* ----------------------------   Onglet n°3  ----------------------------------*/
    
    // déclaration layout troisième onglet
    onglet3.setLayout(new BorderLayout());
    onglet3.setBackground(fondBlanc);
    
    // Titre de l'onglet
    JPanel titreOnglet3 = new JPanel();
    titreOnglet3.setBackground(fondBlanc);
    
    JLabel titre3 = new JLabel("<html><h2>Contraintes de parcours</h2></html>");
    titreOnglet3.add(titre3);
    
    // Panel des composants
    JPanel choixContraintes = new JPanel();
    choixContraintes.setLayout(new GridLayout(2,1));
    choixContraintes.setBackground(fondBlanc);
    
    // Panel super Heures et repas
    JPanel superChoixHeuresRepas = new JPanel();
    superChoixHeuresRepas.setLayout(new BorderLayout());
    superChoixHeuresRepas.setBorder(BorderFactory.createTitledBorder("Choix des heures et repas"));
    superChoixHeuresRepas.setBackground(fondBlanc);
    
    // Panel des heures
    JPanel choixHeures = new JPanel();
    choixHeures.setLayout(new GridLayout(2,2));
    choixHeures.setBackground(fondBlanc);
    
    // JPanel contiendra le repas
    this.repas = new JPanel();
    this.repas.setLayout(new GridLayout(2,1));
    this.repas.setPreferredSize(new Dimension(20,60));
    
    // Création d'une CheckBox pour savoir si l'utilisateur souhaite manger via l'application
    JLabel checkRepas2 = new JLabel("<html><Font size=3>Trouver un restaurant pour déjeuner ?</Font></html>");
    this.checkRepas = new JCheckBox();
    this.repas.add(checkRepas2);
    this.repas.add(this.checkRepas);
    
    // Couleurs composants partie repas
    this.repas.setBackground(fondBlanc);
    this.checkRepas.setBackground(fondBlanc);
    
    // Création de la liste de monuments
	this.monuments = new LinkedList<String>();
	FileReader txt = null;
	BufferedReader readTxt = null;
	String tmStr = "";
	this.monument1 = new JComboBox<String>();
	this.monument2 = new JComboBox<String>();
	this.remplirMonument1et2();
	ItemListener ControleurDebutFin = new ControleurDebutEtFin(this);
	this.monument1.addItemListener(ControleurDebutFin);
	this.monument2.addItemListener(ControleurDebutFin);
	this.monument1.setEnabled(false);
	this.monument2.setEnabled(false);
	
	// Couleurs combobox monuments de départ et d'arrivée
	this.monument1.setBackground(fondBlanc);
	this.monument2.setBackground(fondBlanc);
	
	try
	{
		
        txt= new FileReader ("data/monuments"); 
        readTxt = new BufferedReader(txt);
        int i =0;
        
		while((tmStr=readTxt.readLine())!=null)
        {
			monuments.add(tmStr);
			monument1.addItem(monuments.get(i));
			monument2.addItem(monuments.get(i));
			
			i++;
        }
	}
	catch(FileNotFoundException e)
	{
		System.out.println(e.toString());
	}
    
	
    // Création des panels pour les contraintes
    JPanel dep = new JPanel();    
    JPanel h1 = new JPanel();   
    JPanel arr = new JPanel();
    JPanel h2 = new JPanel();
    
    // Couleurs panels contraintes
    dep.setBackground(fondBlanc);
    h1.setBackground(fondBlanc);
    arr.setBackground(fondBlanc);
    h2.setBackground(fondBlanc);
    
    /* Champs pour entrer l'heure avec checkbox + controleur */
    depart = new JCheckBox("Heure de d\u00E9part : ");
    dep.add(depart);
    heure1 = new JTextField(10);
    heure1.setEnabled(false);
    h1.add(heure1);
    arrivee = new JCheckBox("Heure d'arriv\u00E9e : ");  
    arr.add(arrivee);
    heure2 = new JTextField(10);
    heure2.setEnabled(false);
    h2.add(heure2);
    
    // Couleurs partie heures
    depart.setBackground(fondBlanc);
    arrivee.setBackground(fondBlanc);
    
    // Affectation des controleurss
    depart.addActionListener(new HeureArrDepListener(this));
    arrivee.addActionListener(new HeureArrDepListener(this));
    
     // Formation du troisième onglet
 	choixHeures.add(dep);
 	choixHeures.add(h1);
 	choixHeures.add(arr);
 	choixHeures.add(h2);
    
    // Panel des point de départ et d'arrivée
    JPanel choixDepartArrivee = new JPanel();
    choixDepartArrivee.setLayout(new GridLayout(4,1));
    choixDepartArrivee.setBorder(BorderFactory.createTitledBorder("Point de d\u00E9part et d'arriv\u00E9e"));
    
    
    JPanel Commencer = new JPanel();   
    JPanel Terminer = new JPanel();   
    JPanel commencer = new JPanel();    
    commencer.setLayout(new GridLayout(1,2));
    JPanel terminer = new JPanel();
    terminer.setLayout(new GridLayout(1,2));
    
    this.checkMonumDep = new JCheckBox("Commencer par : ");
    commencer.add(this.checkMonumDep);
    this.checkMonumArr = new JCheckBox("Terminer par : ");
    terminer.add(checkMonumArr);
    
    // Couleurs composants points départ et arrivée 
    choixDepartArrivee.setBackground(fondBlanc);
    Commencer.setBackground(fondBlanc);
    Terminer.setBackground(fondBlanc);
    commencer.setBackground(fondBlanc);
    terminer.setBackground(fondBlanc);
    this.checkMonumDep.setBackground(fondBlanc);
    this.checkMonumArr.setBackground(fondBlanc);
    
    ControleurCheckBoxContrainte ccbc = new ControleurCheckBoxContrainte(this);
    this.checkMonumDep.addActionListener(new ControleurMonumDepArr(this));
    this.checkMonumArr.addActionListener(new ControleurMonumDepArr(this));
    checkMonumDep.addActionListener(ccbc);
    checkMonumArr.addActionListener(ccbc);
    
    // Redimensionnement des ComboBox
    Dimension d = new Dimension(160,30);
	monument1.setPreferredSize(d);
	monument2.setPreferredSize(d);
	
	// Ajout des ComboBox
	commencer.add(monument1);
	terminer.add(monument2);
	
	Commencer.add(commencer);
	Terminer.add(terminer);

	choixDepartArrivee.add(boxType2);
	choixDepartArrivee.add(Commencer);
	choixDepartArrivee.add(boxType3);
	choixDepartArrivee.add(Terminer);
	
	superChoixHeuresRepas.add(choixHeures, BorderLayout.CENTER);
	superChoixHeuresRepas.add(this.repas, BorderLayout.SOUTH);
	
	choixContraintes.add(superChoixHeuresRepas);
	choixContraintes.add(choixDepartArrivee);
	

	onglet3.add(titreOnglet3, BorderLayout.NORTH);
	onglet3.add(choixContraintes, BorderLayout.CENTER);
    
    
    // Formation du panel d'onglets principal
    onglets.add("Parcours",onglet1);
    onglets.add("Choix monuments",onglet2);
    onglets.add("Contraintes",onglet3);
        
    
/* -------------------------------  ContentPane  ------------------------------- */

    JPanel zones = new JPanel();
    zones.setLayout(new BorderLayout());
    zones.setBackground(fondBlanc);
    
    // Panel central aussi divisé en zones pour afficher la carte et la description
    
    //Utilisation de la classe CustomMap qui extends JMapViewer
    JPanel carte = new JPanel();
    carte.setLayout(new BorderLayout());
    carte.setBorder(BorderFactory.createTitledBorder("Carte de Paris"));
    carte.setBackground(fondBlanc);
    
    // Zone où sera stocké la description du parcours
    JPanel Pdescription = new JPanel();
    Pdescription.setLayout(new BorderLayout());
    Pdescription.setPreferredSize(new Dimension(500,220));
    Pdescription.setBackground(fondBlanc);
   
    // Déclaration de la JTextArea
    this.description = new JTextPane();
    description.setContentType("text/html");
    description.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     
    // Création du scollPane de la JTextArea
    JScrollPane scroll = new JScrollPane(this.description);
    
    // ajout du scrollPane qui s'occupe de la JTextArea
    Pdescription.add(scroll, BorderLayout.CENTER);    
    
    // Titre de l'application
    JLabel titre = new JLabel("<html><h1 style='color:white'>Planification de visites - Paris</h1></html>");
    titre.setHorizontalAlignment(SwingConstants.CENTER);
    
    // Pied de l'application
    JLabel pied = new JLabel("<html><h4 style='color:white'>I.U.T Nantes - D\u00E9partement Informatique</h4></html>");
    pied.setHorizontalAlignment(SwingConstants.CENTER);
    
    

    
    // Ajout de la "MAP" et de la zone de texte correspondant 
    carte.add(PanPicLabel, BorderLayout.CENTER);
    carte.add(Pdescription, BorderLayout.SOUTH);
    
    /* Formation du contentPane  */
    
    Principal.add(titre, BorderLayout.NORTH);
    Principal.add(pied, BorderLayout.SOUTH);
    Principal.add(onglets, BorderLayout.WEST);
    Principal.add(carte, BorderLayout.CENTER);
    
    Color fondPrincipal = new Color(3,48,121);
    Principal.setBackground(fondPrincipal);
    
    
    this.chargerParcours();

    
    // BLA BLA administratif
    
    try {  
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		SwingUtilities.updateComponentTreeUI(this);
	 }catch (Exception e) {
		e.printStackTrace();
	 }
	 
    
    
    this.getContentPane().add(Principal);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setSize(new Dimension(1200,825));
    // this.setResizable(false);
    this.setVisible(true);
    }
    
    
    

// *****************************  Méthodes de la vue **************************
    
    /**
     *  Savoir si l'utilisateur a un monument de départ préféré
     */
    public Boolean isSelectedCheckMonumDep(){
    	return this.checkMonumDep.isSelected();
    }
    
    /**
     *  Savoir si l'utilisateur a un monument d'arrivée préféré
     */
    public Boolean isSelectedCheckMonumArr(){
    	return this.checkMonumDep.isSelected();
    }
    
    /**
     *  Autorisation ou non de précisez l'heure de départ
     */
    public void updateHeureDep() {
        boolean b = this.depart.isSelected();
        heure1.setEnabled(b);
    }
    
    /**
     *  Autorisation ou non de précisez l'heure d'arrivée
     */
    public void updateHeureArr() {
        boolean b = this.arrivee.isSelected();
        heure2.setEnabled(b);
    }
    
    /**
     *  Autorisation ou non de précisez le monument de départ
     */
    public void updateMonumDep() {
        boolean b = this.checkMonumDep.isSelected();
        monument1.setEnabled(b);
    }
    
    /**
     *  Autorisation ou non de précisez le monument d'arrivée
     */
    public void updateMonumArr() {
        boolean b = this.checkMonumArr.isSelected();
        monument2.setEnabled(b);
    }
    
    
    /**
     * Methode qui ajoute une checkbox au panel courant
     * @param c identifie la checkBox a ajoute
     */
    public void ajoutMonumentPanel(CheckBox c) {
    	courant.add(c);
    	this.repaint();
    }
    
    
    /**
     * Mise a jour de la description selon le parcours choisis
     * @param Path précise le nouveau chemin vers lequel la TextArea va aller chercher l'itineraire
     * @throws IOException
     */
    public void updateDescription(String str) throws IOException{
           this.description.setText(str);
    }
    
    /**
     * Récupérer quel JRadioButton est sélectionné
     * @return RadioBouton selectionne
     */
    public String estSelectionne(){
    	for (RadioParcours radio : parcs){
    		if (radio.isSelected()){
    			return radio.getText();
    		}  
    	}
    	return null;
    }
    
    public RadioParcours estSelectionneRadio(){
    	for (RadioParcours radio : parcs){
    		if (radio.isSelected()){
    			return radio;
    		}  
    	}
    	return null;
    }	
    
    /**
     * Recherche des monuments selectionnes parmis les checkboxes
     * @return recéupère dans une ArrayList les monuments séléctionnés
     */
    public ArrayList<Monument> getMonumentsSelected()
    {
    	ArrayList<Monument> al = new ArrayList<Monument>();

    	for (Component c : principaux.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		if (cb.isSelected()){
        		al.add(cb.getSonMonument());
    		}
    	}
    	for (Component c : secondaires.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		if (cb.isSelected()){
        		al.add(cb.getSonMonument());
    		}
    	}
    	
    	for (Component c : musee.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		if (cb.isSelected()){
        		al.add(cb.getSonMonument());
    		}
    	}
    	for (Component c : publiques.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		if (cb.isSelected()){
        		al.add(cb.getSonMonument());
    		}
    	}
    	for (Component c : eglise.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		if (cb.isSelected()){
        		al.add(cb.getSonMonument());
    		}
    	}
    	for (Component c : autres.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		if (cb.isSelected()){
        		al.add(cb.getSonMonument());
    		}
    	}

        return al;
    }
      
    /**
     * Remet a 0 le champs de texte correspondant
     * @return le monument que l'utilisateur souhaite ajouté 
     */
    public String getMonumentAjoute() {
    	return monumentAjoute.getText();
    }
    
    /**
     * Recherche de la categorie choisi
     * @return le type de la catégorie sélectionnée sous la forme d'une String dans boxType
     */
    public String getTypeCategorie()
    {
    	return boxType.getSelectedItem().toString();
    }
    
    /**
     * Recherche de la categorie choisi
     * @return le type de la catégorie sélectionnée sous la forme d'une String dans boxType2
     */
    public String getTypeCategorie2() {
    	return boxType2.getSelectedItem().toString();
    }
    
    /**
     * Recherche de la categorie choisi
     * @return le type de la catégorie sélectionnée sous la forme d'une String dans boxType2
     */
    public String getTypeCategorie3() {
    	return boxType3.getSelectedItem().toString();
    }
    
    /**
     * Recupéré le panel courant
     * @return le panneau courant
     */
    public JPanel getCourant() {
		return courant;
	}

	/**
     * Affiche les bonnes checkboxes selon le critère selectionne 
     */
    public void showCategorie(String categorie)
    {
    	ChoixMonums.remove(courant);
    	switch (categorie) 
    	{
    		case Monument.principaux: 
    			this.courant = this.principaux;
    			break;
    		case Monument.secondaire:
    			this.courant = this.secondaires;
    			break;
    		case Monument.musee:
    			this.courant = this.musee;
    			break;
    		case Monument.eglise:
    			this.courant = this.eglise;
    			break;
    		case Monument.publiques:
    			this.courant = this.publiques;
    			break;
    		case Monument.autres:
    			this.courant = this.autres;
    			break;
    		default:
    			System.out.println("Une erreur est survenue");
    	}    	
    	this.ChoixMonums.add(courant);
    	this.repaint();
    }
    
    /**
     * Affiche les bonnes checkboxes selon le critère selectionne 
     */
    public ArrayList<CheckBox> getCategorie()
    {
    	if(this.courant.equals(principaux)) {
    		return this.lieuxPrincipaux;
    	}
    	else if(this.courant.equals(musee)) {
    		return this.musees;
    	}
    	else if(this.courant.equals(secondaires)) {
    		return this.lieuxSecondaires;
    	}
    	else if(this.courant.equals(eglise)) {
    		return this.eglises;
    	}
    	else if(this.courant.equals(publiques)) {
    		return this.lieuxPubliques;
    	}
    	else if(this.courant.equals(autres)) {
    		return this.lieuxAutres;
    	}
    	return null;
    }
    
    
    public void createJmapViewer(){
    	map = new JMapViewer(new MemoryTileCache(),4);
		
		PopUpMap popup = new PopUpMap();
		map.addMouseListener(new CtrlPopup(popup,map));
	
		
		new CustomMapController(map);
		
		//centre la carte à la coordonnée de paris avec un zoom de 12
		map.setDisplayPosition(new Coordinate(48.8584100, 2.3488000), 12); 
		
		map.setZoomContolsVisible(false); //enleve le boutton pour zoomer
		
		
    }
    
	//crée des marqueurs avec le nom du monument pour chaque monuments
	public void setMarkers() throws JsonIOException, JsonSyntaxException, IOException {
		for (Monument monument : MonumentJson.fromJson()) {
			//si la coord n'est pas null 
			if (monument.getCategorie() != null){
				Coordinate coord = new Coordinate(monument.getCoordonnee().getX(),monument.getCoordonnee().getY());
				map.addMapMarker(new MapMarkerDot(monument.getNom(),coord));		
			}

		}
	}
	//Pour un seul monument
	public void setMarkers(Monument m) {
		//si la coord n'est pas null 
		if (m.getCategorie() != null){
			Coordinate coord = new Coordinate(m.getCoordonnee().getX(),m.getCoordonnee().getY());
			map.addMapMarker(new MapMarkerDot(m.getNom(),coord));		
		}
	}
	
	// Changer la couleur d'un monument
	public void setMarkersColor(CheckBox cb, Color c) {
		Monument m = cb.getSonMonument();
		if (m.getCategorie() != null){			
			Coordinate coord = new Coordinate(m.getCoordonnee().getX(),m.getCoordonnee().getY());
			MapMarkerDot mmd = new MapMarkerDot(m.getNom(),coord);
			mmd.setBackColor(c);
			map.addMapMarker(mmd);		
		}
	}
	
	public void setMarkersColor(Monument m, Color c) {
		if (m.getCategorie() != null){			
			Coordinate coord = new Coordinate(m.getCoordonnee().getX(),m.getCoordonnee().getY());
			MapMarkerDot mmd = new MapMarkerDot(m.getNom(),coord);
			mmd.setBackColor(c);
			map.addMapMarker(mmd);		
		}
	}
	
	public void supprimeEtAjoute(ArrayList<Monument> list){
		//supprime tous les marqueurs
		map.removeAllMapMarkers();
		//ajoute un marqueur pour chaque monument
		for (Monument monument : list) {
			this.setMarkers(monument);
		}
	}
	//supprime un marqueur
	public void removeMarkers(Monument m) {
		//si la coord n'est pas null 
		if (m.getCategorie() != null){
			Coordinate coord = new Coordinate(m.getCoordonnee().getX(),m.getCoordonnee().getY());
			map.removeMapMarker(new MapMarkerDot(m.getNom(), coord));	
			System.out.println("etienne me derange et m'etrangle et je meurs");
		}
	}
	
	public void removeMarkers(){
		map.removeAllMapMarkers();
	}
	
	public void drawLine(ArrayList<Monument> monuments){
		for (int i = 0; i < monuments.size() -1 ; i++) {			
			Coordinate a = 	new Coordinate(monuments.get(i).getCoordonnee().getX(), monuments.get(i).getCoordonnee().getY());
			Coordinate b = 	new Coordinate(monuments.get(i+1).getCoordonnee().getX(), monuments.get(i+1).getCoordonnee().getY());
			List<Coordinate> route = new ArrayList<Coordinate>(Arrays.asList(a, b, b));
			map.addMapPolygon(new MapPolygonImpl(route));
		}	
	
	}
	
	public void removeLine(){
		map.removeAllMapPolygons();	
		
	}
    
    public boolean verificationValidite(){
    	boolean valid = true;
		Pattern patt = Pattern.compile("^[a-zA-Z]+(\\s?[a-zA-Z]+)*");
    	Matcher m = patt.matcher(this.monumentAjoute.getText());
    	if(!m.matches()){
    		valid=false;
    		this.erreur();
    	}
    	return valid;
    }
    
    public void erreur(){
    	this.monumentAjoute.setForeground(Color.RED); // On met l'ecriture en rouge pour qu'elle saute a l'oeil de l'utilisateur
		JOptionPane.showMessageDialog(this, "Le monument doit être valide ou non vide. ","Ajout Monument",JOptionPane.OK_OPTION);
		// On renvoie un JOptionPane qui dit que le monument n'est pas valide
		this.monumentAjoute.setText(""); // On met le champ d'erreur a 0 pour que l'utilisateur le rentre	
		this.monumentAjoute.setForeground(Color.BLACK); // On doit remettre l'ecriture en noir
		this.nomParcours.setText(""); // on remet a 0 le nom du parcours aussi
    	
    }
    
    public void nomParcoursNul(){
    	this.nomParcours.setText(""); // on remet a 0 le nom du parcours
    }
    
    public void viderMonumentAjoute(){
    	this.monumentAjoute.setText("");
    }
    
    //HashMap avec comme une clé la catégorie, et comme valeur les monuments
    public HashMap<String, ArrayList<Monument>> monumentParCategorie() throws JsonIOException, JsonSyntaxException, IOException{
    	
    	HashMap<String,ArrayList<Monument>> map = new HashMap<>();
    	
    	for (Monument monument : MonumentJson.fromJson()) {
    		String cat = monument.getCategorie();
			if (map.get(cat) == null){
				map.put(cat,new ArrayList<Monument>());
				map.get(cat).add(monument);
			}
			else {
				map.get(cat).add(monument);
			}
		}  
    	return map;
    }    
    
    
    public void ajoutParcours(Parcours p){
    	//creation d'un radioParcours à partir du parcours
    	RadioParcours radio = new RadioParcours(p.getNom(),null, p);
    	//ajout du radio au button group
    	parcButton.add(radio);
    	//ajout au listener controlDescription
    	radio.addActionListener(new ControlDescription(this));
    	leChoixParcours2.add(radio);
    	radio.doClick();

    }
    
    public void checkboxControleur() {
    	ControleurMonumentSelected cms = new ControleurMonumentSelected(this);
    	for (Component c : principaux.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		cb.addActionListener(cms);
    	}
    	for (Component c : secondaires.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		cb.addActionListener(cms);
    	}
    	for (Component c : musee.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		cb.addActionListener(cms);
    	}
    	for (Component c : publiques.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		cb.addActionListener(cms);
    	}
    	for (Component c : eglise.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		cb.addActionListener(cms);
    	}
    	for (Component c : autres.getComponents()){
    		CheckBox cb = (CheckBox) c;
    		cb.addActionListener(cms);
    	}
    }
    
    /**
     * Remet a 0 le champs de texte correspondant
     * @return le monument que l'utilisateur souhaite ajouté 
     */
    public String getNomParcours(){
    	return this.nomParcours.getText();
    }
    
    public void setOnglet(int index){
    	this.onglets.setSelectedIndex(index);
    }
    
    /**
     * M�thode qui charge les parcours stock� dans data/chemin/ probleme : on ne recupere pas bien l'ordre
     * @throws IOException 
     * @throws JsonSyntaxException 
     * @throws JsonIOException 
     */
    public void chargerParcours() throws JsonIOException, JsonSyntaxException {
    	for (File file : new File("data/chemin/").listFiles()) { // file represente les dossier qui represente un parcours
    		if (!file.getName().equals(".svn")) { // un dossier cach� .svn qu'il ne faut pas prendre en compte
    			Parcours p = new Parcours(file.getName()); 
    			ArrayList<Chemin> chemins = new ArrayList<>();
    			for (File file2 : file.listFiles()) { // file2 represente chaque chemin du parcours file
    	    		if (!file2.getName().equals(".svn")) {
    	    			try {
            				String dep = file2.getName().split(" ")[0].replace("_", " ") + ".json";
            				String arr = file2.getName().split(" ")[1].replace("_", " ");
            				Chemin ch = new Chemin(MonumentJson.fromJson(dep), MonumentJson.fromJson(arr));
            				ch.getChemin_a_prendre();
            				chemins.add(ch);			
    	    			}
    	    			catch (Exception e){
    	    				System.out.println(e);
    	    				return;
    	    			}

    	    		}
    			}
    			ArrayList<Monument> monuments = new ArrayList<>();
    			
    			p.setChemin_a_prendre(chemins);
    			monuments.add(chemins.get(0).getMonumentDep());
    			monuments.add(chemins.get(0).getMonumentArr());
    			for (int i = 1; i < chemins.size(); i++) {
    				monuments.add(chemins.get(i).getMonumentArr());
    			}
    			p.setChemin_monumen(monuments);
    	    	//creation d'un radioParcours à partir du parcours
    	    	RadioParcours radio = new RadioParcours(p.getNom(),null, p);
    	    	//ajout du radio au button group
    	    	parcButton.add(radio);
    	    	//ajout au listener controlDescription
    	    	radio.addActionListener(new ControlDescription(this));
    	    	
    	    	leChoixParcours.add(radio);
		}   	
    }
    }
    
    /**
     * M�thode qui permet de remplir les combobox dans l'onglet contrainte en fonction de la cat�gorie choisis
     * Appel� dans le controleur "ControleurCategorieContrainte"
     * @param categorie qui est la cat�gorie choisis par l'utilisateur
     */
    public void remplirComboBoxContrainte(int j, String categorie) {
    	Fenetre.sauveNousStp = false;
    	if (j==1)
    		monument1.removeAllItems();
    	else if (j==2)
    		monument2.removeAllItems();
    	switch(categorie) {
			case Monument.principaux:
				for(int i=0;i<lieuxPrincipaux.size();i++) {
					if(j==1)
						this.monument1.addItem(lieuxPrincipaux.get(i).getSonMonument().getNom());
					else if (j==2)
						this.monument2.addItem(lieuxPrincipaux.get(i).getSonMonument().getNom());
				}
				break;
			case Monument.secondaire:
				for(int i=0;i<lieuxSecondaires.size();i++) {
					if(j==1)
						this.monument1.addItem(lieuxSecondaires.get(i).getSonMonument().getNom());
					else if (j==2) 
						this.monument2.addItem(lieuxSecondaires.get(i).getSonMonument().getNom());						
				}
				break;
			case Monument.musee:
				for(int i=0;i<musees.size();i++) {
					if(j==1)
						this.monument1.addItem(musees.get(i).getSonMonument().getNom());
					else if (j==2)
						this.monument2.addItem(musees.get(i).getSonMonument().getNom());
				}
				break;
			case Monument.eglise:
				for(int i=0;i<eglises.size();i++) {
					if(j==1)
						this.monument1.addItem(eglises.get(i).getSonMonument().getNom());
					else if (j==2)
						this.monument2.addItem(eglises.get(i).getSonMonument().getNom());
				}
				break;
			case Monument.publiques:
				for(int i=0;i<lieuxPubliques.size();i++) {
					if(j==1)
						this.monument1.addItem(lieuxPubliques.get(i).getSonMonument().getNom());
					else if (j==2)
						this.monument2.addItem(lieuxPubliques.get(i).getSonMonument().getNom());
				}
				break;
			case Monument.autres:
				for(int i=0;i<lieuxAutres.size();i++) {
					if(j==1)
						this.monument1.addItem(lieuxAutres.get(i).getSonMonument().getNom());
					else if (j==2)
						this.monument2.addItem(lieuxAutres.get(i).getSonMonument().getNom());
				}
				break;
			default:
				System.out.println("Une erreur est survenue");
		}
    	Fenetre.sauveNousStp = true;
    }   
    
    public void remplirMonument1et2() {
    	for (int i = 0;i<lieuxPrincipaux.size();i++) {
    		monument1.addItem(lieuxPrincipaux.get(i).getSonMonument().getNom());
    		monument2.addItem(lieuxPrincipaux.get(i).getSonMonument().getNom());
    	}
    }
    
    public Monument getMonumentDepart() {
    	String nom = monument1.getSelectedItem().toString()+".json";
    	try {
			return MonumentJson.fromJson(nom);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public Monument getMonumentArrive() {
    	String nom = monument2.getSelectedItem().toString()+".json";
    	try {
			return MonumentJson.fromJson(nom);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public ArrayList<Monument> getMonumentsCategorieContrainte() {
    	ArrayList<Monument> al = new ArrayList<Monument>();

    	switch(boxType2.getSelectedItem().toString()) {
    	case Monument.principaux:
	    	for (Component c : principaux.getComponents()){
	    		CheckBox cb = (CheckBox) c;
	        	al.add(cb.getSonMonument());
	    	}
	    	break;
    	case Monument.secondaire:
	    	for (Component c : secondaires.getComponents()){
	    		CheckBox cb = (CheckBox) c;
	        	al.add(cb.getSonMonument());
	    	}
	    	break;
    	case Monument.musee:
	    	for (Component c : musee.getComponents()){
	    		CheckBox cb = (CheckBox) c;
	        	al.add(cb.getSonMonument());
	    	}
	    	break;
    	case Monument.publiques:
	    	for (Component c : publiques.getComponents()){
	    		CheckBox cb = (CheckBox) c;
	        	al.add(cb.getSonMonument());
	    	}
	    	break;
    	case Monument.eglise:
	    	for (Component c : eglise.getComponents()){
	    		CheckBox cb = (CheckBox) c;
	    		al.add(cb.getSonMonument());
	    	}
	    	break;
    	case Monument.autres:
	    	for (Component c : autres.getComponents()){
	    		CheckBox cb = (CheckBox) c;
	        	al.add(cb.getSonMonument());	
	    	}
	    	break;
    	}    	
        return al;
    }
    
    public boolean checkboxDebutSelect() {
    	return checkMonumDep.isSelected();
    }
    
    public boolean checkboxArrSelect() {
    	return checkMonumArr.isSelected();
    }
    
    public JComboBox<String> getBoxType2() {
    	return this.boxType2;
    }
    
    public JComboBox<String> getBoxType3() {
    	return this.boxType3;
    }
    
    public JComboBox<String> getMonument1() {
    	return this.monument1;
    }
    public JComboBox<String> getMonument2() {
    	return this.monument2;
    }
    
}