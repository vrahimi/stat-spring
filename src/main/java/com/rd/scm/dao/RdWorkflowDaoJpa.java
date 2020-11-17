package com.rd.scm.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.RdWorkflow;

public interface RdWorkflowDaoJpa extends CrudRepository<RdWorkflow, Integer> {

	List<RdWorkflow> findByStatusCd(String status);

}
