package telran.employees;

import telran.net.TcpServer;

public class NetworkCompanyTcpApp {
	
	static final int PORT = 4000;

	public static void main (String[] args) throws Exception {
		try {
			TcpServer server = new TcpServer (new ProtocolCompanyImplem(), PORT);
			server.run();
			} catch (Exception e) {
			throw new Exception (e.toString());
		}
	}
}


