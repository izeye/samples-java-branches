package learningtest.com.github.dockerjava.api;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Test;

/**
 * Tests for {@link DockerClient}.
 *
 * @author Johnny Lim
 */
public class DockerClientTests {

	@Test
	public void test() {
		DockerClient dockerClient = DockerClientBuilder.getInstance().build();
		Info info = dockerClient.infoCmd().exec();
		System.out.println(info);
	}

}
