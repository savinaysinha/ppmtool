package com.savinetwork.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savinetwork.ppmtool.model.Project;
import com.savinetwork.ppmtool.model.ProjectTask;
import com.savinetwork.ppmtool.service.MapErrorValidationService;
import com.savinetwork.ppmtool.service.ProjectTaskService;

@RestController
@RequestMapping("api/backlog")
@CrossOrigin
public class BacklogController {
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapErrorValidationService mapError;
	
	@PostMapping("{projectIdentifier}")
	public ResponseEntity<?> saveProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String projectIdentifier){
		
		ResponseEntity<?> errors = mapError.mapError(result);
		if (errors != null)
			return errors;
		
		return new ResponseEntity<>(projectTaskService.addProjectTask(projectIdentifier, projectTask), HttpStatus.CREATED);
		
	}
	
	@GetMapping("{projectIdentifier}")
	public Iterable<ProjectTask> getAllProjectTaskByProjectId(@PathVariable String projectIdentifier){
		
		return projectTaskService.findProjectTaskByProjectIdentifier(projectIdentifier);
	}

}