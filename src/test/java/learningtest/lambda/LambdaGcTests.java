package learningtest.lambda;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Tests for GC with lambdas.
 *
 * @author Johnny Lim
 */
public class LambdaGcTests {

    @Test
    public void test() {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(new A().get(), 1, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(new B().get(), 1, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(new C().get(), 1, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(new D().get(), 1, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(new E().get(), 1, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(new F().get(), 1, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(System::gc, 1, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static class A {

        public Runnable get() {
            return new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hi from A");
                }
            };
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("A finalized");
        }

    }

    private static class B {

        public Runnable get() {
            return () -> System.out.println("Hi from B");
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("B finalized");
        }

    }

    private static class C {

        private int count = 0;

        public Runnable get() {
            return () -> System.out.println("Hi from C #" + (++this.count));
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("C finalized");
        }

    }

    private static class D {

        private static int count = 0;

        public Runnable get() {
            return () -> System.out.println("Hi from D #" + (++count));
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("D finalized");
        }

    }

    private static class E {

        private int count = ThreadLocalRandom.current().nextInt();

        public Runnable get() {
            return () -> {
                int count = this.count;
                System.out.println("Hi from E #" + count);
            };
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("E finalized");
        }

    }

    private static class F {

        public Runnable get() {
            return this::friendly;
        }

        public void friendly() {
            System.out.println("Hi from F");
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("F finalized");
        }

    }

}
