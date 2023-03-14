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

public boolean checkLevel (Level level, Level LevelbyParam) {
	return level.compareTo(LevelbyParam) < 1;
}


public void error(String message) {
	if (checkLevel(level, Level.ERROR)) {
		LoggerRecord error = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
			Level.ERROR, name, message);
	handler.publish(error);
	}
	
}
public void warn(String message) {
	if (checkLevel(level, Level.WARN)) {
		LoggerRecord warn = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
				Level.WARN, name, message);
		handler.publish(warn);
	}
}

public void info(String message) {
	if (checkLevel(level, Level.INFO)) {
		LoggerRecord info = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
				Level.INFO, name, message);
		handler.publish(info);
	}
}

public void debug(String message) {
	if (checkLevel(level, Level.DEBUG)) {
		LoggerRecord debug = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
				Level.DEBUG, name, message);
		handler.publish(debug);
	}
}

public void trace(String message) {
	if (checkLevel(level, Level.TRACE)) {
		LoggerRecord trace = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
				Level.TRACE, name, message);
		handler.publish(trace);
		}
	}
}
