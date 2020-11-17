package com.rd.scm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.Project;

public interface ProjectDaoJpa extends CrudRepository<Project, Integer> {

}
