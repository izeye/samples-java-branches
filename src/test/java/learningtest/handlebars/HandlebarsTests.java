package learningtest.handlebars;

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
 * Tests for Handlebars.
 *
 * @author Jungmuk Lim
 */
public class HandlebarsTests {

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

		String template = "{{firstName}} {{lastName}} is {{age}} years old.";

		Map<String, Object> model = new HashMap<>();
		model.put("firstName", "Johnny");
		model.put("lastName", "Lim");
		model.put("age", 20);

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo("Johnny Lim is 20 years old.");
	}

	@Test
	public void testHelper() throws ScriptException, NoSuchMethodException {
		ScriptEngine javaScriptEngine = getScriptEngine();
		loadScripts(javaScriptEngine);
		loadScript(javaScriptEngine, "learningtest/handlebars/sample_helper.js");

		String template = "{{fullName user}} is {{user.age}} years old.";

		Map<String, Object> model = new HashMap<>();
		Map<String, Object> user = new HashMap<>();
		user.put("firstName", "Johnny");
		user.put("lastName", "Lim");
		user.put("age", 20);

		model.put("user", user);

		Invocable invocable = (Invocable) javaScriptEngine;
		String rendered = (String) invocable.invokeFunction(
				RENDER_FUNCTION_NAME, template, model);
		assertThat(rendered).isEqualTo("Johnny Lim is 20 years old.");
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

}
