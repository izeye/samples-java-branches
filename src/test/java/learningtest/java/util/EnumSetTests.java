package learningtest.java.util;

import java.util.EnumSet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link EnumSet}.
 *
 * @author Johnny Lim
 */
public class EnumSetTests {

	@Test
	public void testAllOf() {
		assertThat(EnumSet.allOf(Fruit.class)).containsExactly(Fruit.APPLE, Fruit.BANANA);
	}

	private static enum Fruit {
		APPLE, BANANA
	}

}
