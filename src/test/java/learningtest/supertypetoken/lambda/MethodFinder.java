package learningtest.supertypetoken.lambda;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Interface for finding methods for lambdas.
 *
 * @author Johnny Lim
 */
public interface MethodFinder extends Serializable {

    default SerializedLambda serialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    default Class<?> getContainingClass() {
        String className = serialized().getImplClass().replaceAll("/", ".");
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    default Method method() {
        SerializedLambda lambda = serialized();
        Class<?> containingClass = getContainingClass();
        return Arrays.stream(containingClass.getDeclaredMethods())
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(UnableToGuessMethodException::new);
    }

    default Parameter parameter(int n) {
        return method().getParameters()[n];
    }

    class UnableToGuessMethodException extends RuntimeException {
    }

}
