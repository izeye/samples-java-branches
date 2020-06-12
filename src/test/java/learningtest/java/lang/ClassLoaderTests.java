package learningtest.java.lang;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ClassLoader}.
 *
 * @author Johnny Lim
 */
class ClassLoaderTests {
	
	@Test
	void test() {
		ClassLoader classLoader = getClass().getClassLoader();
		while (classLoader != null) {
			System.out.println(classLoader);
			classLoader = classLoader.getParent();
		}
	}
	
}
