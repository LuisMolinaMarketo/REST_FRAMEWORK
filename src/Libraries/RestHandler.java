package Libraries;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestHandler {
	Boolean isProxy = false;
	String 	mkt_instance="",
			marketoUserId="",
		 	marketoSecretKey="",
			uri="",
			method="";
	Boolean useProxy= false;
	String 	proxyUri="";
	int 	proxyPort= 80;
	String 	proxyUsername="",
			proxyPassword="";
	String 	token = "";
	
	public RestHandler(String fileName){
		ConfigLoader loader =  new ConfigLoader(fileName);
    	marketoUserId = loader.getData("marketoUserId");
    	marketoSecretKey = loader.getData("marketoSecretKey");
    	proxyUri = loader.getData("proxyUri");
    	proxyPort = Integer.parseInt(loader.getData("proxyPort"));
    	proxyUsername = loader.getData("proxyUsername");
    	proxyPassword = loader.getData("proxyPassword");

    	mkt_instance = loader.getData("mkt_instance");
    	uri = loader.getData("Uri");

    	//Boolean useProxy= (loader.getData("useProxy").equalsIgnoreCase("true"));
    	isProxy = Boolean.parseBoolean(loader.getData("useProxy"));
    	method = loader.getData("Method");
    }
	
	public String getToken(){
		httpRequest http;
		String response = "";
	
		//	Get Access Token
		String urlStr=mkt_instance+"/identity/oauth/token?grant_type=client_credentials" + 
				"&client_id=" + marketoUserId + "&client_secret=" + marketoSecretKey;
		http = new httpRequest();
		
		try {
			if(useProxy)
				response = httpRequest.httpGetProxy(urlStr,proxyUri,proxyPort,proxyUsername,proxyPassword);
			else
				response = httpRequest.httpGet(urlStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
		try {
			JSONParser parser=new JSONParser();
			JSONObject obj;
			obj = (JSONObject) parser.parse(response);
	        token= (String) obj.get("access_token");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return token;
	}

	public String get(String urlStr){
		String response="";
		urlStr = mkt_instance + urlStr + "?access_token=" + token;
		try {
		if(isProxy)
			response =httpRequest.httpGetProxy(urlStr,proxyUri,proxyPort,proxyUsername,proxyPassword);
		else
			response =httpRequest.httpGet(urlStr);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}
	
	public String post(String urlStr, String postValues){
		String response="";
		urlStr = mkt_instance + urlStr + "?access_token=" + token;
		if(useProxy)
			response =httpRequest.httpPostProxy(urlStr,postValues,proxyUri,proxyPort,proxyUsername,proxyPassword);
		else
			response =httpRequest.httpPost(urlStr,postValues);
		return response;

	}
		

}