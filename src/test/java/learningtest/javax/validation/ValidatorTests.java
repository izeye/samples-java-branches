package learningtest.javax.validation;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 7. 21..
 */
public class ValidatorTests {
	
	Validator validator;
	
	@Before
	public void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		this.validator = validatorFactory.getValidator();
	}

	// FIXME: This fails with Logback 1.3.0-alpha5, but how?
	@Test
	public void test() {
		String path = null;
		Something something = new Something(path);
		Set<ConstraintViolation<Something>> constraintViolations = this.validator.validate(something);
		assertThat(constraintViolations.size()).isEqualTo(1);
		assertThat(constraintViolations.iterator().next().getMessage())
				.isEqualTo("may not be null");

		path = "/";
		something = new Something(path);
		constraintViolations = this.validator.validate(something);
		assertThat(constraintViolations.size()).isEqualTo(0);

		path = "/a";
		something = new Something(path);
		constraintViolations = this.validator.validate(something);
		assertThat(constraintViolations.size()).isEqualTo(0);
		
		path = "";
		something = new Something(path);
		constraintViolations = this.validator.validate(something);
		assertThat(constraintViolations.size()).isEqualTo(0);

		path = "a";
		something = new Something(path);
		constraintViolations = this.validator.validate(something);
		assertThat(constraintViolations.size()).isEqualTo(1);
		assertThat(constraintViolations.iterator().next().getMessage())
				.isEqualTo("Path must start with / or empty");
	}
	
	static class Something {
		
		@NotNull
		@Pattern(regexp = "/.*|^$", message = "Path must start with / or empty")
		private String path;
		
		public Something(String path) {
			this.path = path;
		}
		
	}
	
}
