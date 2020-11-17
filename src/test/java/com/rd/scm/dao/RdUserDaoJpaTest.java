package com.rd.scm.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rd.scm.model.RdUser;

@SpringBootTest
public class RdUserDaoJpaTest {

	@Autowired
	private RdUserDaoJpa rdUserDaoJpa;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Integer> idMapper = (rs, num) -> rs.getInt("user_id");

	@Test
	public void runJpaRepositoryMethods() {

		RdUser user1 = rdUserDaoJpa.save(new RdUser("user1", "name1", "lname1", "A"));
		rdUserDaoJpa.save(new RdUser("user2", "name2", "lname2", "A"));

		Iterable<RdUser> users = rdUserDaoJpa.findAll();
		Assertions.assertEquals(2, StreamSupport.stream(users.spliterator(), false).count(),
				"lookup should return 2 rdUsers");
		assertNotNull(user1.getUserId());

		// Query the database and validates against the jpa value
		jdbcTemplate.query("select user_id from rd_user", idMapper).forEach(id -> {
			Optional<RdUser> u = rdUserDaoJpa.findById(id);
			assertTrue(u.isPresent());
			assertEquals(id, u.get().getUserId());
		});

		//
		List<String> lnames = StreamSupport.stream(users.spliterator(), false).map(RdUser::getLastName)
				.collect(Collectors.toList());
		assertThat(lnames, containsInAnyOrder("lname1", "lname2"));

		rdUserDaoJpa.delete(users.iterator().next());
		users = rdUserDaoJpa.findAll();
		Assertions.assertEquals(1, StreamSupport.stream(users.spliterator(), false).count(),
				"lookup should return 2 rdUsers");

		rdUserDaoJpa.deleteAll();
		users = rdUserDaoJpa.findAll();
		Assertions.assertEquals(0, StreamSupport.stream(users.spliterator(), false).count(),
				"lookup should return 2 rdUsers");
	}

	@Test
	public void findByIdThatDoesNotExist() {
		Optional<RdUser> user = rdUserDaoJpa.findById(99999);
		assertFalse(user.isPresent());
	}

}
