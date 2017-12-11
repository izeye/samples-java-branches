package learningtest.java.util.function.Consumer;

import java.util.function.Consumer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Consumer}.
 *
 * @author Johnny Lim
 */
public class ConsumerTests {

	@Test
	public void testAccept() {
		SomeObject someObject = new SomeObject();

		assertThat(someObject.getSomeField()).isNull();

		invoke(SomeObject::fillSomeField, someObject);
		assertThat(someObject.getSomeField()).isEqualTo("Hello, world!");
	}

	private static void invoke(Consumer<SomeObject> consumer, SomeObject someObject) {
		consumer.accept(someObject);
	}

	private static class SomeObject {

		private String someField;

		public void fillSomeField() {
			this.someField = "Hello, world!";
		}

		public String getSomeField() {
			return this.someField;
		}

	}

}
