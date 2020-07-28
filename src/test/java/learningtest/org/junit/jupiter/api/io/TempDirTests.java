package learningtest.org.junit.jupiter.api.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TempDir}.
 *
 * @author Johnny Lim
 */
class TempDirTests {

    @Test
    void test(@TempDir Path tempDir) throws IOException {
        System.out.println(tempDir);

        String text = "Hello, world!";

        Path file = tempDir.resolve("test.txt");
        try (FileWriter fw = new FileWriter(file.toFile())) {
            fw.write(text);
        }
        assertThat(Files.readAllLines(file)).containsExactly(text);
    }

}
