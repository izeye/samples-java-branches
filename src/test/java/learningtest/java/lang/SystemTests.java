package learningtest.java.lang;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 9. 17..
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
	
}
