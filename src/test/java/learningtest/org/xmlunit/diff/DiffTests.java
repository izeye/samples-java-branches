package learningtest.org.xmlunit.diff;

import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.ElementSelectors;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DiffTests {

	@Test
	public void getDifferencesWhenIdenticalShouldHaveNoDifference() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		String xml2 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		Diff diff = DiffBuilder.compare(Input.fromString(xml1)).withTest(Input.fromString(xml2)).withNodeMatcher(new DefaultNodeMatcher(
				ElementSelectors.byNameAndText)).build();
		assertThat(diff.getDifferences().iterator().hasNext()).isFalse();
	}

	@Test
	public void getDifferencesWhenDifferentTextShouldHaveDifferentComparisonResult() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		String xml2 = "<persons><person><id>1</id><name>John</name></person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1)).withTest(Input.fromString(xml2)).withNodeMatcher(new DefaultNodeMatcher(
				ElementSelectors.byNameAndText)).build();

		Set<ComparisonResult> comparisonResults = new HashSet<>();
		for (Difference difference : diff.getDifferences()) {
			comparisonResults.add(difference.getResult());
		}
		assertThat(comparisonResults).containsExactly(ComparisonResult.DIFFERENT);
	}

	@Test
	public void getDifferencesWhenDifferentPropertyOrderShouldHaveAllSimilarComparisonResults() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		String xml2 = "<persons><person><name>Johnny</name><id>1</id></person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1)).withTest(Input.fromString(xml2)).withNodeMatcher(new DefaultNodeMatcher(
				ElementSelectors.byNameAndText)).build();

		Set<ComparisonResult> comparisonResults = new HashSet<>();
		for (Difference difference : diff.getDifferences()) {
			comparisonResults.add(difference.getResult());
		}
		assertThat(comparisonResults).containsExactly(ComparisonResult.SIMILAR);
	}

	@Test
	public void getDifferencesWhenDifferentArrayOrderShouldHaveDifferentComparisonResult() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person><person><id>2</id><name>John</name></person></persons>";
		String xml2 = "<persons><person><id>2</id><name>John</name></person><person><id>1</id><name>Johnny</name></person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1)).withTest(Input.fromString(xml2)).withNodeMatcher(new DefaultNodeMatcher(
				ElementSelectors.byNameAndText)).build();

		Set<ComparisonResult> comparisonResults = new HashSet<>();
		for (Difference difference : diff.getDifferences()) {
			comparisonResults.add(difference.getResult());
		}
		assertThat(comparisonResults).containsExactly(ComparisonResult.DIFFERENT);
	}

}
