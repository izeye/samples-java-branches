package learningtest.java.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.junit.Test;

/**
 * Tests for {@link MethodHandle}.
 *
 * @author Johnny Lim
 */
public class MethodHandleTests {

	@Test
	public void invokeExact() throws Throwable {
		Lookup lookup = MethodHandles.lookup();
		MethodHandle methodHandle = lookup.findStatic(getClass(), "hello", MethodType.methodType(void.class));
		methodHandle.invokeExact();
	}

	static void hello() {
		System.out.println("hello");
	}

}
