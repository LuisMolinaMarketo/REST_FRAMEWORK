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
	
	public String postLeads(String postArguments){
		String response = "";
		String urlStr="/rest/v1/leads.json";
    	response = handler.post(urlStr,postArguments);
		return response;
	}	
}