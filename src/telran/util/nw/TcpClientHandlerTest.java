package telran.util.nw;

import java.net.*;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



import java.io.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TcpClientHandlerTest {

	private static final String HOSTNAME = "localhost";
	private static final int PORT = 6000;
	
	static Socket socket;
	static BufferedReader input;
	static PrintStream output;
	static TcpClientHandler client;
	static Logger LOG;

	@BeforeAll
	static void setUp() throws Exception {
			socket = new Socket(HOSTNAME, PORT);
			output = new PrintStream(socket.getOutputStream());
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			client = new TcpClientHandler(socket, output, input);
			LOG = new Logger(client, "newLogger");	
	}

	
	@Test
	@Order (1)
	void debugTest() throws Exception {
		LOG.setLevel(Level.DEBUG);
		LOG.debug("send debug message");
		output.println("count#debug");
		System.out.println(input.readLine() + " records" );
		LOG.debug("one more debug message");
		output.println("count#debug");
		System.out.println(input.readLine() + " records");
	}
	
	@Test
	@Order (2)
	void warningTest() throws Exception  {
		LOG.setLevel(Level.WARN);
		LOG.trace("wrong level");
		LOG.warn("send warning message");
		output.println("count#warn");
		System.out.println(input.readLine() + " records");
	}
	
	@AfterAll
	public static void close() throws Exception {
		socket.close();
	}

}


