package com.rd.scm.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries
({
@NamedQuery(name = "ServiceDomain.findByStatusCd",
            query = "select object(m) from ServiceDomain as m where m.statusCd = ?1")
})

@Table(name = "SERVICE_DOMAIN")
public class ServiceDomain {

    private String sdCd;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String country;
    private String csrApplEnv;
    private String csrChkCustCount;
    private Long csrCloseLockDays = 0L;
    private Long csrLastNumber = 0L;
    private String csrTabApplmgmt;
    private String csrTabIssue;
    private String csrTabOracle;
    private String csrTabPs;
    private String csrTabTask;
    private String custPriorityReq;
    private String descr;
    private String gapplLabel;
    private String phoneNumber;
    private String state;
    private String statusCd;
    private String unit;
    private String updateUserid;
    private String useDailyTime;
    private String useFileSwat;
    private String usePsLocking;
    private String usePsSwat;
    private String zipCode;

    public ServiceDomain() {
    }

    public ServiceDomain(String sdCd1, String descr1, Long csrLastNumber1,
                         String unit1, String usePsSwat1, String usePsLocking1,
                         String useFileSwat1,
                         String updateUserid1, String statusCd1,
                         String csrApplEnv1, String custPriorityReq1,
                         String csrTabTask1) {
        this.sdCd = sdCd1;
        this.descr = descr1;
        this.csrLastNumber = csrLastNumber1;
        this.unit = unit1;
        this.usePsSwat = usePsSwat1;
        this.usePsLocking = usePsLocking1;
        this.useFileSwat = useFileSwat1;
        this.updateUserid = updateUserid1;
        this.statusCd = statusCd1;
        this.csrApplEnv = csrApplEnv1;
        this.custPriorityReq = custPriorityReq1;
        this.csrTabTask = csrTabTask1;
    }

    @Id
    @Column(name="SD_CD", nullable = false)
    public String getSdCd() {
        return sdCd;
    }

    public void setSdCd(String sdCd) {
        this.sdCd = sdCd;
    }

