package com.rd.scm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The persistent class for the RD_OBJECT database table.
 * 
 */
@Entity
@Table(name = "rd_object")
public class RdObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "objectid")
	private int objectid;

	@Column(name = "descr")
	private String descr;

	@Column(name = "object_type_id")
	private int objectTypeId;

	// bi-directional many-to-many association to ProjectArchive
	@ManyToMany
	@JoinTable(name = "project_archive_object", joinColumns = { @JoinColumn(name = "objectid") }, inverseJoinColumns = {
			@JoinColumn(name = "archive_id") })
	private List<ProjectArchive> projectArchives;

	public RdObject() {
	}

	public int getObjectid() {
		return this.objectid;
	}

	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getObjectTypeId() {
		return this.objectTypeId;
	}

	public void setObjectTypeId(int objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public List<ProjectArchive> getProjectArchives() {
		return this.projectArchives;
	}

	public void setProjectArchives(List<ProjectArchive> projectArchives) {
		this.projectArchives = projectArchives;
	}

}