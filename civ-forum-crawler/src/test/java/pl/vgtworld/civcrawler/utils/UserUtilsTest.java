package pl.vgtworld.civcrawler.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class UserUtilsTest {

	@Test
	public void shouldProperlyGenerateSalt() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String salt = UserUtils.generateSalt();
		
		assertThat(salt).hasSize(32);
	}
}
