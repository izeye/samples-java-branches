package learningtest.java.lang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Object#getClass()}.
 *
 * @author Johnny Lim
 */
class ObjectGetClassTests {

	@Test
	void test() {
		ClassB classB = new ClassB();
		assertThat(classB.clazz).isEqualTo(ClassB.class);
	}

	static abstract class ClassA {
		Class<?> clazz = getClass();
	}

	static class ClassB extends ClassA {
	}

}
