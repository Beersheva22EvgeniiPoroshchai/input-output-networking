package telran.util.nw;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TcpClientHandler implements Handler {

	private Socket socket;
	private PrintStream output;
	private BufferedReader input;
	
	
	public TcpClientHandler(Socket socket, PrintStream output, BufferedReader input) {
		super();
		this.socket = socket;
		this.output = output;
		this.input = input;
}
	
	@Override
	public void publish(LoggerRecord loggerRecord) {
		
	LocalDateTime dateTime = LocalDateTime.ofInstant(loggerRecord.timestamp, ZoneId.of(loggerRecord.zoneId));
	String info = "log#" + dateTime + " "  
			+ loggerRecord.loggerName + "#" + loggerRecord.level;
		output.println(info);
		String response = null;
		try {
			response = input.readLine();
		} catch (IOException e) {
			System.out.println("the response hasn't been getting");
		}
		System.out.println(response);
	}



	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	




	


