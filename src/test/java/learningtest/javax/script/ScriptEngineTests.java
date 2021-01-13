package learningtest.javax.script;

import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Tests for {@link ScriptEngine}.
 *
 * @author Johnny Lim
 */
class ScriptEngineTests {

    @Test
    void nashorn() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello, world!');");
    }

    @Test
    void graaljs() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
        engine.eval("print('Hello, world!');");
    }

}
