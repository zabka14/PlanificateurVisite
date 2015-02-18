package APIJson;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Modele.Monument;
/**
 * This class brings together different methods used to read a Json object from an URL and get a Json object containing information such as direction from a point to an other.
 * @author Groupe L
 *
 */
public class APIDirectionJson {
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
  
  
/**
 * This method return a Json object containing information such as directions, departure and arrival point's adresses, coordinates, and distance + time to go for a point to another.
 * @param org A Monument Object which is the departure point of the direction requested 
 * @param dest A Monument Object which is the arrival point of the direction requested
 * @return a JSON Object containing directions about the trip from org tp dest 
 * @throws IOException
 * @throws JSONException
 */
  public static JSONObject getJson(Monument org, Monument dest) throws IOException, JSONException {
	  
		String baseURL = "http://maps.googleapis.com/maps/api/directions/json?origin=";
		String midURL = "&destination=";
		String endURL = "&avoid=highways&mode=driving"+APIGeocod.fr;
		String completURL;
		String orgA = URLEncoder.encode(org.getAdresse(),"UTF-8");
		String destA = URLEncoder.encode(dest.getAdresse(),"UTF-8");
		completURL = baseURL + orgA + midURL + destA + endURL;
	  
	  
	  
    JSONObject json = readJsonFromUrl(completURL);

    
    
    return json;
  }
  
 
  /**
   * This method generate and write a Json file containing information about a Parcours Object such as total time, start and arrial points, and a list of instructions to go from the start to the arrival point under HTML format
   * @param parcours A String that will be used to name the .json file generated
   * @param json The original JSON Object obtained through APIDirectionJson.getJson 
   * @throws JSONException
   * @throws FileNotFoundException
   */
  public static void createCheminJson(String parcours, JSONObject json) throws JSONException, FileNotFoundException
  {
  	  
  	  Map<String, String> m1 = new HashMap<String, String>();
  	  Map<String, String> m2 = new HashMap<String, String>();
  	  Map<String, JSONArray> m3 = new HashMap<String, JSONArray>();
  	  List<String>  l1 = new LinkedList<String>();


  	  // Get total temps
  	  JSONObject tt1 = json.getJSONArray("routes").getJSONObject(0);
  	  JSONObject tt2 = tt1.getJSONArray("legs").getJSONObject(0);
  	  String time = tt2.getJSONObject("duration").getString("text");
  	  // Get total distance
  	  String dist = tt2.getJSONObject("distance").getString("text");

  	  // Get name end and start, plus split pour obtenir que le début
  	  String[] startA = tt2.getString("start_address").split(",");
  	  String[] endA = tt2.getString("end_address").split(",");
  	  

  	  // Get all instructions
  	  JSONArray steps = tt2.getJSONArray("steps");
  	  int size = steps.length();
  	  JSONArray list = new JSONArray();

  	  
  	  for (int foo = 0; foo < size; foo++)
  	  {
  		  JSONObject step = steps.getJSONObject(foo);
  		  String instru = step.getString("html_instructions");
  		  list.put(instru); 
  	  }
  	  // Add dans les map et list
  	  m1.put("\"Temps\"", "\""+time+"\"");
  	  m2.put("\"Distance\"", "\""+dist+"\"");
  	  m3.put("\"Instructions\"", list);
  	  l1.add(m1.toString());
  	  l1.add(m2.toString());
  	  l1.add(m3.toString());
  	  

  	  
  	  String s = l1.toString();
  	  s = s.replace("=", ":");
  	  // changé fichier nom (faire split sur l'adresse)
  	  String start = startA[0].replace(" ", "_");
  	  String end = endA[0].replace(" ", "_");
  	  
  	  String s625 = "data/chemin/"+parcours+"/"+start+" "+end+".json";
  	  System.out.println(s625);
  	  PrintWriter out = new PrintWriter(s625);
  	  out.println(s);
  	  out.close();
  	  
  }
}
  


