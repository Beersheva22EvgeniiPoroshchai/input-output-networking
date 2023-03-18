package telran.employees;


import java.io.Serializable;
import java.util.List;

public interface Company extends Iterable<Employee>, Serializable {

	boolean addEmployer(Employee employee); //return true if added (no 2 employees with the same ID)
	Employee removeEmployee(long id); //return reference to removed Employee or null
	List<Employee> getAllEmployees();
	List<Employee> getEmployeesByMonthBirth(int month); //employees was born on the call month
	List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo); //employees with salary in a given range
	List<Employee> getEmployeesByDepartment(String department);
	Employee getEmployee(long id); //return employee by id or null if no exist
	void save(String pathName); //save all employee objects (company)
	void restore (String pathName); //restore all employee objects
	
	
	
}
