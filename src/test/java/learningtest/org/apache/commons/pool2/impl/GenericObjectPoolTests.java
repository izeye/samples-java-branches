package learningtest.org.apache.commons.pool2.impl;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link GenericObjectPool}.
 */
public class GenericObjectPoolTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testReturnObjectWithWrongObject() {
		GenericObjectPool<SomeObject> objectPool = new GenericObjectPool<>(
				new PooledSomeObjectFactory());

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Returned object not currently part of this pool");
		objectPool.returnObject(new SomeObject());
	}

	@Test
	public void testFailedToCreateAndReturnObjectWithNull() {
		GenericObjectPool<SomeObject> objectPool = new GenericObjectPool<>(
				new FailingPooledSomeObjectFactory());

		SomeObject borrowed = null;
		try {
			borrowed = objectPool.borrowObject();
		}
		catch (Exception ex) {
			assertThat(ex).isInstanceOf(RuntimeException.class);
			assertThat(ex.getMessage()).isEqualTo("Failed to create SomeObject.");
		}
		assertThat(borrowed).isNull();

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Returned object not currently part of this pool");
		objectPool.returnObject(null);
	}

	private static class PooledSomeObjectFactory
			extends BasePooledObjectFactory<SomeObject> {

		@Override
		public SomeObject create() throws Exception {
			return new SomeObject();
		}

		@Override
		public PooledObject<SomeObject> wrap(SomeObject obj) {
			return new DefaultPooledObject<>(obj);
		}
	}

	private static class FailingPooledSomeObjectFactory
			extends BasePooledObjectFactory<SomeObject> {

		@Override
		public SomeObject create() throws Exception {
			throw new RuntimeException("Failed to create SomeObject.");
		}

		@Override
		public PooledObject<SomeObject> wrap(SomeObject obj) {
			return new DefaultPooledObject<>(obj);
		}
	}

	private static class SomeObject {
	}

}
