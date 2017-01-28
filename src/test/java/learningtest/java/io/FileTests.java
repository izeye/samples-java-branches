package learningtest.java.io;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link File}.
 *
 * @author Johnny Lim
 */
public class FileTests {
	
	@Test
	public void testIsFile() {
		assertThat(new File("non_existent").isFile()).isFalse();
	}

	@Test
	public void testGetAbsolutePath() {
		File file = new File(".");
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
	}
	
}
