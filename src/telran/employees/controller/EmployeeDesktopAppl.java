package telran.employees.controller;
import java.util.ArrayList;
import java.util.Arrays;

import telran.employees.*;
import telran.view.*;

public class EmployeeDesktopAppl {

	private static final String FILE_PATH = "employees_data";

	public static void main(String[] args) {
	InputOutput io = new StandardInputOutput();
	Company company = new CompanyImplementation();
	company.restore(FILE_PATH);
	Item[] companyItems = CompanyControllerItem.getCompanyItems(company, new String[] {"lawyers", "security", "developers"});
	ArrayList<Item> items = new ArrayList<>(Arrays.asList(companyItems));
	items.add(Item.of("Exit & save", ios -> company.save(FILE_PATH), true));
	Menu menu = new Menu ("Company Application", items);
	menu.perform(io);
	}
}
