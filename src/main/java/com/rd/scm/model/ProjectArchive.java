package com.rd.scm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the PROJECT_ARCHIVE database table.
 * 
 */
@Entity
@Table(name = "project_archive")
public class ProjectArchive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "archive_id")
	private int archiveId;

	// bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	// bi-directional many-to-one association to RdEnvironment
	@ManyToOne
	@JoinColumn(name = "env_id")
	private RdEnvironment environment;

	// bi-directional many-to-many association to RdObject
	@ManyToMany(mappedBy = "projectArchives")
	private List<RdObject> rdObjects;

	public ProjectArchive() {
	}

	public int getArchiveId() {
		return this.archiveId;
	}

	public void setArchiveId(int archiveId) {
		this.archiveId = archiveId;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public RdEnvironment getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(RdEnvironment environment) {
		this.environment = environment;
	}

	public List<RdObject> getRdObjects() {
		return this.rdObjects;
	}

	public void setRdObjects(List<RdObject> rdObjects) {
		this.rdObjects = rdObjects;
	}

}