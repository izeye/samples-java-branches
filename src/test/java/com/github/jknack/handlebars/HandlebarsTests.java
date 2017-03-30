package com.github.jknack.handlebars;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Handlebars}.
 *
 * @author Johnny Lim
 */
public class HandlebarsTests {

    @Test
    public void test() throws IOException {
        Handlebars handlebars = new Handlebars();
        Template template = handlebars.compileInline("Hello {{this}}!");
        assertThat(template.apply("Handlebars.java")).isEqualTo("Hello Handlebars.java!");
    }

}
