package learningtest.org.apache.tomcat;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for embedded Tomcat.
 *
 * @author Johnny Lim
 */
public class TomcatTests {

	// NOTE: This test will take at least 30 seconds due to sleep.
	@Ignore
	@Test
	public void test() {
		Tomcat tomcat = new Tomcat();
		try {
			tomcat.start();

			stopAfter30Seconds(tomcat);

			String name = "Tomcat:type=Server";

			MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
			Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName(name), null);
			assertThat(objectNames.stream().map(ObjectName::getCanonicalName)).containsExactly(name);

			tomcat.getServer().await();
		}
		catch (LifecycleException ex) {
			throw new RuntimeException(ex);
		}
		catch (MalformedObjectNameException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void stopAfter30Seconds(Tomcat tomcat) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(30);
				tomcat.stop();
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			catch (LifecycleException ex) {
				throw new RuntimeException(ex);
			}
		}).start();
	}

}
