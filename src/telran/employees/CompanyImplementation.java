package telran.employees;

import java.awt.RenderingHints.Key;
import java.awt.font.NumericShaper.Range;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class CompanyImplementation implements Company {

	private static final long serialVersionUID = 1L;
	
	Map<Integer, List <Employee>> emplMonthOfBirth = new HashMap<>();
	Map<String, List <Employee>> emplDepartm = new HashMap<>();
	Map<Integer, List <Employee>> emplSalary = new HashMap<>();
	Map<Long, Employee> emplId = new HashMap<>();
	
	
	@Override
	public Iterator<Employee> iterator() {
		return emplId.values().iterator();
	}

	@Override
	public boolean addEmployer(Employee employee) {
		Employee putId = emplId.put(employee.id, employee);
		if (putId == null) {
				emplMonthOfBirth.computeIfAbsent(employee.getBirthDate().getMonthValue(),
						c -> new ArrayList<>()).add(employee);
				emplDepartm.computeIfAbsent(employee.getDepartment(),
						c -> new ArrayList<>()).add(employee);
				emplSalary.computeIfAbsent(employee.getSalary(),
						c -> new ArrayList<>()).add(employee);
				}
		return putId == null;
		}
	

	@Override
	public Employee removeEmployee(long id) {
		Employee remById = emplId.get(id);
		if (remById != null) {
			List<Employee> helper = getEmployeesByMonthBirth(remById.getBirthDate().getMonthValue());
			helper.remove(remById);
			helper = getEmployeesByDepartment(remById.department);
			helper.remove(remById);
			helper = getEmployeesBysalary(remById.salary, remById.salary);
			helper.remove(remById);
			emplId.remove(id);
			}
		return remById;
		}

	
	@Override
	public List<Employee> getAllEmployees() {
		return new ArrayList<Employee>(emplId.values());
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return emplMonthOfBirth.get(month);
	}

	@Override
	public List<Employee> getEmployeesBysalary(int salaryFrom, int salaryTo) {
		List <Employee> res = new ArrayList<>();
		Set <Integer> setKeys = emplSalary.keySet().stream().filter(s -> s >= salaryFrom && s <= salaryTo).
				collect(Collectors.toSet());
		setKeys.forEach(k -> res.addAll(emplSalary.get(k)));
		return res;

	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return emplDepartm.get(department);
	}

	@Override
	public Employee getEmployee(long id) {
		return emplId.get(id);
	}
	
	@Override
	public void save(String pathName) {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathName))) {
			output.writeObject(this);
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	

	@Override
	public void restore(String pathName)  {
		Company company = new CompanyImplementation();
		try (ObjectInputStream input = new ObjectInputStream (new FileInputStream(pathName))) {
		company = (Company) input.readObject();
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
	}

}
