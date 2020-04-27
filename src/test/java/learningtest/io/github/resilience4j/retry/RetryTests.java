package learningtest.io.github.resilience4j.retry;

import io.github.resilience4j.retry.Retry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;
import org.mockito.BDDMockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class RetryTests {

	@Test
	public void test() {
		HelloWorldService helloWorldService = mock(HelloWorldService.class);
		given(helloWorldService.sayHelloWorld()).willThrow(new RuntimeException("BAM!"));

		Retry retry = Retry.ofDefaults("id");
		CheckedFunction0<String> retryableSupplier = Retry
				.decorateCheckedSupplier(retry, helloWorldService::sayHelloWorld);

		Try<String> result = Try.of(retryableSupplier)
				.recover((throwable) -> "Hello world from recovery function");

		BDDMockito.then(helloWorldService).should(times(3)).sayHelloWorld();
		assertThat(result.get()).isEqualTo("Hello world from recovery function");
	}

	private interface HelloWorldService {

		String sayHelloWorld();

	}

}
