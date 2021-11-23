package learningtest.java.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link Iterator}.
 *
 * @author Jungmuk Lim
 */
class IteratorTests {

	@Test
	void testForEachRemaining() {
		Iterator<String> iterator = Arrays.asList("Johnny", "John").iterator();

		List<String> names = new ArrayList<>();
		iterator.forEachRemaining(names::add);

		assertThat(names).containsExactly("Johnny", "John");
	}

	@Test
	void nextWhenRemovingWithIteratorShouldWork() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		Iterator<Integer> iterator = list.iterator();
		assertThat(iterator.next()).isEqualTo(1);

		iterator.remove();
		assertThat(list).hasSize(3);
		assertThat(iterator.next()).isEqualTo(2);
	}

	@Test
	void nextWhenRemovingWithListWillThrowConcurrentModificationException() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		Iterator<Integer> iterator = list.iterator();
		Integer element = iterator.next();
		assertThat(element).isEqualTo(1);

		list.remove(element);
		assertThat(list).hasSize(3);
		assertThatExceptionOfType(ConcurrentModificationException.class)
				.isThrownBy(() -> iterator.next());
	}

	@Test
	void removeWithoutNextThrowsIllegalStateException() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");

		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		assertThatThrownBy(iterator::remove).isInstanceOf(IllegalStateException.class);
		iterator.next();
		iterator.remove();
		assertThatThrownBy(iterator::remove).isInstanceOf(IllegalStateException.class);
	}

}
