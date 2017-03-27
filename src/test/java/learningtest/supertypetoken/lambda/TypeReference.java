package learningtest.supertypetoken.lambda;

/**
 * Type reference for lambdas similar to super-type tokens.
 *
 * @author Johnny Lim
 */
public interface TypeReference<T> extends Newable<T> {

    T typeIs(T t);

}
