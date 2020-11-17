package com.rd.scm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.RdObject;

public interface RdObjectDaoJpa extends CrudRepository<RdObject, Integer> {

}
