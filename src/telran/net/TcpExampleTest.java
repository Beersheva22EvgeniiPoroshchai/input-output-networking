package telran.net;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class TcpExampleTest {
static NetworkClient client; 
	@BeforeAll
	static void connection() throws Exception {
		client = new TcpClient("localhost", 4000);
	}

	@Test
	void test() {
		assertEquals("olleH", client.send("reverse", "Hello"));
		Integer expected = 5;
		assertEquals(expected, client.send("length", "Hello"));
	}
	@AfterAll
	static void disconnection() throws IOException {
		client.close();
	}

}





