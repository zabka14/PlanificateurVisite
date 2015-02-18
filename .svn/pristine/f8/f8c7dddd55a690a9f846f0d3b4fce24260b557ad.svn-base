package APIJson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;





import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





import Modele.Monument;
/**
 * This class brings together different methods used to read a Json object from an URL and get a Json object containing information such a distances between a matrix of address/point.
 * @author Groupe L
 *
 */
public class APIMatrixJson {

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
   * @param url an absolute URL well formated (cf Google API Doc)
   * @return the JSON Object produce by the request send at the URL taken as param. 
   * @throws IOException 
   * @throws JSONException if the JSON Object seems to be wrongly formated
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

  /**
   * The getDistanceMatrix return a json object containing distances betwen a given list of monument. The Json outputed contains distance between each monument, and time to travel as well. 
   * @param listM an ArrayList of Monument Object. 
   * @return a JSON Object containing information about distances between a matrix of address/point
   * @throws IOException
   * @throws JSONException
   */
  public static JSONObject getDistanceMatrix(ArrayList<Monument> listM) throws IOException, JSONException {
    
	  int size = listM.size();
	  String monuString ="";
	  for(int foo =0; foo < size; foo++)
	  {
		  //Encode les noms avec UTF, sinon erreur au niveau de l'api
		  monuString += URLEncoder.encode(listM.get(foo).getNom(),"UTF-8");
		  if (foo == size-1){
			  monuString += "+Paris";
		  }
		  else {
			  monuString += "+Paris|";
		  }

	  }
    
    String baseURL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
	String midURL = "&destinations=";
	String endURL = "&mode=driving"+APIGeocod.fr;
	String URL = baseURL+monuString+midURL+monuString+endURL;
	JSONObject json = readJsonFromUrl(URL.replace(" ","+"));
	System.out.println(URL.replace(" ","+"));
	//JSONObject json = readJsonFromUrl("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Place+de+la+Concorde|Place+des+Vosges|Pont+Alexandre+III|&destinations=Place+de+la+Concorde|Place+des+Vosges|Pont+Alexandre+III|&mode=driving&language=fr-FR");

	return json;
  }
  
  /**
   * This method return a matrix containing distances betwen each of a given list of monuments.
   * @param listM A list of Monument Object 
   * @return A HashMap, a Matrix containing distances betwen each element of the listM list. For exemple, for a list of A, B and C monument, it will return distan between A&A, A&B, A&C, B&A, B&B, B&C, C&A, C&B, C&C 
   * @throws IOException
   * @throws JSONException
   */
  public static HashMap<Monument, HashMap<Monument,Integer>> getDistanceMap(ArrayList<Monument> listM) throws IOException, JSONException{
	  
	  JSONObject json = APIMatrixJson.getDistanceMatrix(listM);
	  
	  HashMap<Monument, HashMap<Monument,Integer>> matrix = new HashMap<Monument, HashMap<Monument,Integer>>();
	  for (int i = 0; i < listM.size(); i++) {
		HashMap<Monument, Integer> test = new HashMap<Monument,Integer>(); 
		for (int j = 0; j < listM.size(); j++) {
			JSONArray  array = json.getJSONArray("rows").getJSONObject(i).getJSONArray("elements");
			JSONObject obj = array.getJSONObject(j);
			int minute = obj.getJSONObject("duration").getInt("value");
			test.put(listM.get(j), minute);				
		}
		matrix.put(listM.get(i), test);
	}
	  return matrix;
	}

}

