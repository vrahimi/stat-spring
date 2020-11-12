package com.rd.scm.service.integration;

import com.rd.scm.service.TestConnectService;
import com.rd.scm.util.RespondStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class TestConnectServiceTest {
    
    @Autowired
    TestConnectService testConnectService;

    @Test
    @DisplayName("Test testConnectionEnvironmentSuccessful")
    public void testConnectionEnvironmentSuccessful() {
        
        //DbConfig dbConfig = new DbConfig("d12g", 1521, "d12g", "alvrndorcl02.prod.quest.corp", "d12g", 
        //                  "apps", "apps", "A", "N", "", "ORA");
        JdbcTemplate t = new JdbcTemplate();
        //doReturn(t).when(mockedDSConfig).getDynamicDataSource("d12g");

        RespondStatus b = testConnectService.TestConnectEnvironment("d12g");
        Assertions.assertEquals(RespondStatus.STATUS_OK, b.getStatusCode(), "testconnect should return status ok");
    }

    @Test
    @DisplayName("Test testConnectionEnvironmentSuccessfulIntegration")
    public void testConnectionEnvironmentSuccessfulIntegration() {
        
        RespondStatus b = testConnectService.TestConnectEnvironment("d12g");
        Assertions.assertEquals(RespondStatus.STATUS_OK, b.getStatusCode(), "testconnect should return status ok");
    }

    @Test
    @DisplayName("Test testConnectionEnvironmentUnSuccessfulIntegration")
    public void testConnectionEnvironmentUnSuccessfulIntegration() {
        
        RespondStatus b = testConnectService.TestConnectEnvironment("d12g1");
        Assertions.assertEquals(RespondStatus.STATUS_ERROR, b.getStatusCode(), "testconnect should return status error");
    }
}