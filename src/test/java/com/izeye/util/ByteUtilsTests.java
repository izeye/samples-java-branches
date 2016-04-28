package com.izeye.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 4. 28..
 */
public class ByteUtilsTests {
	
	@Test
	public void testRandomBytes() {
		int length = 256;
		
		byte[] bytes = ByteUtils.randomBytes(length);
		assertThat(bytes.length).isEqualTo(length);
		System.out.println(ByteUtils.bytes2HexString(bytes));
	}
	
}
