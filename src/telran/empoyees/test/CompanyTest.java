package telran.empoyees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.employees.*;

class CompanyTest {
	
	String fileName = "company.data";
	
	Company company = new CompanyImplementation();
	
	Employee joe = new Employee(801001, "Joe", LocalDate.of(1981, 12, 07), "Lawers", 13400);
	Employee rohn = new Employee(801002, "Rohn", LocalDate.of(1992, 01, 01), "Developers", 19300);
	Employee anel = new Employee(801003, "Anel", LocalDate.of(1997, 11, 28), "Lawers", 17000);
	Employee tracy = new Employee(801004, "Tracy", LocalDate.of(1985, 11, 30), "Developers", 18900);
	Employee steven = new Employee(801005, "Steven", LocalDate.of(1978, 04, 22), "Developers", 22000);


	@BeforeEach
	void setUp() {
		company.addEmployer(joe);
		company.addEmployer(rohn);
		company.addEmployer(anel);
	}

	@Test
	void AddTest() throws FileNotFoundException, IOException {
		assertTrue(company.addEmployer(tracy)); 
		assertTrue(company.addEmployer(steven)); 
		assertFalse(company.addEmployer(rohn)); 
	}
	
	@Test
	void removeTest() {
		assertEquals(joe, company.removeEmployee(801001));
		assertEquals(null, company.removeEmployee(801001));
		List<Employee> employees = company.getAllEmployees();
		assertEquals(2, employees.size());
	}
	
	@Test
	void getEmployeesBySalary() {
		company.addEmployer(tracy);
		company.addEmployer(steven);
		List<Employee> employees = company.getEmployeesBysalary(17000, 19300);
		assertEquals(3, employees.size());
	}
	
	@Test
	void getEmployeesByMonthTest() {
		company.addEmployer(tracy);
		company.addEmployer(steven);
		List<Employee> employees = company.getEmployeesByMonthBirth(11);
		assertEquals(2, employees.size());
	}
	
	@Test
	void getEmployeesByDepartmentTest() {
		company.addEmployer(tracy);
		company.addEmployer(steven);
		List<Employee> employees = company.getEmployeesByDepartment("Developers");
		assertEquals(3, employees.size());
}

	@Test
	void saveRestoreTest() {
		company.addEmployer(tracy);
		company.addEmployer(steven);
		company.save(fileName);
		company.restore(fileName);
		company.forEach(p -> System.out.printf("%d, %s, %s,%s,%d\r", 
				p.getId(), p.getName(), p.getBirthDate().toString(), p.getDepartment(), p.getSalary()));

	}
	
}
