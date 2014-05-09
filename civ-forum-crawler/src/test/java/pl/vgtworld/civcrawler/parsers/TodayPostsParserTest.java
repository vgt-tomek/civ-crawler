package pl.vgtworld.civcrawler.parsers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class TodayPostsParserTest {

	@Test
	public void shouldFindPostsInExamplePage() throws IOException {
		TodayPostsParser parser = new TodayPostsParser();
		String page = PageLoader.load("/todayPosts.txt");
		
		PostDto[] parse = parser.parse(page);
		
		assertThat(parse).hasSize(21);
		assertThat(parse[0].getMessageId()).isEqualTo(320356);
		assertThat(parse[20].getMessageId()).isEqualTo(320329);
	}
	
}
