package telran.employees.controller;
import telran.employees.*;
import telran.view.*;
import telran.util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static telran.view.Item.*;

public class CompanyControllerItem {
	private static final int MIN_SALARY = 5_000;
	private static final int MAX_SALARY = 15_000;
	static private Company company;
	static private HashSet<String> departments;
	
	private CompanyControllerItem() {    //
	
	}
	
	static public Item[] getCompanyItems(Company company,
		String[] departments) {
		CompanyControllerItem.company = company;
		CompanyControllerItem.departments = new HashSet<>(Arrays.asList(departments));
		return getItems();
	}
	private static Item[] getItems() {
		
		return new Item[] {
			getAdminMenu(), getUserMenu() 	
		};
	}
	private static Item getUserMenu() {
		
		return new Menu("User actions", of("Display Employee Data",CompanyControllerItem::getEmployee),
				of("Display data of all employees",CompanyControllerItem::getAllEmployees),
				of("Display Employees by salary", CompanyControllerItem::getEmployeesBySalary),
				of("Display Employees by Month", CompanyControllerItem::getEmployeesByMonth),
				of("Display Employess from Department", CompanyControllerItem::getEmployeesByDepartment),
				exit());
	}
	private static Item getAdminMenu() {
		
		return new Menu("Admin actions",
				of("Add employee", CompanyControllerItem::addEmployee),
				of("Remove Employee", CompanyControllerItem::removeEmployee),
				exit());
	}
	private static Long getId(InputOutput io, boolean exist) {
		long id = io.readLong("Enter an employee's ID", "Wrong ID number", 1, Long.MAX_VALUE);
		Employee empl = company.getEmployee(id);
		return (empl == null && !exist) || (empl != null && exist) ? id : null;
	}
	private static String getDepartment(InputOutput io) {
		return io.readStringOptions("Enter department " + departments, "Wrong department", departments);
		
	}
	private static void addEmployee(InputOutput io) {
		Long id = getId(io, false);
		if (id == null) {
			io.writeLine("Employee already exists");
		} else {
			String name = io.readString("Enter an employee's name");
			LocalDate birthDate = io.readDateISO("Enter birthdate", "Wrong birthdate");
			String department = getDepartment(io);
			int salary = getSalary(io);
			Employee empl = new Employee(id, name , birthDate , department , salary );
			io.writeLine(company.addEmployee(empl) ? "Employee has been added" : "Employee is already exists");
		}
	}
	private static int getSalary(InputOutput io) {
		return io.readInt("Enter a salary size from " + MIN_SALARY + " to " + MAX_SALARY, "Wrong salary size", MIN_SALARY, MAX_SALARY);
	}
	private static void removeEmployee(InputOutput io) {
		Long id = getId(io, true);
		if (id == null) {
			io.writeLine("Employee doesn't exist");
		} else {
			Employee empl = company.removeEmployee(id);
			io.writeLine(empl != null ? "Employee has been removed" : "Employee not found");
		}
	}
	private static void getAllEmployees(InputOutput io) {
		displayResult(company.getAllEmployees(), io);
	}
	private static void getEmployee(InputOutput io) {
		Long id = getId(io, true);
		io.writeLine(id != null ? company.getEmployee(id) : "Employee doesn't exist");
		
	}
	private static void getEmployeesBySalary(InputOutput io) {
		int salaryMin = io.readInt("Enter salary from " + MIN_SALARY, "Wrong salary", MIN_SALARY, MAX_SALARY);
		int salaryMax = io.readInt("Enter  salary to " + MAX_SALARY, "Wrong salary", salaryMin, MAX_SALARY);
		if (company.getEmployeesBySalary(salaryMin, salaryMax).isEmpty())  {
			io.writeLine("Employee(s) with this salary size not found");
		}
			displayResult(company.getEmployeesBySalary(salaryMin, salaryMax), io);
		}
	
	
	private static void displayResult(List<Employee> employees, InputOutput io) {
		employees.forEach(io::writeLine);
	}
	
