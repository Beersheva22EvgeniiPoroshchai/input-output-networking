package telran.util.nw;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LoggerRecord {
	
	final Instant timestamp;
	final String zoneId;
	final Level level;
	final String loggerName;
	final String message;
	
	public LoggerRecord(Instant timestamp, String zoneId, Level level, String loggerName, String message) {
		super();
		this.timestamp = timestamp;
		this.zoneId = zoneId;
		this.level = level;
		this.loggerName = loggerName;
		this.message = message;
	}
}
