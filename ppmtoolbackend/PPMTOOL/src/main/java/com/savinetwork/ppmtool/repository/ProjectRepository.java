package com.savinetwork.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;

import com.savinetwork.ppmtool.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
}
