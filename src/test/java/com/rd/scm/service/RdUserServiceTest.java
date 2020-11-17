package com.rd.scm.service;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rd.scm.dao.RdUserDaoJpa;
import com.rd.scm.model.RdUser;

@SpringBootTest
public class RdUserServiceTest {

	@MockBean
	private RdUserDaoJpa mockedRdUserDaoJpa;

	@Autowired
	private RdUserService rdUserService;

	@Test
	@DisplayName("Test findAll")
	public void testLookup() {
		// Setup our mock repository
		RdUser rdUser1 = new RdUser("JODOE", "Jonh", "Doe", "A");
		RdUser rdUser2 = new RdUser("JADOE", "Jane", "Doe", "A");
		doReturn(Arrays.asList(rdUser1, rdUser2)).when(mockedRdUserDaoJpa).findAll();

		// Execute the service call
		Iterable<RdUser> rdUsers = rdUserService.lookupRdUser();

		// Assert the response
		Assertions.assertEquals(2, StreamSupport.stream(rdUsers.spliterator(), false).count(),
				"lookup should return 2 rdUsers");

		// Iterate the Iterable object
		for (RdUser u : rdUsers) {
			Assertions.assertEquals(u.getStatusCd(), "A", "status should be A");
		}
	}

}