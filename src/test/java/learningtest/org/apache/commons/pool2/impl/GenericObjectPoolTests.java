package learningtest.org.apache.commons.pool2.impl;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

	private static class SomeObject {
	}

}
