package pl.vgtworld.civcrawler.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

abstract class PageLoader {
	
	public static String load(String resourceLocation) throws IOException {
		InputStream resourceAsStream = PageLoader.class.getResourceAsStream(resourceLocation);
		BufferedReader bufferedReader = null;
		StringBuilder builder = new StringBuilder();
		
		String line;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return builder.toString();
	}
	
}
