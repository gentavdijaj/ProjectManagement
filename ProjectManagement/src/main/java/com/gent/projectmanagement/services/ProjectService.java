package com.gent.projectmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gent.projectmanagement.domain.Project;
import com.gent.projectmanagement.exceptions.ProjectIdException;
import com.gent.projectmanagement.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch(Exception e) {
			throw new ProjectIdException("Project ID: " + project.getProjectIdentifier().toUpperCase() 
			+ " already exists!");
		}
		
	}

}
