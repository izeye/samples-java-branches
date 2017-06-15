package learningtest.handlebars;

import lombok.Data;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Handlebars with {@link List}.
 *
 * @author Johnny Lim
 */
public class HandlebarsListTests {

	private static final String SCRIPT_ENGINE_NAME = "nashorn";

	private static final String SCRIPT_RESOURCE_NAMES[] = new String[] {
			"libs/js/handlebars/handlebars-v4.0.10.js",
			"learningtest/handlebars/render.js"
	};

	private static final String RENDER_FUNCTION_NAME = "render";

	@Test
	public void test() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);

		String template = "<ul>{{#each persons}}<li>{{this.firstName}} {{this.lastName}} is {{this.age}} years old.</li>{{/each}}</ul>";

		List<Person> persons = new ArrayList<>();

		Person person1 = new Person();
		person1.setFirstName("Johnny");
		person1.setLastName("Lim");
		person1.setAge(20);
		persons.add(person1);

		Person person2 = new Person();
		person2.setFirstName("John");
		person2.setLastName("Kim");
		person2.setAge(21);
		persons.add(person2);

		Map<String, Object> model = new HashMap<>();
		model.put("persons", persons);

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo(
				"<ul><li>Johnny Lim is 20 years old.</li><li>John Kim is 21 years old.</li></ul>");
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
	public static class Person {

		private String firstName;
		private String lastName;
		private int age;

	}

}
