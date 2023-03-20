package telran.util.nw;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerLogAppl {
	
	private static final int PORT = 6000;
	private static Map<String, Integer> counter = new HashMap<>();
	
	public static void main(String[] args) throws Exception {

		
	
	@SuppressWarnings("resource")
	ServerSocket serverSocket = new ServerSocket(PORT);
	System.out.println("Server waiting on port: " + PORT);
	while(true) {
	Socket socket = serverSocket.accept();
				try {
					runClientHandler(socket);
				} catch (Exception e) {
					System.out.println("abnormal closing connection");
			}
		}
	}
	

	private static void runClientHandler(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream writer = new PrintStream(socket.getOutputStream());
		while (true) {
			String request = reader.readLine();
			if (request == null) {
				break;
			}
			if (request.equalsIgnoreCase("exit")) {
				break;
			}	
			String response = getResponse(request);
			writer.println(response);
		}
		System.out.println("connection has been closed");
		
	}

	private static String getResponse(String request) {
		String res = "wrong type of request";
		String tokens[] = request.split("#");
		if (tokens.length > 1) {
			res = switch (tokens[0]) {
			case "log" -> logWayToRequest(tokens[1],tokens[2]);
			case "count" -> getAmountOfLogs(tokens[1]);
			default -> "Wrong type: " + tokens[0];
			};
		}
		return res;
	}
		
		private static String getAmountOfLogs(String level) {
		 String res = null;
		 try {
			res = counter.get(level.toUpperCase()).toString() + " " + level;
		} catch (Exception e) {
			res = "wrong log level";
		}
		
		return res;
	}

	private static String logWayToRequest(String message, String level) {
		String res = null;
		try {
			Integer amount = counter.get(level);
			if (amount == null) {
				amount = 1; 
			} else {
				++amount;
			}
			counter.put(level, amount);
			res = "Ok";
			} catch (Exception e) {
			res = "the record has not been saved";
		}
		return res;
	}

	
}
	


