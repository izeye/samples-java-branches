package learningtest.java.lang.management;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 12. 21..
 */
public class ManagementFactoryTests {
	
	@Test
	public void testGetNonHeapMemoryUsage() {
		MemoryUsage nonHeapMemoryUsage
				= ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
		System.out.println(nonHeapMemoryUsage);
		assertThat(nonHeapMemoryUsage.getMax(), is(-1L));
	}
	
}
