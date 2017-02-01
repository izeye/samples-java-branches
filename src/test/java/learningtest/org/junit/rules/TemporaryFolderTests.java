package learningtest.org.junit.rules;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TemporaryFolder}.
 * 
 * @author Johnny Lim
 */
public class TemporaryFolderTests {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testNewFile() throws IOException {
		assertThat(this.folder.newFile("myfile.txt").exists()).isTrue();
		assertThat(this.folder.newFile("subfolder").exists()).isTrue();
	}

}
