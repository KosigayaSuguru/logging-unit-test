/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package logging.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogging {

	Logger logger = LoggerFactory.getLogger(getClass());

    public void loggingMethod() {
    	logger.info("auaua");
    	logger.info("iuiui");
    	logger.info("uuuuu");
    	logger.info("eueue");
    	logger.info("ououo");
    }
}
