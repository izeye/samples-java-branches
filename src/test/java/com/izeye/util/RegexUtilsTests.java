package com.izeye.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 7. 12..
 */
public class RegexUtilsTests {
	
	@Test
	public void testGetGroupNames() {
		String regex = "(?<firstName>.+?) (?<middleName>.+?) (?<lastName>.+)";
		List<String> groupNames = RegexUtils.getGroupNames(regex);
		assertThat(groupNames).isEqualTo(Arrays.asList("firstName", "middleName", "lastName"));
	}
	
}
