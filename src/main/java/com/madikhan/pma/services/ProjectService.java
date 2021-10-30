package com.madikhan.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.madikhan.pma.dao.ProjectRepository;
import com.madikhan.pma.dto.ChartData;
import com.madikhan.pma.entities.Project;

public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	public Project save(Project project) {
		return projectRepository.save(project);
	}
	
	public List<Project> getAll() {
		return projectRepository.findAll();
	}
	
	public List<ChartData> getProjectStatus() {
		return projectRepository.getProjectStatus();
	}
}
