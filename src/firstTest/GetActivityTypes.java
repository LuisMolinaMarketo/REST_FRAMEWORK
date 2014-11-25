package firstTest;

import Marketo.Rest;
import Solutions.ParserActivityTypes;



public class GetActivityTypes {

	public static void main(String[] args) {
			
			Rest rest = new Rest("config\\BNZ.properties");
	    	rest.getToken();
	    	String response = rest.getActivityTypes();
			System.out.println(response); 
			ParserActivityTypes parser = new ParserActivityTypes(response);
	}
}



