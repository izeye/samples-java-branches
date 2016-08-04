package com.izeye.util;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for StringUtils.
 *
 * @author Johnny Lim
 */
public class StringUtilsTests {

	@Test
	public void testConvertWithLong() {
		List<String> strings = Arrays.asList("1", "2", "3");
		List<Long> longs = StringUtils.convert(strings, Long::parseLong);
		assertThat(longs).containsExactly(1L, 2L, 3L);
	}

}
