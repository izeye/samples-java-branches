package learningtest.org.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TestName}.
 *
 * @author Johnny Lim
 */
public class TestNameTests {

	@Rule
	public TestName name = new TestName();

	@Test
	public void testA() {
		assertThat(this.name.getMethodName()).isEqualTo("testA");
	}

	@Test
	public void testB() {
		assertThat(this.name.getMethodName()).isEqualTo("testB");
	}

}
