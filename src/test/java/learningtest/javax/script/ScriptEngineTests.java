package learningtest.javax.script;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ScriptEngine}.
 *
 * @author Johnny Lim
 */
public class ScriptEngineTests {
	
	@Test
	public void testJavaScript() throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		assertThat(javaScriptEngine).isInstanceOf(NashornScriptEngine.class);
		assertThat(scriptEngineManager.getEngineByName("JavaScript"))
				.isNotSameAs(javaScriptEngine);

		javaScriptEngine.eval("someId = 10");
		javaScriptEngine.eval("anotherId = 20");
		assertTrue((Boolean) javaScriptEngine.eval("someId == 10"));
		assertFalse((Boolean) javaScriptEngine.eval("someId != 10"));
		assertTrue((Boolean) javaScriptEngine.eval("someId == 10 && anotherId == 20"));
	}

	@Test
	public void testNashorn() throws ScriptException, FileNotFoundException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine javaScriptEngine = scriptEngineManager.getEngineByName("nashorn");
		assertThat(javaScriptEngine).isInstanceOf(NashornScriptEngine.class);
		assertThat(scriptEngineManager.getEngineByName("nashorn"))
				.isNotSameAs(javaScriptEngine);

		javaScriptEngine.eval("print('Hello, world!')");
	}

	@Test
	public void testEvalWithReader() throws ScriptException, FileNotFoundException {
		loadScript("samples/js/sample.js");
	}

	@Test
	public void testFunctions() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("samples/js/functions.js");
		InputStreamReader reader = new InputStreamReader(inputStream);
		javaScriptEngine.eval(reader);

		Invocable invocable = (Invocable) javaScriptEngine;
		Object result = invocable.invokeFunction("func1", "Peter Parker");
		assertThat(result).isEqualTo("Greetings from JavaScript");
		assertThat(result).isInstanceOf(String.class);

		invocable.invokeFunction("func2", new Date());
		invocable.invokeFunction("func2", LocalDateTime.now());
		invocable.invokeFunction("func2", new Person());
	}

	@Test
	public void testMethods() throws ScriptException {
		loadScript("samples/js/methods.js");
	}

	@Test
	public void testTypedArrays() throws ScriptException {
		loadScript("samples/js/typed_arrays.js");
	}

	@Test
	public void testCollections() throws ScriptException {
		loadScript("samples/js/collections.js");
	}

	@Test
	public void testStreams() throws ScriptException {
		loadScript("samples/js/streams.js");
	}

	@Test
	public void testExtendingClasses() throws ScriptException {
		loadScript("samples/js/extending_classes.js");
	}

	@Test
	public void testParameterOverloading() throws ScriptException {
		loadScript("samples/js/parameter_overloading.js");
	}

	@Test
	public void testJavaBeans() throws ScriptException {
		loadScript("samples/js/java_beans.js");
	}

	@Test
	public void testFunctionLiterals() throws ScriptException {
		loadScript("samples/js/function_literals.js");
	}

	@Test
	public void testBindingProperties() throws ScriptException {
		loadScript("samples/js/binding_properties.js");
	}

	@Test
	public void testTrimmingStrings() throws ScriptException {
		loadScript("samples/js/trimming_strings.js");
	}

	@Test
	public void testSource() throws ScriptException {
		loadScript("samples/js/source.js");
	}

	@Test
	public void testImportScopes() throws ScriptException {
		loadScript("samples/js/import_scopes.js");
	}

	@Test
	public void testConvertingArrays() throws ScriptException {
		loadScript("samples/js/converting_arrays.js");
	}

	@Test
	public void testCallingSuper() throws ScriptException {
		loadScript("samples/js/calling_super.js");
	}

	@Test
	public void testLoadingScripts() throws ScriptException {
		loadScript("samples/js/loading_scripts.js");
	}

	@Test
	public void testEvaluatingAStatement() throws ScriptException {
		ScriptEngine scriptEngine = getScriptEngine();
		scriptEngine.eval("print('Hello, world!')");
	}

	@Test
	public void testEvaluatingAScriptFile() throws ScriptException {
		loadScript("samples/js/evaluating_a_script_file.js");
	}

	@Test
	public void testExposingAJavaObjectAsAGlobalVariable() throws ScriptException {
		ScriptEngine scriptEngine = getScriptEngine();

		File file = new File("test.txt");
		scriptEngine.put("file", file);
		scriptEngine.eval("print(file.getAbsolutePath())");
	}

	@Test
	public void testInvokingAScriptFunction()
			throws ScriptException, NoSuchMethodException {
		ScriptEngine scriptEngine = getScriptEngine();

		scriptEngine.eval("function hello(name) { print('Hello, ' + name); }");
		Invocable invocable = (Invocable) scriptEngine;
		invocable.invokeFunction("hello", "scripting!");
	}

	@Test
	public void testInvokingAScriptObjectMethod()
			throws ScriptException, NoSuchMethodException {
		ScriptEngine scriptEngine = getScriptEngine();

		scriptEngine.eval("var obj = new Object()");
		scriptEngine.eval("obj.hello = function(name) { print('Hello, ' + name); }");

		Object obj = scriptEngine.get("obj");

		Invocable invocable = (Invocable) scriptEngine;
		invocable.invokeMethod(obj, "hello", "script method!");
	}

	@Test
	public void testImplementingAJavaInterfaceWithScriptFunctions()
			throws ScriptException, InterruptedException {
		ScriptEngine scriptEngine = getScriptEngine();

		scriptEngine.eval("function run() { print('run() function called.'); }");

		Invocable invocable = (Invocable) scriptEngine;
		Runnable runnable = invocable.getInterface(Runnable.class);

		Thread thread = new Thread(runnable);
		thread.start();
		thread.join();
	}

	@Test
	public void testImplementingAJavaInterfaceWithScriptObjectMethods()
			throws ScriptException, InterruptedException {
		ScriptEngine scriptEngine = getScriptEngine();

		scriptEngine.eval("var obj = new Object()");
		scriptEngine.eval("obj.run = function() { print('obj.run() method called.'); }");

		Object obj = scriptEngine.get("obj");

		Invocable invocable = (Invocable) scriptEngine;

		Runnable runnable = invocable.getInterface(obj, Runnable.class);

		Thread thread = new Thread(runnable);
		thread.start();
		thread.join();
	}

	@Test
	public void testUsingMultipleScopes() throws ScriptException {
		ScriptEngine scriptEngine = getScriptEngine();

		scriptEngine.put("x", "hello");
		scriptEngine.eval("print(x)");

		Bindings bindings = scriptEngine.createBindings();
		bindings.put("x", "world");

		ScriptContext scriptContext = new SimpleScriptContext();
		scriptContext.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		assertThat(scriptContext.getBindings(ScriptContext.ENGINE_SCOPE))
				.isEqualTo(bindings);
		scriptEngine.eval("print(x)", scriptContext);

		scriptEngine.eval("print(x)");
	}

	private void loadScript(String scriptResourceName) throws ScriptException {
		ScriptEngine javaScriptEngine = getScriptEngine();

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(scriptResourceName);
		InputStreamReader reader = new InputStreamReader(inputStream);
		javaScriptEngine.eval(reader);
	}

	private ScriptEngine getScriptEngine() {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		return scriptEngineManager.getEngineByName("nashorn");
	}

	private static class Person {
	}

	public static String method1(String name) {
		System.out.format("Hi there from Java, %s%n", name);
		return "Greetings from Java";
	}

	public static void method2(Object object) {
		System.out.println(object.getClass());
		if (object instanceof ScriptObjectMirror) {
			ScriptObjectMirror mirror = (ScriptObjectMirror) object;
			System.out.println(mirror.getClassName());
		}
	}

	public static void method3(ScriptObjectMirror mirror) {
		System.out.println(mirror.getClassName() + ": " + Arrays.toString(mirror.getOwnKeys(true)));
	}

	public static void method4(ScriptObjectMirror person) {
		System.out.println("Full name is: " + person.callMember("getFullName"));
	}

	public static class SuperRunner implements Runnable {

		@Override
		public void run() {
			System.out.println("Super run");
		}

	}
	
}
