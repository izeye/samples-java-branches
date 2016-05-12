package learningtest.java.io;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 5. 12..
 */
public class FileTests {
	
	@Test
	public void testIsFile() {
		assertThat(new File("non_existent").isFile()).isFalse();
	}
	
}
