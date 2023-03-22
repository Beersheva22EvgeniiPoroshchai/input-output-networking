package telran.net;

public class NetworkClientSeverObjectTcpAppTest {

	
	
	public static void main(String[] args) {
		TcpClient client;
		
		try {
			client = new TcpClient("localhost", ServerObjectTcpAppTest.PORT);
			Response response = client.send("reverse", "abcdefgh");
			 System.out.printf("%s%s", response.code, response.data);
			
			 response = client.send("length", "London is the capital of Great Britain");
			 System.out.printf("%s%s", response.code, response.data);
			 
			} catch (Exception e) {
			e.getMessage();
		}
		
			}

}
