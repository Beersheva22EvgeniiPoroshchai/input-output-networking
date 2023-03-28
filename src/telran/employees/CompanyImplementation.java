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
	
private	HashMap<Integer, Set <Employee>> emplMonthOfBirth = new HashMap<>();
private HashMap<String, Set <Employee>> emplDepartm = new HashMap<>();
private TreeMap <Integer, Set <Employee>> emplSalary = new TreeMap<>();
private HashMap<Long, Employee> employees = new HashMap<>();
	
	
	@Override
	public Iterator<Employee> iterator() {
	return getAllEmployees().iterator();
	}


	@Override
	public boolean addEmployer(Employee employee) {
		boolean res = false;
		if (employees.putIfAbsent(employee.id, employee) == null) {
			res = true;
			addIndexMap(emplMonthOfBirth, employee.getBirthDate().getMonthValue(), employee);
			addIndexMap(emplDepartm, employee.getDepartment(), employee);
			addIndexMap(emplSalary, employee.getSalary(), employee);
		}
		return res;
		}
	
	private <T> void addIndexMap( Map<T, Set<Employee>> map, T key, Employee employee) {
		map.computeIfAbsent(key, k -> new HashSet<>()).add(employee);
		
	}

	@Override
	public Employee removeEmployee(long id) {
		Employee remById = employees.remove(id);
		if (remById != null) {
			removeIndexMap(emplMonthOfBirth, remById.getBirthDate().getMonthValue(), remById);
			removeIndexMap(emplDepartm, remById.getDepartment(), remById);
			removeIndexMap(emplSalary, remById.getSalary(), remById);
			}
		return remById;
		}
	
	private <T> void removeIndexMap (Map<T, Set <Employee>> map, T key, Employee employee) {
		Set<Employee> set = map.get(key);
		set.remove(employee);
		if (set.isEmpty()) {
			map.remove(key);
		}
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return new ArrayList<>(employees.values());
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return new ArrayList<>(emplMonthOfBirth.getOrDefault(month, Collections.emptySet()));
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return emplSalary.subMap(salaryFrom, true,  salaryTo, true).values().stream().flatMap(Set:: stream).toList();
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return new ArrayList<>(emplDepartm.getOrDefault(department, Collections.emptySet()));
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}
	
	@Override
	public void save(String pathName) {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathName))) {
			output.writeObject(getAllEmployees());
			} catch (Exception e) {
			throw new RuntimeException(e.toString());
			}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void restore(String pathName)  {
		try (ObjectInputStream input = new ObjectInputStream (new FileInputStream(pathName))) {
		List<Employee> allEmployees = (List <Employee>) input.readObject();
		allEmployees.forEach(this:: addEmployer);
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
		throw new RuntimeException(e.toString());
		}
	}

}
