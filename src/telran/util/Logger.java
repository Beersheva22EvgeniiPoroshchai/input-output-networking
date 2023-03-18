package telran.util;
import java.time.Instant;
import java.time.ZoneId;

public class Logger {

private Level level = Level.INFO;	
private Handler handler;
private String name;

public Logger(Handler handler, String name) {
	super();
	this.handler = handler;
	this.name = name;
}
 

public void setLevel(Level level) {
	this.level = level;
}

public boolean checkLevel (Level level, Level LevelByParam) {
	return level.compareTo(LevelByParam) < 1;
}


public void error(String message) {
	if (checkLevel(level, Level.ERROR)) {
		LoggerRecord error = createNewLoggerRecord(message, Level.ERROR);
	handler.publish(error);
	}
}

public void warn(String message) {
	if (checkLevel(level, Level.WARN)) {
		LoggerRecord warn = createNewLoggerRecord(message, Level.WARN);
		handler.publish(warn);
	}
}

public void info(String message) {
	if (checkLevel(level, Level.INFO)) {
		LoggerRecord info = createNewLoggerRecord(message, Level.INFO);
		handler.publish(info);
	}
}

public void debug(String message) {
	if (checkLevel(level, Level.DEBUG)) {
		LoggerRecord debug = createNewLoggerRecord(message, Level.DEBUG);
		handler.publish(debug);
	}
}

public void trace(String message) {
	if (checkLevel(level, Level.TRACE)) {
		LoggerRecord trace = createNewLoggerRecord(message, Level.TRACE);
		handler.publish(trace);
		}
	}

private LoggerRecord createNewLoggerRecord(String message, Level level) {
	return new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
		level, name, message);

}
}
