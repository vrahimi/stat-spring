package com.rd.scm.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.RdUser;

//we could also extends JpaRepository<RdUser, Integer>
public interface RdUserDaoJpa extends CrudRepository<RdUser, Integer> {

	public Optional<RdUser> findByUserCode(String userCode);
}
