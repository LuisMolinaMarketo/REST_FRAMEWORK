package firstTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class json {
	public static JSONObject ArrayToJson(String[] header, String[][] content) {
		JSONArray jsonArray = new JSONArray();
		JSONObject js = new JSONObject();
		JSONObject js2 = new JSONObject();
		int i=0;
		int j=0;
	    for (String[] m : content) {
	    	js = new JSONObject();
	    	j=0;
	    	Boolean nullValue=false;
		    for (String h : header) {
		    	if (header[j]!=null && content[i][j]!=null){
		    		nullValue=true;
		    	js.put(header[j],content[i][j]);
		    	j++;
		    	}
		    }
		    if (nullValue)
	    	jsonArray.add(js);
	    	i++;
	    }
	    String.valueOf(js);
	    js2.put("input",jsonArray);
		return js2;
	}
}
