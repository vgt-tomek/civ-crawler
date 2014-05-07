package pl.vgtworld.civcrawler.parsers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class TodayPostsParserTest {

	@Test
	public void shouldFindPostsInExamplePage() throws IOException {
		TodayPostsParser parser = new TodayPostsParser();
		String page = loadPageIntoString();
		
		PostDto[] parse = parser.parse(page);
		
		assertThat(parse).hasSize(21);
		assertThat(parse[0].getMessageId()).isEqualTo(320356);
		assertThat(parse[20].getMessageId()).isEqualTo(320329);
	}
	
	private String loadPageIntoString() throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("/todayPosts.txt");
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
