package telran.util;


import java.io.PrintStream;

import org.junit.jupiter.api.Test;




public class LoggerTest {

	@Test
	void consoleTest() {
		Logger logger = new Logger(new SimpleStreamHandler(System.out), "newLogger");
		logger.setLevel(Level.WARN);
		logger.error("An ERROR message");
		logger.warn("A WARNING message");
		logger.info("This is a INFO level message");
		logger.trace("This is a TRACE level message");
		
}
	

	@Test
	void fileTest() throws Exception {
		String fileName = "newLogger";
		try(PrintStream printLog = new PrintStream(fileName))
		{
			
			SimpleStreamHandler handler = new SimpleStreamHandler(printLog);
			Logger logger = new Logger(handler, "newLogger");
			logger.setLevel(Level.DEBUG);
			logger.debug("DEBUG message was sent to server");
			logger.error("An ERROR message");
			logger.warn("A WARNING message");
			logger.info("This is a INFO level message");
			logger.trace("This is a TRACE level message");
		};
}
	
	}

