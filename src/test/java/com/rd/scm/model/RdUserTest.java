package com.rd.scm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RdUserTest {
	@Test
	public void testConstructorAndGetters() throws Exception {
		RdUser act = new RdUser();
		act.setUserCode("JODOE");
		act.setFirstName("John");
		act.setLastName("Doe");
		act.setStatusCd("A");

		assertEquals(act.getUserCode(), "JODOE");
		assertEquals(act.getFirstName(), "John");
		assertEquals(act.getLastName(), "Doe");
		assertEquals(act.getStatusCd(), "A");
	}

	// @Test
	public void equalsHashcodeVerify() {
		RdUser act = new RdUser("JODOE", "John", "Doe", "A");

		RdUser act1 = new RdUser("JADOE", "John", "Doe", "A");

		assertEquals(act1, act);
		assertEquals(act1.hashCode(), act.hashCode());
	}
}