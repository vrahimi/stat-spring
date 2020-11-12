package com.rd.scm.service;

import com.rd.scm.dao.ActivityCdDaoJpa;
import com.rd.scm.model.ActivityCd;
import com.rd.scm.model.ActivityCdPK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.google.common.collect.Lists;

@Service
public class ActivityCdService {

    private ActivityCdDaoJpa acitivityCdDaoJpa;

    @Autowired
    public ActivityCdService(ActivityCdDaoJpa activityCdDaoJpa) {
        this.acitivityCdDaoJpa = activityCdDaoJpa;
    }

    public Optional<ActivityCd> findById(ActivityCdPK pk) {
        return acitivityCdDaoJpa.findById(pk);
    }

    public ActivityCd verifyById(ActivityCdPK pk) throws NoSuchElementException {
        return acitivityCdDaoJpa.findById(pk).orElseThrow(() -> 
            new NoSuchElementException("ActivityCd does not exist " + pk));
    }

    public List<ActivityCd> lookup() {
        return Lists.newArrayList(acitivityCdDaoJpa.findAll());
    }

    public long total() {
        return acitivityCdDaoJpa.count();
    }

    public ActivityCd save(ActivityCd activityCd) {

        return acitivityCdDaoJpa.save(activityCd);
    }

    public void deleteById(ActivityCdPK pk) {
        acitivityCdDaoJpa.deleteById(pk);
    }

    /*
    public ActivityCd findByDescr(String descr) {
        ActivityCd activityCd = acitivityCdDaoJpa.findByDescr(descr);
        if( activityCd == null ) {
            throw new RuntimeException(("Activity Description does not exist: " + descr));
        }
        return activityCd;
    }*/

    public ActivityCd findByDescr(String descr) {
        return acitivityCdDaoJpa.findByDescr(descr).orElseThrow(() -> new RuntimeException("Activity Code does not exist: " + descr));
    }

    // findByName(name).orElseThrow(() -> new RuntimeError("No way"))
    // findByName(name).orElse(new class("blah"))
    // findByName(name).get()  
    // findByName(name).ifPresent(obj -> System.out.println(obj.getcode()))
    // boolean = findByName(name).isPresent()   
    // boolean = findByName(name).filter(obj -> obj.getcode().equals("Blah")).isPresent()

}