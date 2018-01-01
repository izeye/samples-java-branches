package learningtest.java.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link ObjectOutputStream}.
 *
 * @author Johnny Lim
 */
public class ObjectOutputStreamTests {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWriteObjectTreeSet() throws IOException {
		Set<Person> persons = new TreeSet<>(Comparator.comparing(Person::getFirstName));
		persons.addAll(Arrays.asList(new Person("Johnny")));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);

		try {
			this.thrown.expect(NotSerializableException.class);
			this.thrown.expectMessage("learningtest.java.io.ObjectOutputStreamTests$$Lambda");
			oos.writeObject(persons);
		}
		finally {
			oos.close();
		}
	}

	@Test
	public void testWriteObjectLinkedHashSet() throws IOException {
		Set<Person> persons = new TreeSet<>(Comparator.comparing(Person::getFirstName));
		persons.addAll(Arrays.asList(new Person("Johnny")));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(new LinkedHashSet<>(persons));
		oos.close();
	}

	@Data
	@AllArgsConstructor
	static class Person implements Serializable {

		private final String firstName;

	}

}
