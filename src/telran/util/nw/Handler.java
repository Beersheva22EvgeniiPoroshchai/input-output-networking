package telran.util.nw;

public interface Handler {
	void publish (LoggerRecord loggerRecord);
	default void close() {
	}
}
