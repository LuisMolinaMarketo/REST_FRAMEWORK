package Libraries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;


public class httpRequest {
	public static String httpGet(String urlStr) throws IOException {
		
		URL url = new URL(urlStr);
		  HttpURLConnection conn =
		      (HttpURLConnection) url.openConnection();

		  if (((HttpURLConnection) conn).getResponseCode() != 200) {
		    throw new IOException(((HttpURLConnection) conn).getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  ((HttpURLConnection) conn).disconnect();
		  return sb.toString();
		}
	public static String httpGetProxy(String urlStr,String proxyUri, int proxyPort, String proxyUsername,String  proxyPassword) throws IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUri, proxyPort));
		URLConnection conn = new URL(urlStr).openConnection(proxy);
		
		  if (((HttpURLConnection) conn).getResponseCode() != 200) {
		    throw new IOException(((HttpURLConnection) conn).getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  ((HttpURLConnection) conn).disconnect();
		  return sb.toString();
		}
	public static String httpPost(String urlStr, String jsonPost) {
		URL url;
		String j=jsonPost;
		try {
			url = new URL(urlStr);
			OutputStreamWriter osw;
			HttpURLConnection conn =
			      (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			OutputStream os = conn.getOutputStream();
			os.write(j.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	 
			conn.disconnect();		
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

		}
	
	public static String httpPostProxy(String urlStr, String jsonPost, String proxyUri, int proxyPort, String proxyUsername,String  proxyPassword) {
		URL url;
		String j=jsonPost;
		try {

			url = new URL(urlStr);
			OutputStreamWriter osw;

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUri, proxyPort));
			URLConnection conn = new URL(urlStr).openConnection(proxy);
			
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			((HttpURLConnection) conn).setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			OutputStream os = conn.getOutputStream();
			os.write(j.getBytes());
			os.flush();
			
			if (((HttpURLConnection) conn).getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ ((HttpURLConnection) conn).getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	 
			((HttpURLConnection) conn).disconnect();		
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

		}
}
