package com.rd.scm.service;

import com.rd.scm.datasource.DSConfig;
import com.rd.scm.util.RespondStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestConnectService {
    
    private final DSConfig dsConfig;

    @Autowired
    public TestConnectService(DSConfig dsConfig) {
        this.dsConfig = dsConfig;
    }

    public RespondStatus TestConnectEnvironment(String dbCd) {
        RespondStatus res = new RespondStatus();
        try {
            dsConfig.getDynamicDataSource(dbCd);
        } catch(Exception e) {
            res.setStatusCode(RespondStatus.STATUS_ERROR);
            res.setErrorText(e.getMessage());
        }

        return res;
    }
}