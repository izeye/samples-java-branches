package com.izeye.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by izeye on 16. 7. 12..
 */
public abstract class RegexUtils {
	
	public static List<String> getGroupNames(String regex) {
		List<String> groupNames = new ArrayList<>();
		Pattern pattern = Pattern.compile("\\(\\?\\<([^\\>]+)\\>[^\\)]+\\)");
		Matcher matcher = pattern.matcher(regex);
		while (matcher.find()) {
			groupNames.add(matcher.group(1));
		}
		return groupNames;
	}
	
}
