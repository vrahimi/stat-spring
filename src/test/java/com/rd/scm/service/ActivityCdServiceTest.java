package com.rd.scm.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rd.scm.dao.ActivityCdDaoJpa;
import com.rd.scm.model.ActivityCd;
import com.rd.scm.model.ActivityCdPK;

// Note that ActivityCdServiceTest also implicitly includes the @ExtendWith(SpringExtension.class) annotation, 
// through the @SpringBootTest annotation, which integrates the test class with JUnit 5.
// The structure of our mock logic is as follows:
//              doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD

@SpringBootTest
public class ActivityCdServiceTest {

	@MockBean
	private ActivityCdDaoJpa mockedActivityCdDaoJpa;

	@Autowired
	private ActivityCdService activityCdService;

	@Test
	@DisplayName("Test findAll")
	public void testLookup() {

		// Setup our mock repository
		ActivityCd activityCd1 = new ActivityCd(new ActivityCdPK("ORA", "big"), null, null, null, null, null, null);
		ActivityCd activityCd2 = new ActivityCd(new ActivityCdPK("ORA", "big1"), null, null, null, null, null, null);
		doReturn(Arrays.asList(activityCd1, activityCd2)).when(mockedActivityCdDaoJpa).findAll();

		// Execute the service call
		List<ActivityCd> activityCds = activityCdService.lookup();

		// Assert the response
		Assertions.assertEquals(2, activityCds.size(), "lookup should return 2 activityCds");

		// Assertions.assertEquals(2, activityCdService.total(), "total should return 2
		// activityCds");
	}

	@Test
	@DisplayName("Test findById Success")
	void testFindById() {
		// Setup our mock repository
		ActivityCdPK pk = new ActivityCdPK("ORA", "big");
		ActivityCd activityCd1 = new ActivityCd(pk, null, null, null, null, null, null);
		doReturn(Optional.of(activityCd1)).when(mockedActivityCdDaoJpa).findById(pk);

		// Execute the service call
		Optional<ActivityCd> returnedActivityCd = activityCdService.findById(pk);

		// Assert the response
		Assertions.assertTrue(returnedActivityCd.isPresent(), "ActivityCd was not found");
		Assertions.assertSame(returnedActivityCd.get(), activityCd1,
				"The ActivityCd returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findById Not Found")
	void testFindByIdNotFound() {
		// Setup our mock repository
		ActivityCdPK pk = new ActivityCdPK("ORA", "big");
		doReturn(Optional.empty()).when(mockedActivityCdDaoJpa).findById(pk);

		// Execute the service call
		Optional<ActivityCd> returnedActivityCd = activityCdService.findById(pk);

		// Assert the response
		Assertions.assertFalse(returnedActivityCd.isPresent(), "ActivityCd should not be found");
	}

	@Test
	@DisplayName("Test save activityCd")
	void testSave() {
		// Setup our mock repository
		ActivityCdPK pk = new ActivityCdPK("ORA", "big");
		ActivityCd activityCd1 = new ActivityCd(pk, null, null, null, null, null, null);
		doReturn(activityCd1).when(mockedActivityCdDaoJpa).save(any());

		// Execute the service call
		ActivityCd returnedActivityCd = activityCdService.save(activityCd1);

		// Assert the response
		Assertions.assertNotNull(returnedActivityCd, "The saved activityCd should not be null");
		Assertions.assertEquals(pk, returnedActivityCd.getPk(), "The version should be incremented");
	}

}