package learningtest.javax.script;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.stream.Stream;

/**
 * Tests for {@link ScriptEngine}.
 *
 * @author Johnny Lim
 */
class ScriptEngineTests {

    @ParameterizedTest
    @MethodSource("getEngineNames")
    void basic(String engineByName) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(engineByName);
        engine.eval("print('Hello, world!');");
    }

    private static Stream<String> getEngineNames() {
        return Stream.of("nashorn", "graal.js");
    }

}
