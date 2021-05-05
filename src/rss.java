import java.io.*;
import java.net.*;

public class rss {
	
	public static void main(String[ ] args) {
		System.out.println(readRSS("https://www.ncsc.gov.uk/api/1/services/v1/all-rss-feed.xml"));
	}
	
	public static String readRSS(String urlAddress) {
		try {
			URL rssUrl = new URL(urlAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
			String sourceCode =" ";
			String line;
			while ((line = in.readLine())!=null) {
					if (line.contains("<title>")) {
					int firstPos = line.indexOf("<title>");
					String temp = line.substring(firstPos);
					temp = temp.replace("<title>"," ");
					int lastPos = temp.indexOf("</title>");
					temp = temp.substring(0,lastPos);
					sourceCode += temp+ "\n";
		}
	}
		in.close();
		return sourceCode;
	}	catch (MalformedURLException ue) {
		System.out.println("Malformed URL");
	} catch (IOException ioe) {
		System.out.println("Something went wrong reading the contents");
	}
	return null;
		
		
	}
}