package com.kindsonthegenius.admissionservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kindsonthegenius.admissionservice.models.DiseasesList;
import com.kindsonthegenius.admissionservice.models.EmployeesList;
import com.kindsonthegenius.admissionservice.models.Patient;

@RestController
@RequestMapping("/admissions")
public class AdmissionsResource {

	@Autowired
	private RestTemplate restTemplate;
	
	List<Patient> patients = Arrays.asList(new Patient("P1", "shubham", "Indian"), new Patient("P2", "Ashmi", "Indian"),
			new Patient("P3", "Rio", "Indian"));

	@RequestMapping("/physicians")
	public EmployeesList getPhysicians() {
	EmployeesList employeesList=restTemplate.getForObject("http://localhost:8082/hr/employees", EmployeesList.class);
	return employeesList;
	}
	
	@RequestMapping("/diseases")
	public DiseasesList getDiseases() {
		DiseasesList diseasesList=restTemplate.getForObject("http://localhost:8083/pathology/disease", DiseasesList.class);
		return diseasesList;
	}
	
	@RequestMapping("/patients")
	public List<Patient> getPatients() {

		return patients;
	}

	@RequestMapping("/patients/{Id}")
	public Patient getPatientById(@PathVariable("Id") String Id) {

		Patient p = patients.stream()
				.filter(patient -> Id.equals(patient .getId()))
				.findAny()
				.orElse(null);
		return p;
	}
	
	

}
