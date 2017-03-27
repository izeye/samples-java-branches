package learningtest.supertypetoken.lambda;

import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for lambda type references.
 *
 * @author Johnny Lim
 */
public class LambdaTypeReferenceTests {

    @Test
    public void testArrayList() {
        ArrayList<String> list = create(i -> i);
        list.add("hello");
        list.add("world");
        list.forEach(System.out::println);

        assertThat(list).containsExactly("hello", "world");
    }

    @Test
    public void testLinkedHashMap() {
        LinkedHashMap<String, Integer> map = create(i -> i);
        map.put("hello", 1);
        map.put("world", 2);
        map.entrySet().forEach(System.out::println);

        assertThat(map).containsEntry("hello", 1).containsEntry("world", 2);
    }

    @Test
    public void testType() {
        TypeReference<String> typeReference = i->i;
        System.out.println(typeReference.type().getSimpleName());
    }

    @Test
    public void testParameterObject() {
        listCustomersWithConsumer(config -> {
            config.includeHidden = false;
            config.companyName = "A Company";
        });

        listCustomersWithParameters(config -> {
            config.includeHidden = false;
            config.companyName = "A Company";
        });

        listCustomersWithParametersHavingGenerics(list -> {
            list.add("Hello");
            list.add("World");
        });
    }

    @ToString
    public static class CustomerQueryOptions {
        public Date from = null;
        public boolean includeHidden = true;
        public String companyName = null;
        public boolean haveOrders = true;
    }

    private void listCustomersWithConsumer(Consumer<CustomerQueryOptions> consumer) {
        CustomerQueryOptions queryOptions = new CustomerQueryOptions();
        consumer.accept(queryOptions);

        System.out.println(queryOptions);
    }

    private void listCustomersWithParameters(Parameters<CustomerQueryOptions> spec) {
        System.out.println(spec.get());
    }

    private void listCustomersWithParametersHavingGenerics(Parameters<ArrayList<String>> params) {
        params.get().forEach(System.out::println);
    }

    public static <T> T create(TypeReference<T> type) {
        return type.newInstance();
    }

}
