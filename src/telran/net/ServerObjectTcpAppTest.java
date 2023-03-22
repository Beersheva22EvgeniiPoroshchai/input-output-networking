package telran.net;

public class ServerObjectTcpAppTest {
	
	static final int PORT = 5000;
	
	public static void main(String[] args) {
		
		
		try {
			TcpServer server = new TcpServer(new ProtocolImplementation(), PORT);
			server.run();
		} catch (Exception e) {
			e.toString();
		}
		
	}
	
	

}
