package telran.employees;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import telran.employees.*;
import telran.net.NetworkClient;
import telran.net.TcpClient;
import telran.net.UdpClient;


public class NetworkCompanyImplem implements Company {

	private static final long serialVersionUID = 1L;
		
	NetworkClient newClient;

	public NetworkCompanyImplem() {
		try {
			newClient = new TcpClient("localhost", NetworkCompanyTcpApp.PORT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Iterator<Employee> iterator() {
	return getAllEmployees().iterator();
		}
	
	@Override
	public boolean addEmployer(Employee employee) {
		return newClient.send("addEmployer", employee);
	}

	@Override
	public Employee removeEmployee(long id) {
		return newClient.send("removeEmployee", id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return newClient.send("getAllEmployees", new ArrayList<>());   
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return newClient.send("getEmployeesByMonthBirth", month);
	}

	
	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return newClient.send("getEmployeesBySalary", new int[] {salaryFrom, salaryTo});
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return newClient.send("getEmployeesByDepartment", department);
	}

	@Override
	public Employee getEmployee(long id) {
		return newClient.send("getEmployee", id);
	}

	@Override
	public void save(String pathName) {
		newClient.send("save", pathName);

	}

	@Override
	public void restore(String pathName) {
		newClient.send("restore", pathName);

	}
	
	public void close() throws IOException {
		newClient.close();
	}

}
