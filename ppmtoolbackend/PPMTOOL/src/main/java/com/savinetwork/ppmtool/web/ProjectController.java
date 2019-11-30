package com.savinetwork.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> errors = mapError.mapError(result);
		if(errors!=null) return errors;
		
		projectService.createProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
}
