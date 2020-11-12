package com.rd.scm.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import com.rd.scm.model.ActivityCd;
import com.rd.scm.model.ActivityCdPK;
import com.rd.scm.service.ActivityCdService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/* 
   Transactional makes sure that the row is undone after the test
*/

@SpringBootTest
@Transactional
public class ActivityCdServiceIntegrationTest {
   
    @Autowired
    private ActivityCdService activityCdService;
    
    //Happy Path to Create a new Tour Rating
    @Test
    public void createNew() {
        ActivityCdPK pk = new ActivityCdPK("ORA", "biggie");

        //would throw NoSuchElementException if TourRating for TOUR_ID by CUSTOMER_ID already exists
        ActivityCd activityCd1 = new ActivityCd(pk, "descr", "N", 2.0, "Y", "A", "KYANG");

        //Verify New Tour Rating created.
        activityCdService.save(activityCd1);

        ActivityCd newActivityCd = activityCdService.verifyById(pk);
        assertEquals(newActivityCd.getActivityCd(), pk.getActivityCd());
        assertEquals(newActivityCd.getBillRate(), 2.0);
    }

}