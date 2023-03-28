package telran.employees.net.application;

import telran.employees.Company;
import telran.employees.CompanyImplementation;
import telran.employees.ProtocolCompanyImplem;
import telran.net.Protocol;
import telran.net.TcpServer;
import telran.net.UdpServer;

public class CompanyUdpApplication {

	public static void main(String[] args) throws Exception {
		
		Company company = new CompanyImplementation();
		company.restore("company.data");
		Protocol protocol = new ProtocolCompanyImplem(company);
		UdpServer server = new UdpServer (4000, protocol);
		server.run();
	}
		
		

	}


