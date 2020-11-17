package com.rd.scm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the RD_STEP database table.
 * 
 */
@Entity
@Table(name = "rd_step")
public class RdStep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "step_id")
	private int stepId;

	@Column(name = "descr")
	private String descr;

	@Column(name = "status_cd")
	private String statusCd;

	// bi-directional many-to-one association to Project
	@OneToMany(mappedBy = "step")
	private List<Project> projects;

	public RdStep() {
	}

	public int getStepId() {
		return this.stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
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

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setStep(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setStep(null);

		return project;
	}

}