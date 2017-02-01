package learningtest.org.junit.rules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Verifier}.
 *
 * @author Johnny Lim
 */
public class VerifierTests {

	private final List<Throwable> exceptions = new ArrayList<>();

	@Rule
	public Verifier verifier = new Verifier() {

		@Override
		protected void verify() throws Throwable {
			System.out.println(exceptions);

			assertThat(exceptions).hasSize(1);
		}
	};

	@Test
	public void test() {
		System.out.println("Hello, world!");
		this.exceptions.add(new RuntimeException());
	}

}
