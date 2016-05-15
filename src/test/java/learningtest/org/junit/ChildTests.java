package learningtest.org.junit;

import org.junit.Test;

/**
 * Created by izeye on 16. 5. 13..
 */
public class ChildTests extends ParentTests {
	
	@Test
	public void testChild() {
		System.out.println("I'm a child.");
	}
	
}
