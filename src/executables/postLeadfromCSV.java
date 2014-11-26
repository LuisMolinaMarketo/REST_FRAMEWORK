package executables;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import Libraries.ConfigLoader;
import Libraries.csvReader;
import Libraries.httpRequest;
import Libraries.json;
import Marketo.Rest;

public class postLeadfromCSV {

	public static void main(String[] args) {
		csvReader r = new csvReader("csv\\leads.csv", ",");
		String[] header = r.getHeader();
		String[][] result = r.getContent();
		String postValues = json.ArrayToJson(header, result).toString();

		Rest rest = new Rest("config\\BNZ.properties");
		rest.getToken();
		String response = rest.postLeads(postValues);
		System.out.println(response);

	}
}
