package learningtest.casting;

import java.util.List;

import org.junit.Test;

/**
 * Tests for type casting with generics.
 *
 * @author Johnny Lim
 */
public class CastingGenericsTests {

	@Test
	public void testGenerics() {
		@SuppressWarnings("unchecked")
		PropertyState<List<String>> propertyState =
				(PropertyState<List<String>>) (Object) getPropertyState(List.class);
		System.out.println(propertyState);
	}

	private <T> PropertyState<T> getPropertyState(Class<T> clazz) {
		return new PropertyState<>();
	}

	private static class PropertyState<T> {
	}

}
