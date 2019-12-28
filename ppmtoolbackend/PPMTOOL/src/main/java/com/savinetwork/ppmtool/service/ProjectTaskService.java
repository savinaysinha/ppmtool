package com.savinetwork.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.savinetwork.ppmtool.model.Backlog;
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
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		projectTask.setBacklog(backlog);
		System.out.println(backlog.getProjectIdentifier());
		Integer backlogSequence= backlog.getPTSequence();
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
		projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
		if(projectTask.getStatus()==""||projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
		}
		if (/* projectTask.getPriority()==0|| */projectTask.getPriority()==null) {
			projectTask.setPriority(3);
		}
		
		return projectTaskRepository.save(projectTask);
	}
	public Iterable<ProjectTask> findProjectTaskByProjectIdentifier(String projectIdentifier){
		System.out.println(projectTaskRepository.findByProjectIdentifier(projectIdentifier));
		return projectTaskRepository.findByProjectIdentifier(projectIdentifier);
	}
}
