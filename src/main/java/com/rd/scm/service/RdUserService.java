package com.rd.scm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rd.scm.dao.RdUserDaoJpa;
import com.rd.scm.model.RdUser;

@Service
public class RdUserService {

	private final RdUserDaoJpa rdUserDaoJpa;

	@Autowired
	public RdUserService(RdUserDaoJpa rdUserDaoJpa) {
		this.rdUserDaoJpa = rdUserDaoJpa;
	}

	public RdUser createRdUser(String userCode, String firstName, String lastName, String statusCd) {
		return rdUserDaoJpa.findByUserCode(userCode)
				.orElse(rdUserDaoJpa.save(new RdUser(userCode, firstName, lastName, statusCd)));
	}

	public Iterable<RdUser> lookupRdUser() {
		return rdUserDaoJpa.findAll();
	}
}
