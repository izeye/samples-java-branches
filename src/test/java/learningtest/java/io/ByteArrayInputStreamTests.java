package learningtest.java.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by izeye on 16. 3. 31..
 */
public class ByteArrayInputStreamTests {
	
	@Test
	public void test() {
		String message = "Hello, world!";
		int repeat = 10000;

		StringBuffer sbText = new StringBuffer();
		for (int i = 0; i < repeat; i++) {
			sbText.append(i);
			sbText.append(": ");
			sbText.append(message);
			sbText.append("\n");
		}
		String text = sbText.toString();
		
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(text.getBytes());
			
			byte[] buffer = new byte[1024];
			int read;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((read = bais.read(buffer)) != -1) {
				System.out.println("read: " + read);
				baos.write(buffer, 0, read);
			}
			baos.close();
			
			String result = baos.toString();
			System.out.println(result);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
