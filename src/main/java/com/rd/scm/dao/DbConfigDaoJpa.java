package com.rd.scm.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.DbConfig;


public interface DbConfigDaoJpa extends CrudRepository<DbConfig, String> {

    
    Collection<DbConfig> findByStatusCd(String statusCd);

}
