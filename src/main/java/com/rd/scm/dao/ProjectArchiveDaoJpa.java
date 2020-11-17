package com.rd.scm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.ProjectArchive;

public interface ProjectArchiveDaoJpa extends CrudRepository<ProjectArchive, Integer> {

}
