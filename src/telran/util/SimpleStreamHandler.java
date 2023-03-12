package telran.util;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SimpleStreamHandler implements Handler {

	protected PrintStream stream;
	
	public SimpleStreamHandler (PrintStream stream) {
	super();
	this.stream = stream;
	
	}
	
	@Override
	public void publish(LoggerRecord loggerRecord) {
		LocalDateTime dateTime = LocalDateTime.ofInstant(loggerRecord.timestamp, ZoneId.of(loggerRecord.zoneId));
		stream.println(dateTime + " " 
				+ loggerRecord.loggerName + " " + loggerRecord.level + " " + loggerRecord.message);
	
	}
}
