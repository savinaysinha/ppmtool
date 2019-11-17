package com.savinetwork.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savinetwork.ppmtool.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	
}
