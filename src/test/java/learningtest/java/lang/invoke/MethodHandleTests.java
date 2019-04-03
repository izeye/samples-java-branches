package learningtest.java.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
	public void invokePrivate() throws Throwable {
		MyClass myClass = new MyClass();

		Lookup lookup = MethodHandles.lookup();
		MethodHandle methodHandle = lookup.findVirtual(MyClass.class, "hello1", MethodType.methodType(void.class));
		methodHandle.invoke(myClass);

		assertThatExceptionOfType(IllegalAccessException.class)
				.isThrownBy(() -> lookup.findVirtual(MyClass.class, "hello2", MethodType.methodType(void.class)));
	}

	@Test
	public void invokeGetterSetter() throws Throwable {
		Point point = new Point();

		Lookup lookup = MethodHandles.lookup();
		MethodHandle methodHandle = lookup.findSetter(Point.class, "x", int.class);
		methodHandle.invoke(point, 15);

		methodHandle = lookup.findSetter(Point.class, "y", int.class);
		methodHandle.invoke(point, 30);

		methodHandle = lookup.findGetter(Point.class, "x", int.class);
		assertThat(methodHandle.invoke(point)).isEqualTo(15);

		methodHandle = lookup.findGetter(Point.class, "y", int.class);
		assertThat(methodHandle.invoke(point)).isEqualTo(30);
	}

	@Test
	public void insertArguments() throws Throwable {
		Lookup lookup = MethodHandles.lookup();
		MethodHandle methodHandle = lookup.findStatic(
				Math.class, "pow", MethodType.methodType(double.class, double.class, double.class));

		methodHandle = MethodHandles.insertArguments(methodHandle, 1, 10);
		assertThat(methodHandle.invoke(2)).isEqualTo(1024d);
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

	static class Point {

		// NOTE: Can't be private as the following exception will be thrown otherwise:
		// java.lang.IllegalAccessException: member is private: learningtest.java.lang.invoke.MethodHandleTests$Point.x/int/putField, from learningtest.java.lang.invoke.MethodHandleTests
		int x;
		int y;

	}

}
