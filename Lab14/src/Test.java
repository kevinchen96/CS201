import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "http://gdata.youtube.com/feeds/api/standardfeeds/most_popular?v=2&alt=json";
		URL url = null;
		try {
			url = new URL(s);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream input = null;
		try {
			input = url.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		String line = null;
		try {
			line = in.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JSONObject json = null;
		try {
			json = new JSONObject(line);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			JSONObject feed = (JSONObject)json.get("feed");
			JSONArray entry = (JSONArray)feed.get("entry");
			for(int i = 0; i < 10; i++){
				System.out.print(((JSONObject) ((JSONObject) entry.get(i)).get("title")).get("$t") + " has " + ((JSONObject) ((JSONObject) entry.get(i)).get("yt$statistics")).get("viewCount") + " views!");
				System.out.println();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
