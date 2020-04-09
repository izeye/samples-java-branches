package com.izeye.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Tests for {@link ManagementFactory#getThreadMXBean()}.
 *
 * @author Johnny Lim
 */
public class ManagementFactoryGetThreadMXBeanTests {

	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		for (long threadId : threadMXBean.getAllThreadIds()) {
			System.out.println(threadId);
		}
	}

}
