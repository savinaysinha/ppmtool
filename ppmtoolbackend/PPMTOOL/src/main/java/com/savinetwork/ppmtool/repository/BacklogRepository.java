package com.savinetwork.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savinetwork.ppmtool.model.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
	Backlog findByProjectIdentifier(String projectIdentifier);

}
