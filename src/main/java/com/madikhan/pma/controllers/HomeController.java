package com.madikhan.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madikhan.pma.dao.EmployeeRepository;
import com.madikhan.pma.dao.ProjectRepository;
import com.madikhan.pma.dto.ChartData;
import com.madikhan.pma.dto.EmployeeProject;
import com.madikhan.pma.entities.Project;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		
		Map<String, Object> map = new HashMap<>();
		
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList" , projects);
		
		List<ChartData> projectData	=  proRepo.getProjectStatus();
		
		
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [ ["NOTSTARTED" , 1] , ["INPROGRESS", 2] ]
		
		model.addAttribute("projectStatusCount", jsonString);
		
		List<EmployeeProject> employeesProjectsCount = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCount", employeesProjectsCount);
		return "main/home";
	}	
	
	
}
