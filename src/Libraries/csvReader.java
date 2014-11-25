package Libraries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class csvReader {
 
	String csvFile;
	String cvsSplitBy;
	public csvReader(String urlStr,String splitStr){
		csvFile = urlStr;
		cvsSplitBy = splitStr;
	}
	
	public String[] getHeader() {
		String[] header = new String[20];
		BufferedReader br = null;
		String line = "";
		int i=0;
		int j=0;
		try {		
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);
				if (i==0){
					for (String s : country) {
						header[j]=s;
						j++;
					}
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return header;		
	
	}
	
	public String[][] getContent() {
		String[][] content = new String[1000][20];
		BufferedReader br = null;
		String line = "";
		String[] header = new String[20];
		int i=0;
		int j=0;
		try {		
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);
				if (i>0){
					j=0;
				    for (String s : country) {
				    	content[i-1][j]=s;
				    	j++;
				    }		
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return content;
	
	}
 /* public String[][] run() {
 
	BufferedReader br = null;
	String line = "";
	String[] header = new String[20];
	String[][] content = new String[1000][20];
	int i=0;
	int j=0;
	try {
 
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			String[] country = line.split(cvsSplitBy);
			if (i==0 && opt=="header" ){
			    for (String s : country) {
			    	header[j]=s;
			    	j++;
			    }
			}else if(i>0 && opt=="content"){
				j=0;
			    for (String s : country) {
			    	content[i][j]=s;
			    	j++;
			    }				
			}
			i++;
		        // use comma as separator
 
			//System.out.println("Country [code= " + country[4] 
             //                    + " , name=" + country[5] + "]");
	}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	
	System.out.println("Done");
	return content;
  }
  */
 
}