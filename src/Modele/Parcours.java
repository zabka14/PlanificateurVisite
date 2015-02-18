package Modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jgrapht.alg.HamiltonianCycle;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.json.JSONException;

import APIJson.APIMatrixJson;
/**
 * 
 * @author Group L
 * This class put together handlers for Parcours Object. Parcours are multiple Chemin Object put together, sorted in a ordered manner (After graph algorithm application)
 */
public class Parcours {

	private String nom;
	private ArrayList<Chemin> chemin_a_prendre;
	private ArrayList<Monument> chemin_monument;
	
	public Parcours(String name){
		this.nom = name;
	}
		
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Chemin> getChemin_a_prendre() {
		return chemin_a_prendre;
	}
	public ArrayList<Monument> getChemin_monument() {
		return chemin_monument;
	}
	
	public void setChemin_a_prendre(ArrayList<Chemin> chemin_a_prendre) {
		this.chemin_a_prendre = chemin_a_prendre;
	}
	
	public void setChemin_monumen(ArrayList<Monument> chemin_monument) {
		this.chemin_monument = chemin_monument;
	}

	/**
	 * This method set up the "chemin_a_prend" attribute. It's applying a mathematical algorithm (graph stuff) on the Monument List, and sorted them so the total path going through each monument is the shortest/fastest possible.
	 * @param listeMonument A list of Monument Object, no need to be sorted by any kind of parameters
	 * @throws IOException
	 * @throws JSONException
	 */
	public void creationChemin(ArrayList<Monument> listeMonument) throws IOException, JSONException{
		//creation d'un chemin à partir d'une liste de monument
		SimpleWeightedGraph<Monument, DefaultWeightedEdge>  graph = this.creationGraph(listeMonument);
		this.chemin_a_prendre = plusCoursCycle(graph);
	}
	/**
	 * This method used the Matrix API to create a matrix of distances between each monument given as parameters, with those data, this method create a non oriented valuated graph of the Monuments. Each edge represents the distance between each Monuments.
	 * @param monuments A ArrayList of Monuments Object.
	 * @return A SimpleWeightGraph Object : a graph with ordered monuments on it. 
	 * @throws IOException
	 * @throws JSONException
	 */
	//crée un graph orienté qui a pour sommet des monuments à partir d'une liste de monument
	public SimpleWeightedGraph<Monument, DefaultWeightedEdge> creationGraph(ArrayList<Monument> monuments) throws IOException, JSONException{
		SimpleWeightedGraph<Monument, DefaultWeightedEdge> g = new SimpleWeightedGraph<Monument,DefaultWeightedEdge>(DefaultWeightedEdge.class);

		for (Monument monument : monuments) {
			g.addVertex(monument);
		}
		
		//récupere une matrice permettant d'avoir le temps entre chaque monument
		HashMap<Monument, HashMap<Monument,Integer>> mapTemps = APIMatrixJson.getDistanceMap(monuments);
		int temps;
		//pour chaque sommet il faut sa distance avec les autres sommets
		for (Monument sommet : g.vertexSet()) {
			for (Monument autre_sommet : g.vertexSet()) {	
				//creation de l'arc entre les deux sommets
				if (!sommet.equals(autre_sommet)){
					g.addEdge(sommet, autre_sommet);
					temps = mapTemps.get(sommet).get(autre_sommet);
					g.setEdgeWeight(g.getEdge(sommet, autre_sommet), temps);
				}
			}
			
		}		
		return g;
	}
	/**
	 * Based on a SimpleWeightedGraph Object (obtained through "creationGraph" method). In this graph which isn't oriented but valuated, the method try to find an optimal cycle.
	 * @param g A SimpleWeightedGraph<Monument, DefaultWeightedEdge> Object (see creationGraph method)
	 * @return An ArrayList of Chemin Objects
	 */
	//création du plus cours cycle qui parcours tous les monument, retourne une liste de ces chemin
	public ArrayList<Chemin> plusCoursCycle(SimpleWeightedGraph<Monument, DefaultWeightedEdge> g){
		
		List<Monument> monuments =  HamiltonianCycle.getApproximateOptimalForCompleteGraph(g);
		this.chemin_monument = new ArrayList<Monument>(monuments);

		ArrayList<Chemin> listeChemin = new ArrayList<Chemin>();
		
		for (int i = 0 ; i < monuments.size() - 1 ; i++){
			Monument monumentD = monuments.get(i); //monument de depart du chemin
			Monument monumentA = monuments.get(i+1); //monument d'arrive du chemin
			Chemin chemin = new Chemin(monumentD,monumentA);
		
			listeChemin.add(chemin);
		}
		return listeChemin;
	}	
	
}
