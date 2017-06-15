package learningtest.handlebars;

import lombok.Data;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Handlebars complex helper.
 *
 * @author Johnny Lim
 */
public class HandlebarsComplexHelperTests {

	private static final String SCRIPT_ENGINE_NAME = "nashorn";

	private static final String SCRIPT_RESOURCE_NAMES[] = new String[] {
			"libs/js/handlebars/handlebars-v4.0.10.js",
			"learningtest/handlebars/render.js"
	};

	private static final String RENDER_FUNCTION_NAME = "render";

	@Test
	public void testComplexHelper() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);
		loadScript(javaScriptEngine, "learningtest/handlebars/sample_complex_helper.js");

		String template = "{{complexHelper person metadata}} and is {{person.age}} years old.";

		Map<String, Object> model = new HashMap<>();

		Person person = new Person();
		person.setFirstName("Johnny");
		person.setLastName("Lim");
		person.setAge(20);

		SomeService someService = new DefaultSomeService();
		SomeContext someContext = new SomeContext();
		someContext.setValue1("abc");
		someContext.setValue2("xyz");

		Metadata metadata = new Metadata();
		metadata.setSomeService(someService);
		metadata.setSomeContext(someContext);

		model.put("person", person);
		model.put("metadata", metadata);

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo("Johnny Lim has abc and xyz and is 20 years old.");
	}

	private ScriptEngine getScriptEngine() {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		return scriptEngineManager.getEngineByName(SCRIPT_ENGINE_NAME);
	}

	private void loadScripts(ScriptEngine javaScriptEngine) {
		for (String scriptResourceName : SCRIPT_RESOURCE_NAMES) {
			loadScript(javaScriptEngine, scriptResourceName);
		}
	}

	private void loadScript(ScriptEngine javaScriptEngine, String scriptResourceName) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(scriptResourceName);
		InputStreamReader reader = new InputStreamReader(inputStream);
		try {
			javaScriptEngine.eval(reader);
		}
		catch (ScriptException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Data
	public class Person {

		private String firstName;
		private String lastName;
		private int age;

	}

	@Data
	private class SomeContext {

		private String value1;
		private String value2;

	}

	public interface SomeService {

		String doService(SomeContext someContext, Person person);
	}

	private static class DefaultSomeService implements SomeService {

		@Override
		public String doService(SomeContext someContext, Person person) {
			return person.getFirstName() + " " + person.getLastName() + " has " + someContext.getValue1() + " and "
					+ someContext.getValue2();
		}

	}

	@Data
	public static class Metadata {

		private SomeService someService;
		private SomeContext someContext;

	}

}