	private static void getEmployeesByMonth(InputOutput io) {
		int month = io.readInt("Enter month number", "Wrong month number", 1, 12);
		
		displayResult(company.getEmployeesByMonthBirth(month), io);
	}
	private static void getEmployeesByDepartment(InputOutput io) {
		String department = getDepartment(io);
		
		displayResult(company.getEmployeesByDepartment(department),io);
	}
}

	
	
	
	
	
	
	
	
	
//	static public Item[] getCompanyItems(Company company,
//			String[] departments) {
//		CompanyControllerItem.company = company;
//		CompanyControllerItem.departments = new HashSet<>(Arrays.asList(departments));
//		return getItems();
//	}
//
//	private static Item[] getItems() {
//		return new Item[] {
//			getAdminMenu(), getUserMenu() 	
//		};
//	}
//	
//	private static Item getUserMenu() {
//		return new Menu("User actions menu",
//				of("Getting all employees", CompanyControllerItem:: getAllEmployees),
//				of("Getting employees by salary size", CompanyControllerItem:: getEmployeesBySalary),
//				of("Getting employees by month of birth", CompanyControllerItem:: getEmployeesByMonth),
//				of("Enter an employee's month of birth from 1 to 12", CompanyControllerItem:: getEmployeesByDepartment),
//				of("Getting employee by Id", CompanyControllerItem:: getEmployee), 
//				exit());
//	}
//	
//	private static Item getAdminMenu() {
//		return new Menu("Admin actions menu", 
//				of("Addition employee", CompanyControllerItem:: addEmployee),
//				of("Removing employee", CompanyControllerItem:: removeEmployee),
//				exit());
//	}
//	
//	private static long getId (InputOutput io) {
//		return io.readLong("Enter Id number", "Wrong Id number", 1, Long.MAX_VALUE);
//		    
//	}
//	
//	private static String getDepartment(InputOutput io) {
//		String department = io.readStringOptions("Enter a department name: " + departments + "", "Wrong department name", departments);
//		return department;
//	}
//	
//	private static void addEmployee(InputOutput io) {
//		long id = getId(io);
//		String name = checkEmplName (io);
//		LocalDate bd = io.readDate("Enter an employee's Birth Date using format ''yyyy-MM-dd''", "Wrong date", "yyyy-MM-dd", 
//				LocalDate.of(1900, 1, 1), LocalDate.MAX); 
//		String dep = getDepartment(io);
//		int salarySize = getSalary(io);
//		Employee employee = new Employee (id, name, bd, dep, salarySize);
//		
//		boolean res = company.addEmployee(employee);
//		io.writeLine(res ? "Employee with Id: " + id + " has been added" :
//			"Employee with Id: " + id + " is already exists");
//		}
//
//	
//	private static int getSalary(InputOutput io) {
//		return io.readInt("Enter an employee's salary size", "Wrong employee's salary size",
//				MIN_SALARY, MAX_SALARY);
//	}
//	
//	private static void removeEmployee (InputOutput io) {
//		Long id = getId(io);
//		io.writeLine(company.removeEmployee(id) == null ? "Employee with id " + id + " does not exist in company" :
//			"Employee with id " + id + " has been removed");
//		}
//	
//	
//	private static void getAllEmployees (InputOutput io) {
//		displayResult(company.getAllEmployees(), io);
//	}
//	
//	
//	private static void getEmployee (InputOutput io) {
//		Long id = getId(io);
//		io.writeLine(id != null ? company.getEmployee(id) : "Employee with id " + id + " not found");
//		
//	}
//	
//	
//	private static void getEmployeesBySalary(InputOutput io) {
//		int salaryMin = io.readInt("Enter a salary size from", "Wrong entered salary size", MIN_SALARY, MAX_SALARY);
//		int salaryMax = io.readInt("Enter a salary size to", "Wrong entered salary size", salaryMin, MAX_SALARY);
//		displayResult(company.getEmployeesBySalary(salaryMin, salaryMax), io);
//	}
//
//	private static void displayResult(List<Employee> employees, InputOutput io) {
//		employees.forEach(io:: writeLine);
//	}
//	
//	private static void getEmployeesByMonth (InputOutput io) {
//		int month = io.readInt("Enter an employee's month of birth from 1 to 12", "Wrong data", 1, 12);
//		displayResult(company.getEmployeesByMonthBirth(month), io);
//	}
//	
//	private static void getEmployeesByDepartment (InputOutput io) {
//		String department = getDepartment(io);
//		displayResult(company.getEmployeesByDepartment(department), io);
//		
//	}
//	
//	private static String checkEmplName (InputOutput io) {
//		String name = io.readString("Enter an employee's name");
//		if (name.matches("[a-zA-Z]+")) {
//			return name;
//		} else {
//			io.readString("Name shoud contains only letters");
//		} 
//		return name;
//		}
//	
//}
//	
//	
//	
//	
	
	
	












