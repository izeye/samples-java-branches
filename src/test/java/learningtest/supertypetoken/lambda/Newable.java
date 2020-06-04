package learningtest.supertypetoken.lambda;

/**
 * Interface for creating a new instance.
 *
 * @author Johnny Lim
 */
public interface Newable<T> extends MethodFinder {

    default Class<T> type() {
        return (Class<T>) parameter(0).getType();
    }

    default T newInstance() {
        try {
            return type().getDeclaredConstructor().newInstance();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
