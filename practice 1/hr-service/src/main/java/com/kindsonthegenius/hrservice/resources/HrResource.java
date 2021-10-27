package com.kindsonthegenius.hrservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kindsonthegenius.hrservice.models.Employee;
import com.kindsonthegenius.hrservice.models.EmployeesList;

@RestController
@RequestMapping("/hr")
public class HrResource {
	
	List<Employee> employees=Arrays.asList(
			new Employee("E1","Kindson","Munonye","meditech") ,
			new Employee("E2","Lila","Hardcastle","Surgery") ,
			new Employee("E3","Solace","Chucks","Dentistry") 
			);
	
	@RequestMapping("/employees")
	public EmployeesList getEmployee(){
	EmployeesList employeesList= new EmployeesList();
	employeesList.setEmployees(employees);
	
	return employeesList;
	}
	
	@RequestMapping("/employees/{Id}")
	public Employee getEmployeeById ( @PathVariable("Id")String Id) {
		Employee e=employees.stream()
				.filter(employee -> Id.equals(employee.getId()))
				.findAny()
				.orElse(null);
		return e;
	}
}
