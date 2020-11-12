package com.rd.scm.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ActivityCdPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private String activityCd;
    private String sdCd;

    public ActivityCdPK() {
    }

    public ActivityCdPK(String sdCd, String activityCd) {
        this.activityCd = activityCd;
        this.sdCd = sdCd;
    }

    public void setActivityCd(String activityCd) {
        this.activityCd = activityCd;
    }

    public String getActivityCd() {
        return activityCd;
    }

    public void setSdCd(String sdCd) {
        this.sdCd = sdCd;
    }

    public String getSdCd() {
        return sdCd;
    }

}
