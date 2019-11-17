package com.savinetwork.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Project> saveOrUpdate(@RequestBody Project project){
		projectService.createProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
}
