package telran.empoyees.test;

import org.junit.jupiter.api.BeforeEach;

import telran.employees.CompanyImplementation;


public class CompanyImplTest extends CompanyTest {

	@BeforeEach
	@Override
	
	void setUp () throws Exception {
		company = new CompanyImplementation();
		super.setUp();
		

	}
}
