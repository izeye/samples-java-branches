package learningtest.java.net;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import java.net.URLClassLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link URLClassLoader}.
 *
 * @author Johnny Lim
 */
class URLClassLoaderTests {

	@Test
	@EnabledOnJre(JRE.JAVA_8)
	void test() throws ClassNotFoundException {
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

		assertThat(Class.forName("learningtest.java.net.URLClassLoaderTests$ReturnsFoo", true, someFooHidingClassLoader)).isNotNull();
		assertThatThrownBy(() -> Class.forName("learningtest.java.net.URLClassLoaderTests$ReturnsAbstractFoo", true, someFooHidingClassLoader))
				.isExactlyInstanceOf(NoClassDefFoundError.class);
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
