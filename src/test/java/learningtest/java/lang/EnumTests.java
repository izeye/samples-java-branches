package learningtest.java.lang;

import java.lang.reflect.Constructor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 15. 8. 23..
 */
public class EnumTests {

	@Test
	public void testOrdinal() {
		assertThat(Fruit.APPLE.ordinal()).isEqualTo(0);
		assertThat(Fruit.BANANA.ordinal()).isEqualTo(1);
	}
	
	@Test
	public void testConstructor() {
		// An enum has no constructor.
		Constructor<?>[] constructors = Animal.class.getConstructors();
		assertThat(constructors).isEmpty();

		// A class has a default constructor.
		constructors = Flower.class.getConstructors();
		assertThat(constructors).isNotEmpty();
	}
	
	enum Fruit {
		APPLE, BANANA
	}
	
}
