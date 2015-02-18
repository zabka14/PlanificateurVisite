package APIJson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import Modele.Monument;
/**
 * This class brings together different methods for writing .json file from Monument object and create Monument Object from a .json file.
 * @author Groupe L
 *
 */
public class MonumentJson {
		
	/**
	 * The toJson method write a .json file from a Monument Object.
	 * @param m a Monument Object
	 * @throws IOException
	 */
	public static void toJson(Monument m) throws IOException{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream("data/monuments/" + m.getNom() + ".json"), "UTF-8"));
		writer.beginArray();
		gson.toJson(m,Monument.class,writer);	
		writer.endArray();
		writer.close();

	}
	
	/**
	 * The fromJson method read all .json files in the /data/monument and create a Monument Object for each one of the file contained in the directory
	 * @return An ArrayList<Monument> of Monument Object
	 * @throws JsonIOException
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	public static ArrayList<Monument> fromJson() throws JsonIOException, JsonSyntaxException, IOException {
    	ArrayList<Monument> monuments = new ArrayList<Monument>();

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
	     
        File dir = new File("data/monuments/");
        //ne prend que les fichiers qui se finissent par l'extension .json
        File[] files = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".json");
			}
		});
        //Pour chaque fichier dans le repertoire data
        for (File fichier : files) {
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(fichier), "UTF-8"));
            reader.beginArray();
        
			Monument m = gson.fromJson(reader, Monument.class); //crée un Monument à partir du Json
			monuments.add(m); //ajoute le monument à la liste	
			reader.endArray();
			reader.close();
		}
            
        return monuments;
	}
	
/**
 * This method load a Json file containing information about a specific monument, and re-create a Monument Object
 * @param name A name of a Monument
 * @return a Monument Object
 * @throws JsonIOException
 * @throws JsonSyntaxException
 * @throws IOException
 */
	public static Monument fromJson(String name) throws JsonIOException, JsonSyntaxException, IOException {
    	new ArrayList<Monument>();

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
	     
        File file = new File("data/monuments/" + name);

        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        reader.beginArray();
        
		Monument m = gson.fromJson(reader, Monument.class); //crée un Monument à partir du Json
         
        return m;
	}	
	

}
