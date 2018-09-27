package learningtest.java.lang;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Class}.
 *
 * @author Johnny Lim
 */
public class ClassTests {

	@Test
	public void testGetAnnotation() {
		SomeAnnotation annotation = SomeAnnotationTests.class.getAnnotation(SomeAnnotation.class);
		assertThat(annotation.name()).isEqualTo("myAnnotation");
	}

	@Test
	public void getName() {
		assertThat(ClassTests.class.getName()).isEqualTo("learningtest.java.lang.ClassTests");
	}

	@Test
	public void testGetClass() {
		assertThat(getClass()).isSameAs(ClassTests.class);
	}

	@Test
	public void anonymousClassGetSimpleNameReturnsEmptyString() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
			}
		};

		assertThat(runnable.getClass().getSimpleName()).isEmpty();
		assertThat(runnable.getClass().getName()).isEqualTo("learningtest.java.lang.ClassTests$1");
	}

	@Test
	public void lambda() {
		Runnable runnable = () -> {
		};

		assertThat(runnable.getClass().getName()).startsWith("learningtest.java.lang.ClassTests$$Lambda$");
		assertThat(runnable.getClass().getSimpleName()).startsWith("ClassTests$$Lambda$");
	}

	@Retention(RetentionPolicy.RUNTIME)
	private @interface SomeAnnotation {

		String name();

	}

	@SomeAnnotation(name = "myAnnotation")
	private static class SomeAnnotationTests {
	}

}
