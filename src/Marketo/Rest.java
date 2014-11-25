package Marketo;

import Libraries.RestHandler;

public class Rest {
	RestHandler handler;
	
    public Rest(String configFileName){
    	handler = new RestHandler("soap.properties");
    }
    
	public String getToken(){
		return handler.getToken();
    }
	
	public String getActivityTypes(){
		String response = "";
    	String urlStr="/rest/v1/activities/types.json"; 
    	response = handler.get(urlStr);
		return response;
	}
}