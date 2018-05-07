package learningtest.org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link EmailValidator}.
 *
 * @author Johnny Lim
 */
public class EmailValidatorTests {

	@Test
	public void isValid() {
		assertThat(EmailValidator.getInstance().isValid("izeye@naver.com")).isTrue();
		assertThat(EmailValidator.getInstance().isValid("www.google.com")).isFalse();
	}

}
