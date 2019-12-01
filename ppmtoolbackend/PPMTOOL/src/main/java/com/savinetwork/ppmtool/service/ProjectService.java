package com.savinetwork.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savinetwork.ppmtool.exception.ProjectIdentifierException;
import com.savinetwork.ppmtool.model.Project;
import com.savinetwork.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project createProject(Project project) {
		project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		try {
			return projectRepository.save(project);
		}catch (Exception e) {
			throw new ProjectIdentifierException("Project Identifier '"+project.getProjectIdentifier().toUpperCase()+"' is already exist");
		}
		
	}
}
