package nl.elec332.lib.java.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * Created by Elec332 on 31-10-2017.
 */
public class ConnectionHelper {

	public static HttpURLConnection openConnectionTo(String url) throws MalformedURLException, IOException {
		URL obj = new URL(url);
		return (HttpURLConnection) obj.openConnection();
	}

	public static void makePostRequest(HttpURLConnection con, String urlParameters) throws IOException {
		try {
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
		} catch (ProtocolException e){
			throw new RuntimeException(e);
		}
	}

}
