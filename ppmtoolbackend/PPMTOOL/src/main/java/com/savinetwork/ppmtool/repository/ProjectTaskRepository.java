package com.savinetwork.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savinetwork.ppmtool.model.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
	Iterable<ProjectTask> findByProjectIdentifier(String projectIdentifier);

	ProjectTask findByprojectSequence(String projectSequence);
}
