package learningtest.java.util.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 12. 30..
 */
public class PatternTests {
	
	@Test
	public void test() {
		String target = "I {keyword:dislike} you and {keyword:hate} yours.";
		String keyword = "love";
		
		Pattern pattern = Pattern.compile("\\{keyword:([^}]+)\\}");
		
		Matcher matcher = pattern.matcher(target);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, keyword);
		}
		matcher.appendTail(sb);
		String result = sb.toString();
		assertThat(result, is("I love you and love yours."));
		
		matcher.reset();
		sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1));
		}
		matcher.appendTail(sb);
		result = sb.toString();
		assertThat(result, is("I dislike you and hate yours."));
	}

	@Test
	public void testWithSlashOrDollar() {
		String target = "I {keyword:dis\\like} you and {keyword:ha$te} yours.";
		String keyword = "l$o\\ve";

		Pattern pattern = Pattern.compile("\\{keyword:([^}]+)\\}");

		Matcher matcher = pattern.matcher(target);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, escapeRegexGroup(keyword));
		}
		matcher.appendTail(sb);
		String result = sb.toString();
		assertThat(result, is("I l$o\\ve you and l$o\\ve yours."));

		matcher.reset();
		sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, escapeRegexGroup(matcher.group(1)));
		}
		matcher.appendTail(sb);
		result = sb.toString();
		assertThat(result, is("I dis\\like you and ha$te yours."));
	}
	
	String escapeRegexGroup(String string) {
		return string.replace("\\", "\\\\").replace("$", "\\$");
	}
	
}
