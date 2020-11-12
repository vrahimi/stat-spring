package com.rd.scm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityCdTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        ActivityCdPK pk = new ActivityCdPK("ORA","Big");
        ActivityCd act = new ActivityCd();
        act.setPk(pk);
        act.setAllowDescrEdit("Y");
        act.setBillRate(2.0);
        act.setCommentReqd("Y");
        act.setDescr("big");
        act.setStatusCd("A");
        act.setUpdateUserid("VV");


        assertEquals(act.getActivityCd(), "Big");
        assertEquals(act.getAllowDescrEdit(), "Y");
        assertEquals(act.getBillRate(), 2.0);
        assertEquals(act.getCommentReqd(), "Y");
        assertEquals(act.getDescr(), "big");
        assertEquals(act.getSdCd(), "ORA");
        assertEquals(act.getStatusCd(), "A");
        assertEquals(act.getUpdateUserid(), "VV");
    }

    //@Test
    public void equalsHashcodeVerify() {
        ActivityCdPK pk = new ActivityCdPK("ORA","Big");
        ActivityCd act = new ActivityCd();
        act.setPk(pk);

        ActivityCd act1 = new ActivityCd();
        act1.setPk(pk);

        assertEquals(act1,act);
        assertEquals(act1.hashCode(), act.hashCode());
    }    
}