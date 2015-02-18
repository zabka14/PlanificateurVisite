package APIJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import Modele.Coordonnees;
/**
 * This class brings together different methods used to read a Json object from an URL and get a Json object containing information about the precise adress of a given point.
 * @author Groupe L
 *
 */
public class APIGeocod {
	
	public final static String fr = "&language=fr-FR";
	
	/**
	 * 
	 * @param rd a Reader object
	 * @return a String, content of the rd Reader given as a param
	 * @throws IOException
	 */
  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
  /**
   * 
   * @param url an asbolute URL well formated (cf Google API Doc)
   * @return the JSON Object produce by the request send at the URL taken as param. 
   * @throws IOException 
   * @throws JSONException if the JSON Object seems to be wrongly fomated
   */
  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  /* Retourne un objet de type JSONObject
   * Prend en paramètre un String
   */
  /**
   * The getJSON method output a JSON Object containing precise information such as coordinates, full adress with informations surch as :
   * <ul>
   * <li> Street Number (if any)
   * <li> Route 
   * <li> Locality
   * <li> Level 2 Administrative Area (In France this would be the "departement"
   * <li> Level 1 Administrative Area (In France this would be the "region"
   * <li> Country
   * <li> Postal code
   * <li> Location :
   * 	<ul>
   * 	<li> Latitude (Double)
   * 	<li> Longitude (Double)
   * 	</ul>
   * </ul>
   * The monument need to been in Paris, du to the structur of the method (append "Paris" at the end of the adress automaticly)
   * @param org a String containing the approximative adress of a monument in Paris.
   * @return a JSON Object containing information about the monument at the adress given as param. The structure of the JSON outputed can be found on the Google API Doc.
   * @throws IOException
   * @throws JSONException if the JSON Object seems to be wrongly fomated
   */
  public static JSONObject getJSON(String org) throws IOException, JSONException {
	  
		String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String completURL;
		completURL = baseURL + org+"+Paris"+APIGeocod.fr;
		JSONObject json = readJsonFromUrl(completURL.replace(" ", "+"));
    
    
 
    return json;
  }
  
 /**
  * This return the Coordonnees Object of a given adress. 
  * @param org a String containing the approximative adress of a monument in Paris.
  * @return a Coordonnees Object
  * @throws IOException
  * @throws JSONException
  */
  public static Coordonnees getCoordones(String org) throws IOException, JSONException {
	  
		String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String completURL;
		completURL = baseURL +org+"+Paris"+APIGeocod.fr;
	  	  
		JSONObject jsonObject = readJsonFromUrl(completURL.replace(" ", "+"));
  			
			JSONObject obj1 = jsonObject.getJSONArray("results").getJSONObject(0);
			// Dans la clé "routes" :
			JSONObject obj2 =  obj1.getJSONObject("geometry").getJSONObject("location");
			// Dans la clé "location" :		    		
			Double lat =  obj2.getDouble("lat");
			Double lng =  obj2.getDouble("lng");

			System.out.println(lat);
			System.out.println(lng);
						
			Coordonnees coord = new Coordonnees(lat, lng);
  
  return coord;
}
  
  /**
   * This return coordinates for a given JSON object related to a monument
   * @param jsonObject containing monument information (from Geocod API)
   * @return Coordonnees object
   * @throws IOException
   * @throws JSONException
   */
  public static Coordonnees getCoordones(JSONObject jsonObject) throws IOException, JSONException {		
			JSONObject obj1 = jsonObject.getJSONArray("results").getJSONObject(0);
			// Dans la clé "routes" :
			JSONObject obj2 =  obj1.getJSONObject("geometry").getJSONObject("location");
			// Dans la clé "location" :		    		
			Double lat =  obj2.getDouble("lat");
			Double lng =  obj2.getDouble("lng");
			System.out.println(lat);
			System.out.println(lng);						
			Coordonnees coord = new Coordonnees(lat, lng); 
  return coord;
} 
  /**
   * Return the adress of a monument with a given String
   * @param org The name of the monument you want to get the adress from
   * @return String : the adress of the given monument
   * @throws IOException
   * @throws JSONException
   */
	public static String getAdresse(String org) throws IOException, JSONException  {
		String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String completURL;
		completURL = baseURL +org+"+Paris"+APIGeocod.fr;
	  	  
		JSONObject json = readJsonFromUrl(completURL.replace(" ", "+"));
		
		return json.getJSONArray("results").getJSONObject(0).getString("formatted_address");
	} 
	
	
	  /**
	   * Return the adress of a monument with a given Coordinate Object
	   * @param coord The Coordinate object of the monument you want to get the adress from
	   * @return String : the adress of the given monument
	   * @throws IOException
	   * @throws JSONException
	   */
	public static String getAdresse(Coordinate coord) throws IOException, JSONException  {
		String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + coord.getLat() + "," + coord.getLon(); 
		JSONObject json = readJsonFromUrl(baseURL);  
		return json.getJSONArray("results").getJSONObject(0).getString("formatted_address");
	}
	
	
	/**
	   * Return the adress of a monument with a given JSON Object
	   * @param json The JSON object of the monument you want to get the adress from
	   * @return String : the adress of the given monument
	   * @throws IOException
	   * @throws JSONException
	   */
	public static String getAdresse(JSONObject json) throws JSONException {
		return json.getJSONArray("results").getJSONObject(0).getString("formatted_address");
	}
  
	/**
	 * This function returns the complete name of a monument using a String as parameter
	 * @param org A String that will be used as monument name for a Geocode API Research. 
	 * @return A string with the full name of the monument
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String getNom(String org) throws IOException, JSONException  {
		String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String completURL;
		completURL = baseURL +org;	  	  
		JSONObject json = readJsonFromUrl(completURL.replace(" ", "+"));
		
		return json.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(0).getString("long_name");
	}
/**
 * This function returns the complete name of a monument using a JSON Object as parameter
 * @param json JSON Object obtained through Geocode API
 * @return A string with the full name of the monument
 * @throws IOException
 * @throws JSONException
 */
	public static String getNom(JSONObject json) throws IOException, JSONException  {	
		return json.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(0).getString("long_name");
	} 

	/**
	 * This function check if the monument you're looking for in inside a squared area around Paris, and if so, look if the long name of the monument isn't "Paris". Indeed, the Geocode API return the center of Paris for a research that doesn't existe (i.e : "azertyuiop Paris" will return a long name = "Paris").
	 * @param json Un monument récupéré par une autre méthode, un fichier JSON issue de l'API Geocode !
	 * @return true si le monument existe, false si le monument est invalide
	 * @throws JSONException
	 */
	public static Boolean checkIfPOI(JSONObject json) throws JSONException
	{	
		String lname = json.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(0).getString("long_name");
		double coordX = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		double coordY = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

		if ((48.807931<coordX) && (coordX<48.913638) && (2.220998 < coordY) && (coordY<2.433515))
		{
			if (lname.equals("Paris"))
			{return false;}
			else return true;
		}
		else return false;
		
		
	
	
	}
	
	
	
	
}


