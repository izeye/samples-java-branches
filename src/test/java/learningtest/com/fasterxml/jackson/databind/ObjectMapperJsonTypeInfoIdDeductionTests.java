package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectMapper} with {@link JsonTypeInfo.Id#DEDUCTION}.
 *
 * @author Johnny Lim
 */
class ObjectMapperJsonTypeInfoIdDeductionTests {

    @Test
    void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Apple apple = new Apple();
        apple.setType("apple");
        apple.setAppleSpecific("apple-specific");

        Basket basket1 = new Basket();
        basket1.setFruit(apple);

        String json1 = mapper.writeValueAsString(basket1);

        Basket readBasket1 = mapper.readValue(json1, Basket.class);
        Fruit fruit1 = readBasket1.getFruit();
        assertThat(fruit1).isInstanceOf(Apple.class);
        assertThat(((Apple) fruit1).getAppleSpecific()).isEqualTo(apple.getAppleSpecific());

        Banana banana = new Banana();
        banana.setType("banana");
        banana.setBananaSpecific("banana-specific");

        Basket basket2 = new Basket();
        basket2.setFruit(banana);

        String json2 = mapper.writeValueAsString(basket2);

        Basket readBasket2 = mapper.readValue(json2, Basket.class);
        Fruit fruit2 = readBasket2.getFruit();
        assertThat(fruit2).isInstanceOf(Banana.class);
        assertThat(((Banana) fruit2).getBananaSpecific()).isEqualTo(banana.getBananaSpecific());
    }

    static class Basket {

        private Fruit fruit;

        public Fruit getFruit() {
            return this.fruit;
        }

        public void setFruit(Fruit fruit) {
            this.fruit = fruit;
        }

    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    @JsonSubTypes({ @JsonSubTypes.Type(Apple.class), @JsonSubTypes.Type(Banana.class) })
    static abstract class Fruit {

        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    static class Apple extends Fruit {

        private String appleSpecific;

        public String getAppleSpecific() {
            return this.appleSpecific;
        }

        public void setAppleSpecific(String appleSpecific) {
            this.appleSpecific = appleSpecific;
        }

    }

    static class Banana extends Fruit {

        private String bananaSpecific;

        public String getBananaSpecific() {
            return this.bananaSpecific;
        }

        public void setBananaSpecific(String bananaSpecific) {
            this.bananaSpecific = bananaSpecific;
        }

    }

}
