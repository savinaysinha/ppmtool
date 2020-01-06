package com.savinetwork.ppmtool.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savinetwork.ppmtool.exception.ProjectNotFoundException;
import com.savinetwork.ppmtool.model.Backlog;
import com.savinetwork.ppmtool.model.Project;
import com.savinetwork.ppmtool.model.ProjectTask;
import com.savinetwork.ppmtool.repository.BacklogRepository;
import com.savinetwork.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	


	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		try {
			System.out.println(projectIdentifier);
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			projectTask.setBacklog(backlog);
			System.out.println(backlog.getProjectIdentifier());
			Integer backlogSequence = backlog.getPTSequence();
			backlogSequence++;
			backlog.setPTSequence(backlogSequence);
			projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);

			if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
				projectTask.setStatus("TO_DO");
			}
			if (/* projectTask.getPriority()==0|| */projectTask.getPriority() == null) {
				projectTask.setPriority(3);
			}
			return projectTaskRepository.save(projectTask);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Project Not Found");
		}

	}

	public Iterable<ProjectTask> findProjectTaskByProjectIdentifier(String projectIdentifier) {
		ArrayList<ProjectTask> projectTasks= (ArrayList<ProjectTask>) projectTaskRepository.findByProjectIdentifier(projectIdentifier);
		if(projectTasks.isEmpty()) {
			throw new  ProjectNotFoundException("Project Not Found");
		}
		
		
		return projectTasks;
	}

	public ProjectTask findProjectTaskByProjectSequennce(String projectIdentifier, String projectSequence) {
		
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		if(backlog==null) {
			throw new ProjectNotFoundException("Project Not Found");
		}
		
		ProjectTask projectTask = projectTaskRepository.findByprojectSequence(projectSequence);
		
		if(projectTask==null) {
			throw new ProjectNotFoundException("Project Not Found");
		}
		
		if(!projectTask.getProjectIdentifier().equals(projectIdentifier)) {
			throw new ProjectNotFoundException("Project Not Found");
		}
		
		return projectTask;
	}
}
