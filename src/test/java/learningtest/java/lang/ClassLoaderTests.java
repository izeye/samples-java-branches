package learningtest.java.lang;

import org.junit.Test;

/**
 * Created by izeye on 16. 3. 4..
 */
public class ClassLoaderTests {
	
	@Test
	public void test() {
		ClassLoader classLoader = getClass().getClassLoader();
		while (classLoader != null) {
			System.out.println(classLoader);
			classLoader = classLoader.getParent();
		}
	}
	
}
