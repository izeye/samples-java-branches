package learningtest.exception;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/**
 * Tests for exception hierarchy.
 *
 * @author Johnny Lim
 */
class ExceptionHierarchyTests {

	@Test
	void testIOException() {
		try {
			throwIOException();
		} catch (JsonProcessingException ex) {
			fail();
		} catch (IOException expected) {
		}
	}

	@Test
	void testJsonProcessingException() {
		try {
			throwJsonProcessingException();
		} catch (JsonProcessingException expected) {
		} catch (IOException ex) {
			fail();
		}
	}

	void throwJsonProcessingException() throws IOException {
		throw mock(JsonProcessingException.class);
	}

	void throwIOException() throws IOException {
		throw new IOException();
	}

}
