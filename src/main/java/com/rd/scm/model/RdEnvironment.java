package com.rd.scm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the RD_ENVIRONMENT database table.
 * 
 */
@Entity
@Table(name = "rd_environment")
public class RdEnvironment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "env_id")
	private int envId;

	@Column(name = "descr")
	private String descr;

	@Column(name = "status_cd")
	private String statusCd;

	// bi-directional many-to-one association to ProjectArchive
	@OneToMany(mappedBy = "environment")
	private List<ProjectArchive> projectArchives;

	public RdEnvironment() {
	}

	public int getEnvId() {
		return this.envId;
	}

	public void setEnvId(int envId) {
		this.envId = envId;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getStatusCd() {
		return this.statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public List<ProjectArchive> getProjectArchives() {
		return this.projectArchives;
	}

	public void setProjectArchives(List<ProjectArchive> projectArchives) {
		this.projectArchives = projectArchives;
	}

	public ProjectArchive addProjectArchive(ProjectArchive projectArchive) {
		getProjectArchives().add(projectArchive);
		projectArchive.setEnvironment(this);

		return projectArchive;
	}

	public ProjectArchive removeProjectArchive(ProjectArchive projectArchive) {
		getProjectArchives().remove(projectArchive);
		projectArchive.setEnvironment(null);

		return projectArchive;
	}

}