package learningtest.supertypetoken.lambda;

/**
 * Parameter object using lambda type reference.
 *
 * @author Johnny Lim
 */
public interface Parameters<T> extends NewableConsumer<T> {

    default T get() {
        T t = newInstance();
        accept(t);
        return t;
    }

}
