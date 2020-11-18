package com.rd.scm.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the PROJECT database table.
 * 
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "project_id")
	private int projectId;

	@Column(name = "descr", nullable = false)
	private String descr;

	@Column(name = "update_dt", nullable = false)
	private Timestamp updateDt;

	// bi-directional many-to-one association to RdStep
	@ManyToOne
	@JoinColumn(name = "step_id", nullable = false)
	private RdStep step;

	// bi-directional many-to-one association to RdUser
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private RdUser owner;

	// bi-directional many-to-one association to RdUser
	@ManyToOne
	@JoinColumn(name = "update_userid", nullable = false)
	private RdUser updatedUser;

	// bi-directional many-to-one association to RdWorkflow
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "workflow_id")
	private RdWorkflow workflow;

	// bi-directional many-to-one association to ProjectArchive
	@OneToMany(mappedBy = "project")
	private List<ProjectArchive> projectArchives;

	public Project() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Timestamp getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Timestamp updateDt) {
		this.updateDt = updateDt;
	}

	public RdStep getStep() {
		return this.step;
	}

	public void setStep(RdStep step) {
		this.step = step;
	}

	public RdUser getOwner() {
		return this.owner;
	}

	public void setOwner(RdUser owner) {
		this.owner = owner;
	}

	public RdUser getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(RdUser updatedUser) {
		this.updatedUser = updatedUser;
	}

	public RdWorkflow getWorkflow() {
		return this.workflow;
	}

	public void setWorkflow(RdWorkflow workflow) {
		this.workflow = workflow;
	}

	public List<ProjectArchive> getProjectArchives() {
		return this.projectArchives;
	}

	public void setProjectArchives(List<ProjectArchive> projectArchives) {
		this.projectArchives = projectArchives;
	}

	public ProjectArchive addProjectArchive(ProjectArchive projectArchive) {
		getProjectArchives().add(projectArchive);
		projectArchive.setProject(this);

		return projectArchive;
	}

	public ProjectArchive removeProjectArchive(ProjectArchive projectArchive) {
		getProjectArchives().remove(projectArchive);
		projectArchive.setProject(null);

		return projectArchive;
	}

	public Project(String descr, RdStep step, RdUser owner, RdWorkflow workflow, Timestamp updateDt,
			RdUser updatedUser) {
		super();
		this.descr = descr;
		this.step = step;
		this.owner = owner;
		this.workflow = workflow;
		this.updateDt = updateDt;
		this.updatedUser = updatedUser;
	}

}