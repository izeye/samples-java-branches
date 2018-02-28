package learningtest.org.xmlunit.diff;

import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.DifferenceEvaluators;
import org.xmlunit.diff.ElementSelectors;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.xmlunit.diff.ElementSelectors.byNameAndText;
import static org.xmlunit.diff.ElementSelectors.byXPath;
import static org.xmlunit.diff.ElementSelectors.conditionalBuilder;

public class DiffTests {

	@Test
	public void getDifferencesWhenIdenticalShouldHaveNoDifference() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		String xml2 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		Diff diff = DiffBuilder.compare(Input.fromString(xml1))
				.withTest(Input.fromString(xml2))
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
				.checkForSimilar()
				.build();
		assertThat(diff.hasDifferences()).isFalse();
	}

	@Test
	public void getDifferencesWhenDifferentTextShouldHaveDifferentComparisonResult() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		String xml2 = "<persons><person><id>1</id><name>John</name></person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1))
				.withTest(Input.fromString(xml2))
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
				.checkForSimilar()
				.build();

		Set<ComparisonResult> comparisonResults = new HashSet<>();
		for (Difference difference : diff.getDifferences()) {
			comparisonResults.add(difference.getResult());
		}
		assertThat(comparisonResults).containsExactly(ComparisonResult.DIFFERENT);
	}

	@Test
	public void getDifferencesWhenDifferentPropertyOrderShouldHaveNoDifference() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person></persons>";
		String xml2 = "<persons><person><name>Johnny</name><id>1</id></person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1))
				.withTest(Input.fromString(xml2))
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
				.checkForSimilar()
				.build();
		assertThat(diff.hasDifferences()).isFalse();
	}

	@Test
	public void getDifferencesWhenDifferentlyOrderedCollectionShouldHaveNoDifference() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person><person><id>2</id><name>John</name></person></persons>";
		String xml2 = "<persons><person><id>2</id><name>John</name></person><person><id>1</id><name>Johnny</name></person></persons>";

		// FIXME: Supply an XML schema somehow here.
		Diff diff = DiffBuilder.compare(Input.fromString(xml1))
				.withTest(Input.fromString(xml2))
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
				.checkForSimilar()
				.build();

		// FIXME: Expected with unordered collection.
//		assertThat(diff.hasDifferences()).isFalse();

		Set<ComparisonResult> comparisonResults = new HashSet<>();
		for (Difference difference : diff.getDifferences()) {
			comparisonResults.add(difference.getResult());
		}
		assertThat(comparisonResults).containsExactly(ComparisonResult.DIFFERENT);
	}

	@Test
	public void getDifferencesWhenDifferentlyOrderedCollectionShouldHaveNoDifferenceWithGlobalUnordered() {
		String xml1 = "<persons><person><id>1</id><name>Johnny</name></person><person><id>2</id><name>John</name></person></persons>";
		String xml2 = "<persons><person><id>2</id><name>John</name></person><person><id>1</id><name>Johnny</name></person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1))
				.withTest(Input.fromString(xml2))
				.withNodeMatcher(new DefaultNodeMatcher(conditionalBuilder()
						.whenElementIsNamed("person").thenUse(byXPath("./id", byNameAndText))
						.elseUse(byNameAndText).build()))
				.withDifferenceEvaluator(
						DifferenceEvaluators.chain(
								DifferenceEvaluators.Default,
								DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.CHILD_NODELIST_SEQUENCE)))
				.checkForSimilar()
				.build();
		assertThat(diff.hasDifferences()).isFalse();
	}

	@Test
	public void getDifferencesWhenDifferentlyOrderedCollectionTextOnlyShouldHaveNoDifferenceWithGlobalUnordered() {
		String xml1 = "<persons><person>Johnny</person><person>John</person></persons>";
		String xml2 = "<persons><person>John</person><person>Johnny</person></persons>";

		Diff diff = DiffBuilder.compare(Input.fromString(xml1))
				.withTest(Input.fromString(xml2))
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
				.withDifferenceEvaluator(
						DifferenceEvaluators.chain(
								DifferenceEvaluators.Default,
								DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.CHILD_NODELIST_SEQUENCE)))
				.checkForSimilar()
				.build();
		assertThat(diff.hasDifferences()).isFalse();
	}

}
