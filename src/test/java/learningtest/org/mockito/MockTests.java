package learningtest.org.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by izeye on 15. 8. 30..
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTests {
	
	@Mock
	private Environment environment;
	
	@Test
	public void test() {
		assertThat(environment, is(not(nullValue())));
	}
	
	class Environment {
	}
	
}
