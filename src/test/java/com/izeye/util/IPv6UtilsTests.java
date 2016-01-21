package com.izeye.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 16. 1. 21..
 */
public class IPv6UtilsTests {
	
	@Test
	public void testUncompress() {
		String uncompressed = "2001:0db8:0000:0000:0000:ff00:0042:8329";
		assertThat(IPv6Utils.uncompress(uncompressed), is(uncompressed));
		assertThat(IPv6Utils.uncompress("2001:db8:0:0:0:ff00:42:8329"), is(uncompressed));
		assertThat(IPv6Utils.uncompress("2001:db8::ff00:42:8329"), is(uncompressed));
	}
	
}
