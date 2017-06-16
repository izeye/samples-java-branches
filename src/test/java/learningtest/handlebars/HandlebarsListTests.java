package learningtest.handlebars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

	private static final String RENDER_WITH_JSON_FUNCTION_NAME = "renderWithJson";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testRenderWithObjectHavingList() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);

		String template = "<ul>{{#each persons}}<li>{{this.firstName}} {{this.lastName}} is {{this.age}} years old.</li>{{/each}}</ul>";

		Map<String, Object> model = createModel();

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo(
				"<ul><li>Johnny Lim is 20 years old.</li><li>John Kim is 21 years old.</li></ul>");
	}

	@Test
	public void testRenderWithObjectHavingListAndReferenceParent() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);

		String template = "<ul>{{#each persons}}<li>{{this.firstName}} {{this.lastName}} is {{this.age}} years old. Hey, {{../key}}!</li>{{/each}}</ul>";

		Map<String, Object> model = createModel();

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo(
				"<ul><li>Johnny Lim is 20 years old. Hey, value!</li><li>John Kim is 21 years old. Hey, value!</li></ul>");
	}

	@Test
	public void testRenderWithObjectHavingListAndUseRoot() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);

		String template = "<ul>{{#each persons}}<li>{{firstName}} {{lastName}} is {{age}} years old. Hey, {{@root.key}}!</li>{{/each}}</ul>";

		Map<String, Object> model = createModel();

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo(
				"<ul><li>Johnny Lim is 20 years old. Hey, value!</li><li>John Kim is 21 years old. Hey, value!</li></ul>");
	}

	private Map<String, Object> createModel() {
		Map<String, Object> model = new HashMap<>();
		model.put("persons", createPersons());
		model.put("key", "value");
		return model;
	}

	private List<Person> createPersons() {
		List<Person> persons = new ArrayList<>();

		Person person1 = new Person();
		person1.setFirstName("Johnny");
		person1.setLastName("Lim");
		person1.setAge(20);

		List<String> hobbies1 = new ArrayList<>();
		hobbies1.add("hobby1");
		hobbies1.add("hobby2");
		person1.setHobbies(hobbies1);

		persons.add(person1);

		Person person2 = new Person();
		person2.setFirstName("John");
		person2.setLastName("Kim");
		person2.setAge(21);

		List<String> hobbies2 = new ArrayList<>();
		hobbies2.add("hobby3");
		person2.setHobbies(hobbies2);

		persons.add(person2);
		return persons;
	}

	@Test
	public void testRenderWithObjectHavingNestedList() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);

		String template = "<ul>{{#each persons}}<li>{{this.firstName}} {{this.lastName}} is {{this.age}} years old and my hobbies are {{#each hobbies}}{{this}}, {{/each}}.</li>{{/each}}</ul>";

		Map<String, Object> model = createModel();

		Invocable invocable = (Invocable) javaScriptEngine;

		this.thrown.expect(ScriptException.class);
		this.thrown.expectMessage("TypeError: context.hasOwnProperty is not a function in <eval> at line number 743");
		invocable.invokeFunction(RENDER_FUNCTION_NAME, template, model);
	}

	@Test
	public void testRenderWithJsonHavingNestedList() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);

		String template = "<ul>{{#each persons}}<li>{{this.firstName}} {{this.lastName}} is {{this.age}} years old and my hobbies are {{#each hobbies}}{{this}}, {{/each}}.</li>{{/each}}</ul>";

		Map<String, Object> model = createModel();
		String json = toJson(model);

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_WITH_JSON_FUNCTION_NAME, template, json);
		assertThat(rendered).isEqualTo(
				"<ul><li>Johnny Lim is 20 years old and my hobbies are hobby1, hobby2, .</li><li>John Kim is 21 years old and my hobbies are hobby3, .</li></ul>");
	}

	private String toJson(Map<String, Object> model) {
		try {
			return objectMapper.writeValueAsString(model);
		}
		catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
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

		private List<String> hobbies;

	}

}
