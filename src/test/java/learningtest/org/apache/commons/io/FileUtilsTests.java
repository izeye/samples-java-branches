package learningtest.org.apache.commons.io;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * Tests for {@link FileUtils}.
 *
 * @author Johnny Lim
 */
public class FileUtilsTests {

	@Test
	public void testReadLines() throws IOException {
		String pathname = ".gitignore";

		List<String> lines = FileUtils.readLines(new File(pathname));
		lines.forEach(System.out::println);

		Collections.sort(lines);
		lines.forEach(System.out::println);
	}

}
