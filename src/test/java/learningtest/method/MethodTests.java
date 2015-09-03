package learningtest.method;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by izeye on 15. 9. 3..
 */
public class MethodTests {
	
	@Test
	public void testOverload() {
		String string = "string";
		
		assertTrue(getParameterClass(string) == String.class);
		assertTrue(getParameterClass((Object) string) == Object.class);
		
		Object[] objects = new Object[] { string };
		assertTrue(getParameterClass(objects[0]) == Object.class);
	}
	
	private Class<?> getParameterClass(String value) {
		return String.class;
	}

	private Class<?> getParameterClass(Object value) {
		return Object.class;
	}
	
}