    @Column(name="ADDRESS1", nullable = false)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(name="ADDRESS2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name="ADDRESS3")
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Column(name="CITY", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name="CSR_APPL_ENV", nullable = false)
    public String getCsrApplEnv() {
        return csrApplEnv;
    }

    public void setCsrApplEnv(String csrApplEnv) {
        this.csrApplEnv = csrApplEnv;
    }

    @Column(name="CSR_CHK_CUST_COUNT", nullable = false)
    public String getCsrChkCustCount() {
        return csrChkCustCount;
    }

    public void setCsrChkCustCount(String csrChkCustCount) {
        this.csrChkCustCount = csrChkCustCount;
    }

    @Column(name="CSR_CLOSE_LOCK_DAYS")
    public Long getCsrCloseLockDays() {
        return csrCloseLockDays;
    }

    public void setCsrCloseLockDays(Long csrCloseLockDays) {
        this.csrCloseLockDays = csrCloseLockDays;
    }

    @Column(name="CSR_LAST_NUMBER")
    public Long getCsrLastNumber() {
        return csrLastNumber;
    }

    public void setCsrLastNumber(Long csrLastNumber) {
        this.csrLastNumber = csrLastNumber;
    }

    @Column(name="CSR_TAB_APPLMGMT", nullable = false)
    public String getCsrTabApplmgmt() {
        return csrTabApplmgmt;
    }

    public void setCsrTabApplmgmt(String csrTabApplmgmt) {
        this.csrTabApplmgmt = csrTabApplmgmt;
    }

    @Column(name="CSR_TAB_ISSUE", nullable = false)
    public String getCsrTabIssue() {
        return csrTabIssue;
    }

    public void setCsrTabIssue(String csrTabIssue) {
        this.csrTabIssue = csrTabIssue;
    }

    @Column(name="CSR_TAB_ORACLE", nullable = false)
    public String getCsrTabOracle() {
        return csrTabOracle;
    }

    public void setCsrTabOracle(String csrTabOracle) {
        this.csrTabOracle = csrTabOracle;
    }

    @Column(name="CSR_TAB_PS", nullable = false)
    public String getCsrTabPs() {
        return csrTabPs;
    }

    public void setCsrTabPs(String csrTabPs) {
        this.csrTabPs = csrTabPs;
    }

    @Column(name="CSR_TAB_TASK", nullable = false)
    public String getCsrTabTask() {
        return csrTabTask;
    }

    public void setCsrTabTask(String csrTabTask) {
        this.csrTabTask = csrTabTask;
    }

    @Column(name="CUST_PRIORITY_REQ")
    public String getCustPriorityReq() {
        return custPriorityReq;
    }

    public void setCustPriorityReq(String custPriorityReq) {
        this.custPriorityReq = custPriorityReq;
    }

    @Column(name="DESCR", nullable = false)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Column(name="GAPPL_LABEL")
    public String getGapplLabel() {
        return gapplLabel;
    }

    public void setGapplLabel(String gapplLabel) {
        this.gapplLabel = gapplLabel;
    }

    @Column(name="PHONE_NUMBER", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="STATE", nullable = false)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="STATUS_CD", nullable = false)
    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    @Column(name="UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name="UPDATE_USERID", nullable = false)
    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    @Column(name="USE_DAILY_TIME")
    public String getUseDailyTime() {
        return useDailyTime;
    }

    public void setUseDailyTime(String useDailyTime) {
        this.useDailyTime = useDailyTime;
    }

    @Column(name="USE_FILE_SWAT")
    public String getUseFileSwat() {
        return useFileSwat;
    }

    public void setUseFileSwat(String useFileSwat) {
        this.useFileSwat = useFileSwat;
    }

    @Column(name="USE_PS_LOCKING", nullable = false)
    public String getUsePsLocking() {
        return usePsLocking;
    }

    public void setUsePsLocking(String usePsLocking) {
        this.usePsLocking = usePsLocking;
    }

    @Column(name="USE_PS_SWAT")
    public String getUsePsSwat() {
        return usePsSwat;
    }

    public void setUsePsSwat(String usePsSwat) {
        this.usePsSwat = usePsSwat;
    }

    @Column(name="ZIP_CODE", nullable = false)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ServiceDomain)) {
            return false;
        }
        ServiceDomain serviceDomain = (ServiceDomain) o;
        return Objects.equals(sdCd, serviceDomain.sdCd) && Objects.equals(address1, serviceDomain.address1) && Objects.equals(address2, serviceDomain.address2) && Objects.equals(address3, serviceDomain.address3) && Objects.equals(city, serviceDomain.city) && Objects.equals(country, serviceDomain.country) && Objects.equals(csrApplEnv, serviceDomain.csrApplEnv) && Objects.equals(csrChkCustCount, serviceDomain.csrChkCustCount) && Objects.equals(csrCloseLockDays, serviceDomain.csrCloseLockDays) && Objects.equals(csrLastNumber, serviceDomain.csrLastNumber) && Objects.equals(csrTabApplmgmt, serviceDomain.csrTabApplmgmt) && Objects.equals(csrTabIssue, serviceDomain.csrTabIssue) && Objects.equals(csrTabOracle, serviceDomain.csrTabOracle) && Objects.equals(csrTabPs, serviceDomain.csrTabPs) && Objects.equals(csrTabTask, serviceDomain.csrTabTask) && Objects.equals(custPriorityReq, serviceDomain.custPriorityReq) && Objects.equals(descr, serviceDomain.descr) && Objects.equals(gapplLabel, serviceDomain.gapplLabel) && Objects.equals(phoneNumber, serviceDomain.phoneNumber) && Objects.equals(state, serviceDomain.state) && Objects.equals(statusCd, serviceDomain.statusCd) && Objects.equals(unit, serviceDomain.unit) && Objects.equals(updateUserid, serviceDomain.updateUserid) && Objects.equals(useDailyTime, serviceDomain.useDailyTime) && Objects.equals(useFileSwat, serviceDomain.useFileSwat) && Objects.equals(usePsLocking, serviceDomain.usePsLocking) && Objects.equals(usePsSwat, serviceDomain.usePsSwat) && Objects.equals(zipCode, serviceDomain.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sdCd, address1, address2, address3, city, country, csrApplEnv, csrChkCustCount, csrCloseLockDays, csrLastNumber, csrTabApplmgmt, csrTabIssue, csrTabOracle, csrTabPs, csrTabTask, custPriorityReq, descr, gapplLabel, phoneNumber, state, statusCd, unit, updateUserid, useDailyTime, useFileSwat, usePsLocking, usePsSwat, zipCode);
    }

    @Override
    public String toString() {
        return "{" +
            " sdCd='" + getSdCd() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", csrApplEnv='" + getCsrApplEnv() + "'" +
            ", csrChkCustCount='" + getCsrChkCustCount() + "'" +
            ", csrCloseLockDays='" + getCsrCloseLockDays() + "'" +
            ", csrLastNumber='" + getCsrLastNumber() + "'" +
            ", csrTabApplmgmt='" + getCsrTabApplmgmt() + "'" +
            ", csrTabIssue='" + getCsrTabIssue() + "'" +
            ", csrTabOracle='" + getCsrTabOracle() + "'" +
            ", csrTabPs='" + getCsrTabPs() + "'" +
            ", csrTabTask='" + getCsrTabTask() + "'" +
            ", custPriorityReq='" + getCustPriorityReq() + "'" +
            ", descr='" + getDescr() + "'" +
            ", gapplLabel='" + getGapplLabel() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", state='" + getState() + "'" +
            ", statusCd='" + getStatusCd() + "'" +
            ", unit='" + getUnit() + "'" +
            ", updateUserid='" + getUpdateUserid() + "'" +
            ", useDailyTime='" + getUseDailyTime() + "'" +
            ", useFileSwat='" + getUseFileSwat() + "'" +
            ", usePsLocking='" + getUsePsLocking() + "'" +
            ", usePsSwat='" + getUsePsSwat() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            "}";
    }

}
