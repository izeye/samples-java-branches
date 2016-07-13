package learningtest.javax.script;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by izeye on 16. 7. 13..
 */
public class ScriptEngineTests {
	
	@Test
	public void test() throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		javaScriptEngine.eval("someId = 10");
		javaScriptEngine.eval("anotherId = 20");
		assertTrue((Boolean) javaScriptEngine.eval("someId == 10"));
		assertFalse((Boolean) javaScriptEngine.eval("someId != 10"));
		assertTrue((Boolean) javaScriptEngine.eval("someId == 10 && anotherId == 20"));
	}
	
}
