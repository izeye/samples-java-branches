package learningtest.java.lang;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests for {@link System}.
 *
 * @author Johnny Lim
 */
public class SystemTests {
	
	@Test
	public void testIdentityHashCode() {
		Object object = new Object();
		
		String classCanonicalName = object.getClass().getCanonicalName();
		int identityHashCode = System.identityHashCode(object);
		
		assertThat(classCanonicalName + '@' + Long.toHexString(identityHashCode),
				is(object.toString()));
	}

	@Test
	public void testCurrentTimeMillis() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(System.currentTimeMillis());
			try {
				Thread.sleep(1);
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	@Test
	public void nanoTime() {
		for (int i = 0; i < 100; i++) {
			System.out.println(System.nanoTime());
		}
	}
	
}
