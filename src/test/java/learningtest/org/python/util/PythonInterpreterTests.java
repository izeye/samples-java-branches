package learningtest.org.python.util;

import org.junit.Test;
import org.python.core.PyClass;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	public void testScriptFile() {
		String resourceName = "learningtest/jython/hello_world.py";
		this.interpreter.execfile(getClass().getClassLoader().getResourceAsStream(resourceName));
	}

	@Test
	public void testScriptFileWithImport() {
		String resourceName = "learningtest/jython/divider_tests.py";
		this.interpreter.execfile(getClass().getClassLoader().getResourceAsStream(resourceName));
	}

	@Test
	public void testPyClass() {
		this.interpreter.exec("from divider import Divider");
		PyClass dividerDef = (PyClass) this.interpreter.get("Divider");
		PyObject divider = dividerDef.__call__();
		PyInteger result = (PyInteger) divider.invoke("divide", new PyInteger(20), new PyInteger(4));
		assertThat(result.getValue()).isEqualTo(5);
	}

}
