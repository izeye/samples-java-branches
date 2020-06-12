package learningtest.java.lang;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	void bootstrapClassLoaderIsNull() {
		assertThat(ArrayList.class.getClassLoader()).isNull();
	}
	
}
