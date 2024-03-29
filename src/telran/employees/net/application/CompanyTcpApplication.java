package telran.employees.net.application;

import telran.employees.Company;
import telran.employees.CompanyImplementation;
import telran.employees.ProtocolCompanyImplem;
import telran.net.Protocol;
import telran.net.TcpServer;

public class CompanyTcpApplication {
	
	public static void main (String[] args) throws Exception {
		Company company = new CompanyImplementation();
		company.restore("company.data");
		Protocol protocol = new ProtocolCompanyImplem(company);
		TcpServer server = new TcpServer (protocol, 4000);
		server.run();
	}
	

}
