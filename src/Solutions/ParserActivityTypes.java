package Solutions;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParserActivityTypes {
	public ParserActivityTypes(String str){
		String descrip ="";
    	PrintWriter writer = null;
		try {
			writer = new PrintWriter("activities.html", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	writer.println("<style>h1{font-size: 20px;} table { border-collapse: collapse;} table td,table th{ border: 1px solid black;width:200px;padding: 0 10px;}</style>");
    	writer.println("<style>.title{margin-top:30px; margin-bottom: 10px}</style>");
		
		JsonElement jE = new JsonParser().parse(str);;
		JsonObject js = jE.getAsJsonObject();
		JsonArray ar = js.getAsJsonArray("result");
		System.out.print(ar.size());
	    for(int i = 0 ; i < ar.size(); i++){
			JsonObject js2 = (JsonObject) ar.get(i);
			JsonElement id = js2.get("id");
			JsonElement name = js2.get("name");
			JsonElement description = js2.get("description");
	    	writer.println("<h1>Activity " + name + " (id: "+ id +")</h1>");
	    	if (description==null){
	    		descrip = "";
	    	}else{
	    		descrip=description.getAsString();
	    	}
    		writer.println("<p><b>Description:</b> " + descrip + "<p>");
	    	writer.println("<p class='title'><b> Primary Attributes:</b></p>");

	    	
	    	JsonObject primaryAttributes = (JsonObject) js2.get("primaryAttribute");
			System.out.println(primaryAttributes);
			if (primaryAttributes!=null){
		    	JsonElement name_P_attr = (primaryAttributes).get("name");
				JsonElement dataType_P_attr = primaryAttributes.get("dataType");
		    	writer.println("<table>");
		    	writer.println("<tr><th>Name</th><th>Type</th></tr>");			    writer.println("<tr><td>" + name_P_attr.getAsString() + "</td><td>" + dataType_P_attr.getAsString() + "</td></tr>");
		    	writer.println("</table>");

			}
	    	writer.println("<p class='title'><b> Attributes:</b></p>");
		
			JsonArray attributes = js2.getAsJsonArray("attributes");
			if (attributes!=null){
		    	writer.println("<table style='margin-bottom: 50px;'>");
		    	writer.println("<tr><th>Name</th><th>Type</th></tr>");	
		    	for(int j = 0 ; j < attributes.size(); j++){
		    		JsonObject js3 = (JsonObject) attributes.get(j);
		    		JsonElement name_attr = js3.get("name");
		    		JsonElement dataType_atrr = js3.get("dataType");
		    		writer.println("<tr><td>" + name_attr.getAsString() + "</td><td>" + dataType_atrr.getAsString() + "</td></tr>");
		    	}
			}
	    	writer.println("</table>");
			System.out.println(attributes);
			//System.out.println(id + name + description);
	    }
	    
    	
    	//writer.println("The second line");
    	writer.close();
	}
}

