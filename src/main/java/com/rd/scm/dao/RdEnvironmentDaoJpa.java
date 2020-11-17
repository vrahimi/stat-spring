package com.rd.scm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.RdEnvironment;

public interface RdEnvironmentDaoJpa extends CrudRepository<RdEnvironment, Integer> {

}
