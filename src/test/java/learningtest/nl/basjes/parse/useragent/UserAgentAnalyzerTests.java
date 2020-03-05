package learningtest.nl.basjes.parse.useragent;

import com.izeye.util.TimingUtils;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.junit.jupiter.api.Test;

class UserAgentAnalyzerTests {

	private static final String USER_AGENT_MAC_OS = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
	private static final String USER_AGENT_WINDOWS = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
	private static final String USER_AGENT_I_PHONE = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Mobile/15E148 Safari/604.1";
	private static final String USER_AGENT_ANDROID = "Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-N950N) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/11.1 Chrome/75.0.3770.143 Mobile Safari/537.36";

	private final UserAgentAnalyzer userAgentAnalyzer = TimingUtils.printTiming(() -> createUserAgentAnalyzer());

	@Test
	void test() {
		System.out.println(parse(USER_AGENT_MAC_OS));
		System.out.println(parse(USER_AGENT_WINDOWS));
		System.out.println(parse(USER_AGENT_I_PHONE));
		System.out.println(parse(USER_AGENT_ANDROID));
	}

	private UserAgent parse(String userAgent) {
		return TimingUtils.printTiming(() -> this.userAgentAnalyzer.parse(userAgent));
	}

	private static UserAgentAnalyzer createUserAgentAnalyzer() {
		return UserAgentAnalyzer.newBuilder()
				.hideMatcherLoadStats()
				.withCache(10000)
				.build();
	}

}
