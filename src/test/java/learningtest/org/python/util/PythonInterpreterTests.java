package learningtest.org.python.util;

import org.junit.Test;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Tests for {@link PythonInterpreter}.
 *
 * @author Johnny Lim
 */
public class PythonInterpreterTests {

	private final PythonInterpreter interpreter = new PythonInterpreter();

	@Test
	public void test() {
		this.interpreter.exec("import sys");
		this.interpreter.exec("print sys");

		this.interpreter.set("a", new PyInteger(42));
		this.interpreter.exec("print a");

		this.interpreter.exec("x = 2 + 2");
		PyObject x = this.interpreter.get("x");
		System.out.println("x: " + x);
	}

}
