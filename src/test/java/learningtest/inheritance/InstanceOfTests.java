package learningtest.inheritance;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code instanceof}.
 *
 * @author Johnny Lim
 */
public class InstanceOfTests {

	@Test
	public void test() {
		A a = new B();
		assertThat(a instanceof A).isTrue();
		assertThat(a instanceof B).isTrue();
		assertThat(a).isInstanceOf(A.class);
		assertThat(a).isInstanceOf(B.class);
	}

	static class A {
	}

	static class B extends A {
	}

}
