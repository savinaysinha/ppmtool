package com.savinetwork.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savinetwork.ppmtool.model.User;

@Repository
public interface UserRespository extends CrudRepository<User, Long> {

}
