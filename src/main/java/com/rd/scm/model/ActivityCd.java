package com.rd.scm.model;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries
({
@NamedQuery(name = "ActivityCd.findAllActiveBySDCd",
    query = "select object(m) from ActivityCd as m where m.statusCd = 'A' AND m.pk.sdCd = ?1 order by m.descr"),
@NamedQuery(name = "ActivityCd.findAllBySDCd",
    query = "select object(m) from ActivityCd as m where m.pk.sdCd = ?1")
})
@Table(name = "ACTIVITY_CD")
public class ActivityCd {

    private ActivityCdPK pk;
    private String    descr;
    private String    commentReqd;
    private Double    billRate = 0.0;
    private String    allowDescrEdit;
    private String    statusCd;
    private String    updateUserid;

    public ActivityCd() {
    }

    public ActivityCd(ActivityCdPK pk, String descr, String commentReqd, Double billRate, 
                    String allowDescrEdit, String statusCd, String updateUserid) {
        this.pk = pk;
        this.descr = descr;
        this.commentReqd = commentReqd;
        this.billRate = billRate;
        this.allowDescrEdit = allowDescrEdit;
        this.statusCd = statusCd;
        this.updateUserid = updateUserid;
    }
    

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="activityCd", column=@Column(name="ACTIVITY_CD", nullable = false)),
        @AttributeOverride(name="sdCd",       column=@Column(name="SD_CD", nullable = false))
    })
    public ActivityCdPK getPk() { return pk; }
    public void setPk(ActivityCdPK pk) { this.pk = pk; }

    @Transient
    public String getActivityCd() {
        return getPk().getActivityCd();
    }

    @Transient
    public String getSdCd() {
        return getPk().getSdCd();
    }

    @Column(name="DESCR", nullable = false)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Column(name="COMMENT_REQD", nullable = false)
    public String getCommentReqd() {
        return commentReqd;
    }

    public void setCommentReqd(String commentReqd) {
        this.commentReqd = commentReqd;
    }

    @Column(name="BILL_RATE")
    public Double getBillRate() {
        return billRate;
    }

    public void setBillRate(Double billRate) {
        this.billRate = billRate;
    }

    @Column(name="ALLOW_DESCR_EDIT")
    public String getAllowDescrEdit() {
        return allowDescrEdit;
    }

    public void setAllowDescrEdit(String allowDescrEdit) {
        this.allowDescrEdit = allowDescrEdit;
    }

    @Column(name="STATUS_CD")
    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }


    @Column(name="UPDATE_USERID")
    public String getUpdateUserid() {
        return updateUserid;
    }
    
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ActivityCd)) {
            return false;
        }
        ActivityCd activityCd = (ActivityCd) o;
        return Objects.equals(pk, activityCd.pk) && Objects.equals(descr, activityCd.descr) && Objects.equals(commentReqd, activityCd.commentReqd) && Objects.equals(billRate, activityCd.billRate) && Objects.equals(allowDescrEdit, activityCd.allowDescrEdit) && Objects.equals(statusCd, activityCd.statusCd) && Objects.equals(updateUserid, activityCd.updateUserid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, descr, commentReqd, billRate, allowDescrEdit, statusCd, updateUserid);
    }

    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", descr='" + getDescr() + "'" +
            ", commentReqd='" + getCommentReqd() + "'" +
            ", billRate='" + getBillRate() + "'" +
            ", allowDescrEdit='" + getAllowDescrEdit() + "'" +
            ", statusCd='" + getStatusCd() + "'" +
            ", updateUserid='" + getUpdateUserid() + "'" +
            "}";
    }

    
}
