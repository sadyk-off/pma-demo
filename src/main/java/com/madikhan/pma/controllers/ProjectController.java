package com.madikhan.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madikhan.pma.dao.EmployeeRepository;
import com.madikhan.pma.dao.ProjectRepository;
import com.madikhan.pma.entities.Employee;
import com.madikhan.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("")
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll(); 
		model.addAttribute("employees" , projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) { 
		// this should handle saving to the database...
		
		proRepo.save(project);
		
		
		// use a redirect to prevent duplicate submissions 
		return "redirect:/projects";
	}
}
