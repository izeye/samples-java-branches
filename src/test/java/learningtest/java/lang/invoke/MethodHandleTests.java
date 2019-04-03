package learningtest.java.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

	@Test
	public void invoke() throws Throwable {
		MyClass myClass = new MyClass();

		Lookup lookup = MethodHandles.lookup();
		MethodHandle methodHandle = lookup.findVirtual(MyClass.class, "hello1", MethodType.methodType(void.class));
		methodHandle.invoke(myClass);

		assertThatExceptionOfType(IllegalAccessException.class)
				.isThrownBy(() -> lookup.findVirtual(MyClass.class, "hello2", MethodType.methodType(void.class)));
	}

	static void hello() {
		System.out.println("hello");
	}

	static class MyClass {

		public void hello1() {
			System.out.println("hello from hello1");
		}

		private void hello2() {
			System.out.println("hello from hello2");
		}

	}

}
