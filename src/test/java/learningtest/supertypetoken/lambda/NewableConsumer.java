package learningtest.supertypetoken.lambda;

import java.util.function.Consumer;

/**
 * Consumer supporting a new instance creation.
 *
 * @author Johnny Lim
 */
public interface NewableConsumer<T> extends Consumer<T>, Newable<T> {
}
