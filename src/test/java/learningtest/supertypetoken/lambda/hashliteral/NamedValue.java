package learningtest.supertypetoken.lambda.hashliteral;

import learningtest.supertypetoken.lambda.MethodFinder;

import java.util.Objects;
import java.util.function.Function;

/**
 * Named value for hash literals.
 *
 * @author Johnny Lim
 */
public interface NamedValue<T> extends Function<String, T>, MethodFinder {

    default String name() {
        checkParametersEnabled();
        return parameter(0).getName();
    }

    default void checkParametersEnabled() {
        if (Objects.equals("arg0", parameter(0).getName())) {
            throw new IllegalStateException(
                    "You need to compile with java '-parameters' for parameter reflection to work; You also need 'java' 8u60 or newer to use it with lambdas");
        }
    }

    default T value() {
        return apply(name());
    }

}
