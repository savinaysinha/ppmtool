package com.savinetwork.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savinetwork.ppmtool.model.Project;
import com.savinetwork.ppmtool.service.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
		
	@PostMapping("")
	public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Project project, BindingResult result){
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			return new ResponseEntity<String>("Project Object error",HttpStatus.BAD_REQUEST);
		}
		projectService.createProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
}
