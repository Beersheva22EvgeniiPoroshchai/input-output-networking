package telran.empoyees.test;

import java.io.Closeable;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import telran.employees.NetworkCompanyImplem;
import telran.net.TcpClient;

public class CompanyTcpTest extends CompanyTest {

	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		company = new NetworkCompanyImplem(new TcpClient("localhost", 4000));
		company.forEach(e -> company.removeEmployee(e.getId()));
		super.setUp();
	}
	
	@AfterEach
	void closeConnection() throws IOException {
		((Closeable) company).close();
	}
	
}
