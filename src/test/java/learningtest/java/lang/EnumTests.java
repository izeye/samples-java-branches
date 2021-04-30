package learningtest.java.lang;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code enum}.
 *
 * @author Johnny Lim
 */
class EnumTests {

	@Test
	void testOrdinal() {
		assertThat(Fruit.APPLE.ordinal()).isEqualTo(0);
		assertThat(Fruit.BANANA.ordinal()).isEqualTo(1);
	}
	
	@Test
	void testConstructor() {
		// An enum has no constructor.
		Constructor<?>[] constructors = Animal.class.getConstructors();
		assertThat(constructors).isEmpty();

		// A class has a default constructor.
		constructors = Flower.class.getConstructors();
		assertThat(constructors).isNotEmpty();
	}

	@Test
	void testHashCode() {
		System.out.println(Fruit.APPLE.hashCode());
		System.out.println(Fruit.BANANA.hashCode());
	}

	@Test
	void valuesCreatesNewArray() {
		assertThat(Fruit.values()).isNotSameAs(Fruit.values());
	}
	
	enum Fruit {
		APPLE, BANANA
	}
	
}
