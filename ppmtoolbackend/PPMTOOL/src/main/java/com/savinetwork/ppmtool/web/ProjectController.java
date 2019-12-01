package com.savinetwork.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savinetwork.ppmtool.model.Project;
import com.savinetwork.ppmtool.service.MapErrorValidationService;
import com.savinetwork.ppmtool.service.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapErrorValidationService mapError;

	@PostMapping("")
	public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Project project, BindingResult result) {

		ResponseEntity<?> errors = mapError.mapError(result);
		if (errors != null)
			return errors;
		return new ResponseEntity<Project>(projectService.createProject(project), HttpStatus.CREATED);
	}

	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> findByProjectIdentifier(@PathVariable String projectIdentifier) {
		return new ResponseEntity<>(projectService.getProjectByProjectIdentifier(projectIdentifier), HttpStatus.OK);

	}
	@GetMapping("/all")
	public ResponseEntity<Iterable<Project>> findAllProject(){
		return new ResponseEntity<Iterable<Project>>(projectService.getAllProject(),HttpStatus.OK);
	}
	@DeleteMapping("/{projectIdentifier}")
	public ResponseEntity<?> deleteProjectByProjectIdentifier(@PathVariable String projectIdentifier){
		projectService.deleteProjectByProjectIdentifier(projectIdentifier.toUpperCase());
		return new ResponseEntity<>("Project with '"+projectIdentifier.toUpperCase()+"' deleted successfully",HttpStatus.OK);
	}
}
