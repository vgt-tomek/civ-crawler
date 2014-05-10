package pl.vgtworld.civcrawler.services.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ThreadWithNewPostsTest {

	@Test
	public void shouldSplitNameIntoTitleAndBoard() {
		ThreadWithNewPosts thread = new ThreadWithNewPosts("first > second > third", 1);
		
		assertThat(thread.getName()).isEqualTo("third");
		assertThat(thread.getBoard()).isEqualTo("second");
	}
	
	@Test
	public void shouldPutFullStringIntoNameWhereThereIsNoSeparator() {
		ThreadWithNewPosts thread = new ThreadWithNewPosts("some name", 1);
		
		assertThat(thread.getName()).isEqualTo("some name");
		assertThat(thread.getBoard()).isEqualTo("");
	}
}
