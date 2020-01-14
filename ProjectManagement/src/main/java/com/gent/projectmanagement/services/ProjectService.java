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
	
	public Project findByProjectIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project with ID: "+ projectId + " doesn not exist!");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIdException("Cannot find project with ID: " + projectId 
					+ ". This project does not exist!");
		}
		projectRepository.delete(project);
	}
	
	public Project updateProjectDetails(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIdException("Cannot find project with ID: " + projectId 
					+ ". This project does not exist!");
		}
		return projectRepository.save(project);
	}

}
