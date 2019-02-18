package learningtest.java.util.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Stream}.
 *
 * @author Johnny Lim
 */
public class StreamTests {

	@Test
	public void testOf() {
		Stream.of("Apple", "Banana").forEach(System.out::println);
	}

	@Test
	public void testIterateAndLimit() {
		assertThat(Stream.iterate(1, i -> i + 1).limit(5)).containsExactly(1, 2, 3, 4, 5);
	}
	
	@Test
	public void test() {
		int n = 10;
		List<int[]> pairs = Stream.iterate(1, i -> i + 1).limit(n)
				.flatMap(i -> Stream.iterate(1, j -> j + 1).limit(i).map(j -> new int[]{i, j}))
				.filter(pair -> isPrime(pair[0] + pair[1]))
				.collect(toList());
		pairs.stream().forEach(pair -> System.out.println(Arrays.toString(pair)));
		assertThat(pairs).doesNotContain(new int[] {1, 1});
		assertThat(pairs).doesNotContain(new int[] {1, 2});
		assertThat(pairs).contains(new int[] {2, 1});
	}

	private boolean isPrime(int n) {
		return Stream.iterate(2, i -> i + 1).limit((long) Math.sqrt(n)).noneMatch(i -> n % i == 0);
	}

	@Test
	public void testGenerate() {
		randomStream(10)
				.limit(10)
				.forEach(System.out::println);
	}

	private Stream<Long> randomStream(int range) {
		return Stream.generate(Math::random)
				.map(n -> n * range)
				.map(Math::round);
	}

	@Test
	public void testFlatMap() {
		Developer polyglot = new Developer("esoteric");
		polyglot.add("clojure");
		polyglot.add("scala");
		polyglot.add("groovy");
		polyglot.add("go");

		Developer busy = new Developer("pragmatic");
		busy.add("java");
		busy.add("javascript");

		List<Developer> team = new ArrayList<>();
		team.add(polyglot);
		team.add(busy);

		List<String> teamLanguages = team.stream()
				.map(d -> d.getLanguages())
				.flatMap(l -> l.stream())
				.collect(Collectors.toList());
		assertThat(teamLanguages).containsAll(polyglot.getLanguages());
		assertThat(teamLanguages).containsAll(busy.getLanguages());
	}

	@Test
	public void toCollection() {
		Set<Integer> sorted = Arrays.asList(3, 2, 1).stream().collect(Collectors.toCollection(TreeSet::new));
		assertThat(sorted).containsExactly(1, 2, 3);
	}

	@Test
	public void joiningWhenStreamIsEmpty() {
		String joined = Stream.<String>empty()
				.collect(Collectors.joining(",", "{\"measurements\":[", "]}"));
		assertThat(joined).isEqualTo("{\"measurements\":[]}");
	}

	private static class Developer {

		private final String name;
		private final Set<String> languages = new HashSet<>();

		public Developer(String name) {
			this.name = name;
		}

		public void add(String language) {
			this.languages.add(language);
		}

		public Set<String> getLanguages() {
			return this.languages;
		}

	}
	
}
