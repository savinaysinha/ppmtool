package com.savinetwork.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	@PostMapping("/{projectIdentifier}")
	public ResponseEntity<?> saveProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
			@PathVariable String projectIdentifier) {

		ResponseEntity<?> errors = mapError.mapError(result);
		if (errors != null)
			return errors;

		ProjectTask projectTask2 = projectTaskService.addProjectTask(projectIdentifier, projectTask);

		return new ResponseEntity<>(projectTask2, HttpStatus.CREATED);

	}

	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<Iterable<ProjectTask>> getAllProjectTaskByProjectId(@PathVariable String projectIdentifier) {

		return new ResponseEntity<Iterable<ProjectTask>>(projectTaskService.findProjectTaskByProjectIdentifier(projectIdentifier),HttpStatus.OK) ;
	}

	@GetMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<ProjectTask> getProjectTaskByProjectSequennce(@PathVariable String projectIdentifier,
			@PathVariable String projectSequence) {

		return new ResponseEntity<ProjectTask>(
				projectTaskService.findProjectTaskByProjectSequennce(projectIdentifier, projectSequence),
				HttpStatus.OK);

	}

	@PatchMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask updatedProjectTask, BindingResult result,
			@PathVariable String projectIdentifier, @PathVariable String projectSequence) {

		ResponseEntity<?> errors = mapError.mapError(result);
		if (errors != null)
			return errors;
		
		ProjectTask projectTask = projectTaskService.updateProjectTaskByProjectSequence(updatedProjectTask,projectIdentifier,projectSequence);
		
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);

	}
	
	@DeleteMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String projectIdentifier,
			@PathVariable String projectSequence){
		projectTaskService.deleteProjectTask(projectIdentifier,projectSequence);
		return new ResponseEntity<>("Deleted Succesfully",HttpStatus.OK);
	}

}
