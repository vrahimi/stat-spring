package com.rd.scm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the RD_WORKFLOW database table.
 * 
 */
@Entity
@Table(name = "rd_workflow")
public class RdWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "workflow_id")
	private int workflowId;

	@Column(name = "descr")
	private String descr;

	@Column(name = "status_cd")
	private String statusCd;

	// bi-directional many-to-one association to Project
	@OneToMany(mappedBy = "workflow")
	private List<Project> projects;

	public RdWorkflow() {
	}

	public int getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
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
		project.setWorkflow(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setWorkflow(null);

		return project;
	}

}