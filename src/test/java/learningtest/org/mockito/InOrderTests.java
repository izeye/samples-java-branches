package learningtest.org.mockito;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link InOrder}.
 *
 * @author Johnny Lim
 */
public class InOrderTests {

	@Test
	public void test12() {
		Something something1 = mock(Something.class);
		Something something2 = mock(Something.class);
		something1.doSomething();
		something2.doSomething();

		InOrder inOrder = inOrder(something1, something2);
		inOrder.verify(something1).doSomething();
		inOrder.verify(something2).doSomething();
	}

	@Test
	public void test21() {
		Something something1 = mock(Something.class);
		Something something2 = mock(Something.class);
		something2.doSomething();
		something1.doSomething();

		InOrder inOrder = inOrder(something1, something2);
		inOrder.verify(something2).doSomething();
		inOrder.verify(something1).doSomething();
	}

	static class Something {

		public void doSomething() {
		}

	}

}
