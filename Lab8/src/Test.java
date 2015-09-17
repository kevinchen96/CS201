import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;
public class Test {
	public static void main(String args[]){
		String s = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22";
		System.out.println("Type a ticker: ");
		Scanner S = new Scanner(System.in);
		s = s + S.next()+ "%22)&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		URL url = null;
		Scanner A = null;
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
		FileWriter fw = null;
		try {
			fw = new FileWriter("./stats.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		String line = null;
		try {
			while((line = in.readLine()) != null) {
			    pw.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
		try {
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File fXmlFile = new File("./stats.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
	 
		//NodeList nodes = doc.getDocumentElement().getChildNodes();
		System.out.println(doc.getDocumentElement().getElementsByTagName("Name").item(0).getTextContent() + " has a year low of: " + doc.getDocumentElement().getElementsByTagName("YearLow").item(0).getTextContent() + " and a year high of: " + doc.getDocumentElement().getElementsByTagName("YearHigh").item(0).getTextContent() + ". It's current market capitalization is " + doc.getDocumentElement().getElementsByTagName("MarketCapitalization").item(0).getTextContent() + ".");
		
	}
}
