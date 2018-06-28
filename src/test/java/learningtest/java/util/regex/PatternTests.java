package learningtest.java.util.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


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
		assertThat(result).isEqualTo("I love you and love yours.");
		
		matcher.reset();
		sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1));
		}
		matcher.appendTail(sb);
		result = sb.toString();
		assertThat(result).isEqualTo("I dislike you and hate yours.");
	}

	@Test
	public void testWithBackslashOrDollar() {
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
		assertThat(result).isEqualTo("I l$o\\ve you and l$o\\ve yours.");

		matcher.reset();
		sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, escapeRegexGroup(matcher.group(1)));
		}
		matcher.appendTail(sb);
		result = sb.toString();
		assertThat(result).isEqualTo("I dis\\like you and ha$te yours.");
	}
	
	String escapeRegexGroup(String string) {
		return string.replace("\\", "\\\\").replace("$", "\\$");
	}
	
	@Test
	public void testNamedCapturingGroups() {
		Pattern pattern = Pattern.compile("(?<firstName>.+?) (?<middleName>.+?) (?<lastName>.+)");
		Matcher matcher = pattern.matcher("Johnny I. Lim");
		matcher.find();
		assertThat(matcher.group("firstName")).isEqualTo("Johnny");
		assertThat(matcher.group("middleName")).isEqualTo("I.");
		assertThat(matcher.group("lastName")).isEqualTo("Lim");
	}

	@Test
	public void testCurlyBraces() {
		// NOTE: Escaping a closing curly brace is optional.
		Pattern pattern = Pattern.compile("^\\{.+}.*$");
		assertThat(pattern.matcher("{noop}password").matches()).isTrue();
	}

	@Test
	public void replaceFirst() {
		Pattern pattern = Pattern.compile("^https?://[^/]+/");

		assertThat(pattern.matcher("https://www.google.com/hello").replaceFirst("")).isEqualTo("hello");
		assertThat(pattern.matcher("/hello").replaceFirst("")).isEqualTo("/hello");
	}

	@Test
	public void replaceAllWithCapturingGroup() {
		Pattern pattern = Pattern.compile("([, =\"])");

		assertThat(pattern.matcher("a,b").replaceAll("\\\\$1")).isEqualTo("a\\,b");
		assertThat(pattern.matcher("a=b").replaceAll("\\\\$1")).isEqualTo("a\\=b");
		assertThat(pattern.matcher("a b").replaceAll("\\\\$1")).isEqualTo("a\\ b");
		assertThat(pattern.matcher("\"ab\"").replaceAll("\\\\$1")).isEqualTo("\\\"ab\\\"");
		assertThat(pattern.matcher("\"a,b\"").replaceAll("\\\\$1")).isEqualTo("\\\"a\\,b\\\"");
	}
	
}
