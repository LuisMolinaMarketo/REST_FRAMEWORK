package firstTest;

	import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import Libraries.ConfigLoader;
import Libraries.csvReader;
import Libraries.httpRequest;
import Libraries.json;



public class getLeadfromCSV {

	public static void main(String[] args) {

		try{
			csvReader r = new csvReader("csv\\leads.csv",",");
			String[] header = r.getHeader();
			String[][] result = r.getContent();
			String postValues = json.ArrayToJson(header,result).toString();
			
			ConfigLoader loader =  new ConfigLoader("soap.properties");
	    	String marketoUserId = loader.getData("marketoUserId");
	    	String marketoSecretKey = loader.getData("marketoSecretKey");
	    	String proxyUri = loader.getData("proxyUri");
	    	int proxyPort = Integer.parseInt(loader.getData("proxyPort"));
	    	String proxyUsername = loader.getData("proxyUsername");
	    	String proxyPassword = loader.getData("proxyPassword");

	    	String mkt_instance = loader.getData("mkt_instance");
	    	String uri = loader.getData("Uri");

	    	//Boolean useProxy= (loader.getData("useProxy").equalsIgnoreCase("true"));
	    	Boolean useProxy = Boolean.parseBoolean(loader.getData("useProxy"));
	    	String method = loader.getData("Method");
	    	
	    	String token = "";
	    	httpRequest http;
	    	String response;
	    	
	    	
	    	//	Get Access Token
	    	String urlStr=mkt_instance+"/identity/oauth/token?grant_type=client_credentials" + 
	    			"&client_id=" + marketoUserId + "&client_secret=" + marketoSecretKey;
	    	http = new httpRequest();
	    	
	    	if(useProxy)
	    		response =httpRequest.httpGetProxy(urlStr,proxyUri,proxyPort,proxyUsername,proxyPassword);
	    	else
	    		response =httpRequest.httpGet(urlStr);
			

			JSONParser parser=new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(response);
	        
	        token= (String) obj.get("access_token");
	    	
	    	//urlStr="https://363-kzp-741.mktorest.com/rest/v1/lead/1.json?access_token=" + token;
	    	urlStr=mkt_instance + uri + "?access_token=" + token;
		
	    	http = new httpRequest();
	    	if (method=="get"){
	    		if(useProxy)
	    			response =httpRequest.httpGetProxy(urlStr,proxyUri,proxyPort,proxyUsername,proxyPassword);
	    		else
	    			response =httpRequest.httpGet(urlStr);
	    	}else{
	    		if(useProxy)
	    			response =httpRequest.httpPostProxy(urlStr,postValues,proxyUri,proxyPort,proxyUsername,proxyPassword);
	    		else
	    			response =httpRequest.httpPost(urlStr,postValues);
	    	}
			System.out.println(response); 
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



