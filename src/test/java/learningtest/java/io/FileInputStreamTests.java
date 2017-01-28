package learningtest.java.io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for {@link FileInputStream}.
 *
 * @author Johnny Lim
 */
public class FileInputStreamTests {

	@Test
	public void test() throws IOException, InterruptedException {
		FileInputStream fis = new FileInputStream("./build.gradle");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int readBytes;
		while ((readBytes = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, readBytes);
		}

		String content = baos.toString();
		System.out.println(content);

		baos.close();
		fis.close();
	}

}
