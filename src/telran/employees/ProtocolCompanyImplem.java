package telran.employees;

import java.io.Serializable;
import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class ProtocolCompanyImplem implements Protocol {
	Company newCompany = new CompanyImplementation();

	@Override
	public Response getResponse(Request request) throws Exception {
		return switch (request.type) {
		case "addEmployer" -> new Response (ResponseCode.OK, newCompany.addEmployer((Employee) (request.data)));
		case "removeEmployee" -> new Response (ResponseCode.OK, newCompany.removeEmployee((Long) request.data));  
		case "getAllEmployees" -> new Response (ResponseCode.OK, (Serializable) newCompany.getAllEmployees());
		case "getEmployeesByMonthBirth" -> new Response (ResponseCode.OK, (Serializable) newCompany.getEmployeesByMonthBirth((Integer)request.data));
		case "getEmployeesBySalary" -> toEmployeesSalaryList(request.data);
		case "getEmployeesByDepartment" -> new Response (ResponseCode.OK, (Serializable) newCompany.getEmployeesByDepartment((String) request.data)); 
		case "save" -> saveCompany (request.data);
		case "restore" -> restoreCompany (request.data);
		default -> new Response(ResponseCode.WRONG_REQUEST, request.type + " wrong request");
		};
	}

	private Response restoreCompany(Serializable data) {
		newCompany.save((String) (data));
		return new Response (ResponseCode.OK, "");
	}

	private Response saveCompany(Serializable data) {
		newCompany.restore((String) (data));           
		return new Response (ResponseCode.OK, "");
	}

	private Response toEmployeesSalaryList(Serializable data) {
		int[] employeesSalaryList = (int []) data; 
 		return new Response (ResponseCode.OK, (Serializable) newCompany.getEmployeesBySalary(employeesSalaryList[0], employeesSalaryList[1]));
	}

}
