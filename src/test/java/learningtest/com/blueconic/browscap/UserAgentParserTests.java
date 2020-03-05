package learningtest.com.blueconic.browscap;

import java.io.IOException;
import java.util.Arrays;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import com.izeye.util.TimingUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link UserAgentParser}.
 *
 * @author Johnny Lim
 */
class UserAgentParserTests {

	private static final String USER_AGENT_MAC_OS = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
	private static final String USER_AGENT_WINDOWS = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";
	private static final String USER_AGENT_I_PHONE = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Mobile/15E148 Safari/604.1";
	private static final String USER_AGENT_ANDROID = "Mozilla/5.0 (Linux; Android 9; SAMSUNG SM-N950N) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/11.1 Chrome/75.0.3770.143 Mobile Safari/537.36";

	private final UserAgentParser userAgentParser = TimingUtils.printTiming(() -> createUserAgentParser());

	@Test
	void test() {
		System.out.println(parse(USER_AGENT_MAC_OS));
		System.out.println(parse(USER_AGENT_WINDOWS));
		System.out.println(parse(USER_AGENT_I_PHONE));
		System.out.println(parse(USER_AGENT_ANDROID));
	}

	private Capabilities parse(String userAgent) {
		return TimingUtils.printTiming(() -> this.userAgentParser.parse(userAgent));
	}

	private static UserAgentParser createUserAgentParser() {
		try {
			return new UserAgentService().loadParser(Arrays.asList(BrowsCapField.values()));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}

}
