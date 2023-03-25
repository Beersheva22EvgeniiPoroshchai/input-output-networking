package telran.net;

import java.io.*;
import java.net.*;


public class ServerTcpExampleTest {
	
	private static final int PORT = 4000;

	public static void main(String[] args) throws Exception{
		TcpServer server = new TcpServer(new ProtocolImplementation(), PORT);
		server.run();

	
	}


}





