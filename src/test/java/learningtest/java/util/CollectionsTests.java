package learningtest.java.util;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTests {

	@Ignore
	@Test
	public void makeUnmodifiableListIteratorHasNextTriggerStackOverflowError() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		List<Integer> wrapped = numbers;
		for (int i = 0; i < 29634; i++) {
			wrapped = Collections.unmodifiableList(wrapped);

			// NOTE: Without the following call, the following error occurs first:
			//
			// java.lang.StackOverflowError
			// at java.util.ArrayList.iterator(ArrayList.java:834)
			// at java.util.Collections$UnmodifiableCollection$1.<init>(Collections.java:1039)
			// at java.util.Collections$UnmodifiableCollection.iterator(Collections.java:1038)
			// at java.util.Collections$UnmodifiableCollection$1.<init>(Collections.java:1039)
			wrapped.iterator();
		}

		// Now we can see the following error:
		//
		// java.lang.StackOverflowError
		// at java.util.Collections$UnmodifiableCollection$1.hasNext(Collections.java:1041)
		// at java.util.Collections$UnmodifiableCollection$1.hasNext(Collections.java:1041)
		wrapped.iterator().hasNext();
	}

}
