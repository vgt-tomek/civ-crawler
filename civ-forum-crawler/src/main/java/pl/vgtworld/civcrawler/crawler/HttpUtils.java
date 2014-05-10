package pl.vgtworld.civcrawler.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class HttpUtils {
	
	public static String downloadUrl(String address) throws IOException {
		BufferedReader in = null;
		try {
			URL url = new URL(address);
			String line = null;
			StringBuilder builder = new StringBuilder();
			in = new BufferedReader(new InputStreamReader(url.openStream(), "ISO-8859-2"));
			while ((line = in.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
	
}
