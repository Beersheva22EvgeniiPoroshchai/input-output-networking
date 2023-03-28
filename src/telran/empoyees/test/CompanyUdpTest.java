package telran.empoyees.test;

import java.io.Closeable;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import telran.employees.NetworkCompanyImplem;

import telran.net.UdpClient;

public class CompanyUdpTest extends CompanyTest {

	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		company = new NetworkCompanyImplem(new UdpClient("localhost", 4000));
		company.forEach(e -> company.removeEmployee(e.getId()));
		super.setUp();
	}
	
}
	

