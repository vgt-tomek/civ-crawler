package pl.vgtworld.civcrawler.parsers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class ThreadParserTest {

	@Test
	public void shouldFindRequiredDataInThreadPage() throws IOException, ParseException {
		ThreadParser parser = new ThreadParser();
		String page = PageLoader.load("/thread.txt");
		
		ThreadDto result = parser.parse(page);
		
		assertThat(result).isNotNull();
		assertThat(result.getThreadId()).isEqualTo(20999);
		assertThat(result.getPage()).isEqualTo(2);
		assertThat(result.getTitle()).isEqualTo("civ.org.pl forums > Space 4X - Ogólne > Galactic Civilization III");
	}
}
