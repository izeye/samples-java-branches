package learningtest.org.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by izeye on 15. 8. 26..
 */
public class LoggerTests {
  
  Logger logger = LoggerFactory.getLogger(getClass());
  
  @Test
  public void testParameterizedMessage() {
    logger.debug("This is a test.");
    
    // Will use `void debug(String format, Object arg);`.
    logger.debug("My name is {}", "Johnny");

    RuntimeException ex = new RuntimeException("Test exception");
    // Will use `void debug(String msg, Throwable t);`.
    // So `{}` doesn't work as expected.
    logger.debug("Unexpected exception: {}", ex);
  }
  
}
