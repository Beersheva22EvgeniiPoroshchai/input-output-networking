package telran.employees;

import java.io.Serializable;
import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class ProtocolCompanyImplem implements Protocol {
	
	Company company;
	
	@Override
	public Response getResponse(Request request) {
		try {
			return switch(request.type) {
			case "addEmployer" -> getResponseData (addEmployer(request.data));
			case "removeEmployee" -> getResponseData (removeEmployee(request.data));
			case "getAllEmployees" -> getResponseData (getAllEmployees(request.data));
			case "getEmployeesByMonthBirth" -> getResponseData (getEmployeesByMonthBirth(request.data));
			case "getEmployeesBySalary" -> getResponseData (getEmployeesBySalary(request.data));
			case "getEmployeesByDepartment" -> getResponseData (getEmployeesByDepartment(request.data));
			case "getEmployee" -> getResponseData (getEmployee(request.data));
			case "save" -> getResponseData (save(request.data));
			case "restore" -> getResponseData (restore(request.data));
			default -> new Response(ResponseCode.WRONG_REQUEST, request.type + "Requet type not found");
			};
			
		} catch (Throwable e) {
			return new Response (ResponseCode.WRONG_DATA, e.toString());
		}
	}
		private Response getResponseData(Serializable data) {
			return new Response (ResponseCode.OK, data);
	}
		Serializable addEmployer(Serializable data) {
			Employee empl = (Employee) data;
			return company.addEmployer(empl);
		}

		Serializable removeEmployee(Serializable data) {
			long id = (long) data;
			return company.removeEmployee(id);
		}
		
		Serializable getAllEmployees (Serializable data) {
			return (Serializable) company.getAllEmployees();
		}
		
		Serializable getEmployeesByMonthBirth (Serializable data) {
			int month = (int) data;
			return (Serializable)company.getEmployeesByMonthBirth(month);
		}
		
		Serializable getEmployeesBySalary (Serializable data) {
			int salaries[] = (int[]) data;
			return (Serializable) company.getEmployeesBySalary(salaries[0], salaries[1]);
		}
		
		Serializable getEmployeesByDepartment (Serializable data) {
			String department = (String) data;
			return (Serializable) company.getEmployeesByDepartment(department);
		}
		
		Serializable getEmployee(Serializable data) {
			long id = (long) data;
			return company.getEmployee(id);
		}
		
		Serializable save (Serializable data) {
			String filePath = (String) data;
			company.save(filePath);
			return "";
		}
		
		Serializable restore (Serializable data) {
			String filePath = (String) data;
			company.restore(filePath);
			return "";
		}
		public ProtocolCompanyImplem(Company company) {
			this.company = company;
		}
}
		
		
		
		
		
		