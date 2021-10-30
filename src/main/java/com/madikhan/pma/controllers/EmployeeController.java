package com.madikhan.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.madikhan.pma.dao.EmployeeRepository;
import com.madikhan.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping("")
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll(); 
		model.addAttribute("employees" , employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
	}	
	
	 @PostMapping("/save")
	 public String createEmployee(Model model, @Valid Employee employee) {
		 // save to the database using an employee crud repository
		 
		 empRepo.save(employee);
		 
		 return "redirect:/employees/new";
	 }
	 
	 @GetMapping("/update")
	 public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		 
		 Employee theEmp = empRepo.findByEmployeeId(theId);
		 
		 model.addAttribute("employee", theEmp);
		 
		 return "employees/new-employee";
	 }
	 
	 @GetMapping("/delete")
	 public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		 Employee theEmp = empRepo.findByEmployeeId(theId);
		 empRepo.delete(theEmp);
		 return "redirect:/employees";
	 }
}









