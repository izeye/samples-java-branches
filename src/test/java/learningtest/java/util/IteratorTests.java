package learningtest.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Tests for {@link Iterator}.
 *
 * @author Jungmuk Lim
 */
public class IteratorTests {

	@Test
	public void testForEachRemaining() {
		Iterator<String> iterator = Arrays.asList("Johnny", "John").iterator();

		List<String> names = new ArrayList<>();
		iterator.forEachRemaining(names::add);

		assertThat(names).containsExactly("Johnny", "John");
	}

	@Test
	public void nextWhenRemovingWithIteratorShouldWork() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		Iterator<Integer> iterator = list.iterator();
		assertThat(iterator.next()).isEqualTo(1);

		iterator.remove();
		assertThat(list).hasSize(3);
		assertThat(iterator.next()).isEqualTo(2);
	}

	@Test
	public void nextWhenRemovingWithListWillThrowConcurrentModificationException() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		Iterator<Integer> iterator = list.iterator();
		Integer element = iterator.next();
		assertThat(element).isEqualTo(1);

		list.remove(element);
		assertThat(list).hasSize(3);
		assertThatExceptionOfType(ConcurrentModificationException.class)
				.isThrownBy(() -> iterator.next());
	}

}
