package com.rd.scm.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "DB_CONFIG")
public class DbConfig {

    private String dbCd;

    private Integer dbListenerPort = 0;
    private String descr;
    private String hostname;
    private String serviceName;
    private String srvLogid;
    private String srvPwd;
    private String statusCd;
    private String useTns;
    private String tnsEntry;
    private String dbType;

    public DbConfig() {
    }

    public DbConfig(String dbCd, Integer dbListenerPort, String descr, String hostname, String serviceName, 
                          String srvLogid, String srvPwd, String statusCd, String useTns, String tnsEntry,
                          String dbType) {
        this.dbCd = dbCd;
        this.dbListenerPort = dbListenerPort;
        this.descr = descr;
        this.hostname = hostname;
        this.serviceName = serviceName;
        this.srvLogid = srvLogid;
        this.srvPwd = srvPwd;
        this.statusCd = statusCd;
        this.useTns = useTns;
        this.tnsEntry = tnsEntry;
        this.dbType = dbType;
    }


    @Id
    @Column(name="DB_CD", nullable = false)
    public String getDbCd() {
        return dbCd;
    }

    public void setDbCd(String dbCd) {
        this.dbCd = dbCd;
    }

    @Column(name="DB_LISTENER_PORT")
    public Integer getDbListenerPort() {
        return dbListenerPort;
    }

    public void setDbListenerPort(Integer dbListenerPort) {
        this.dbListenerPort = dbListenerPort;
    }

    @Column(name="DESCR", nullable = false)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Column(name="HOSTNAME")
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Column(name="SERVICE_NAME")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Column(name="SRV_LOGID")
    public String getSrvLogid() {
        return srvLogid;
    }

    public void setSrvLogid(String srvLogid) {
        this.srvLogid = srvLogid;
    }

    @Column(name="SRV_PWD")
    public String getSrvPwd() {
        return srvPwd;
    }

    public void setSrvPwd(String srvPwd) {
        this.srvPwd = srvPwd;
    }


    @Column(name="STATUS_CD", nullable = false)
    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }


    @Column(name="USE_TNS", nullable = false)
    public String getUseTns() {
        return useTns;
    }

    public void setUseTns(String useTns) {
        this.useTns = useTns;
    }

    @Column(name="TNS_ENTRY")
    public String getTnsEntry() {
        return tnsEntry;
    }

    public void setTnsEntry(String tnsEntry) {
        this.tnsEntry = tnsEntry;
    }

    @Column(name="DB_TYPE")
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DbConfig)) {
            return false;
        }
        DbConfig dbConfig = (DbConfig) o;
        return Objects.equals(dbCd, dbConfig.dbCd) && Objects.equals(dbListenerPort, dbConfig.dbListenerPort) && Objects.equals(descr, dbConfig.descr) && Objects.equals(hostname, dbConfig.hostname) && Objects.equals(serviceName, dbConfig.serviceName) && Objects.equals(srvLogid, dbConfig.srvLogid) && Objects.equals(srvPwd, dbConfig.srvPwd) && Objects.equals(statusCd, dbConfig.statusCd) && Objects.equals(useTns, dbConfig.useTns) && Objects.equals(tnsEntry, dbConfig.tnsEntry) && Objects.equals(dbType, dbConfig.dbType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbCd, dbListenerPort, descr, hostname, serviceName, srvLogid, srvPwd, statusCd, useTns, tnsEntry, dbType);
    }

    @Override
    public String toString() {
        return "{" +
            " dbCd='" + getDbCd() + "'" +
            ", dbListenerPort='" + getDbListenerPort() + "'" +
            ", descr='" + getDescr() + "'" +
            ", hostname='" + getHostname() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", srvLogid='" + getSrvLogid() + "'" +
            ", srvPwd='" + getSrvPwd() + "'" +
            ", statusCd='" + getStatusCd() + "'" +
            ", useTns='" + getUseTns() + "'" +
            ", tnsEntry='" + getTnsEntry() + "'" +
            ", dbType='" + getDbType() + "'" +
            "}";
    }


}
