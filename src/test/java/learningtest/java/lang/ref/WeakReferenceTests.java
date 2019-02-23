package learningtest.java.lang.ref;

import org.junit.Test;

import java.lang.ref.WeakReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WeakReference}.
 *
 * @author Johnny Lim
 */
public class WeakReferenceTests {

	@Test
	public void stringLiteralWillNotBeGarbageCollected() {
		WeakReference<String> reference = new WeakReference("Hello, world!");
		assertThat(reference.get()).isNotNull();
		System.gc();
		assertThat(reference.get()).isNotNull();
	}

	@Test
	public void stringObjectWillBeGarbageCollected() {
		WeakReference<String> reference = new WeakReference(new String("Hello, world!"));
		assertThat(reference.get()).isNotNull();
		System.gc();
		assertThat(reference.get()).isNull();
	}

}
