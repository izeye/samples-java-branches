package learningtest.java.net;

import java.net.URLClassLoader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link URLClassLoader}.
 *
 * @author Johnny Lim
 */
public class URLClassLoaderTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test() throws ClassNotFoundException {
		URLClassLoader someFooHidingClassLoader = new URLClassLoader(
				((URLClassLoader) URLClassLoaderTests.class.getClassLoader()).getURLs(), null) {
			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				if (name.equals("learningtest.java.net.URLClassLoaderTests$SomeFoo")) {
					throw new ClassNotFoundException(name);
				}
				return super.findClass(name);
			}
		};

		assertThat(Class.forName(
				"learningtest.java.net.URLClassLoaderTests$ReturnsFoo", true, someFooHidingClassLoader)).isNotNull();

		this.thrown.expect(NoClassDefFoundError.class);
		Class.forName(
				"learningtest.java.net.URLClassLoaderTests$ReturnsAbstractFoo", true, someFooHidingClassLoader);
	}

	private static class ReturnsFoo {

		public Foo getFoo() {
			if (ClassUtils.isPresent("learningtest.java.net.URLClassLoaderTests$SomeFoo")) {
				return new SomeFoo();
			}
			return null;
		}

	}

	private static class ReturnsAbstractFoo {

		public AbstractFoo getFoo() {
			if (ClassUtils.isPresent("learningtest.java.net.URLClassLoaderTests$SomeFoo")) {
				return new SomeFoo();
			}
			return null;
		}

	}

	private interface Foo {
	}

	private static abstract class AbstractFoo implements Foo {
	}

	private static class SomeFoo extends AbstractFoo {
	}

	private static class ClassUtils {

		public static boolean isPresent(String name) {
			try {
				Class.forName(name);
				return true;
			} catch (ClassNotFoundException e) {
				return false;
			}
		}
	}

}
