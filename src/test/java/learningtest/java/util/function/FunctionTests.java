package learningtest.java.util.function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Function}.
 *
 * @author Johnny Lim
 */
public class FunctionTests {

	@Test
	public void testLambda() {
		List<String> digits = Arrays.asList("1", "2", "3", "4", "5");
		List<Integer> numbers = map(s -> Integer.valueOf(s), digits);
		assertThat(numbers).containsExactly(1, 2, 3, 4, 5);
	}

	@Test
	public void testMethodReference() {
		List<String> digits = Arrays.asList("1", "2", "3", "4", "5");
		List<Integer> numbers = map(Integer::valueOf, digits);
		assertThat(numbers).containsExactly(1, 2, 3, 4, 5);
	}

	private static <T, R> List<R> map(Function<T, R> function, List<T> source) {
		List<R> results = new ArrayList<>();
		for (T item : source) {
			R result = function.apply(item);
			results.add(result);
		}
		return results;
	}

}
