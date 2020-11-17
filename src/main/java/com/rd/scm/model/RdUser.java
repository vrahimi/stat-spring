package com.rd.scm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the RD_USER database table.
 * 
 */
@Entity
@Table(name = "rd_user")
public class RdUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	// @GeneratedValue(strategy = GenerationType.AUTO) - selects a generation
	// strategy based on the database specific dialect. For most popular databases,
	// it selects GenerationType.SEQUENCE
	private int userId;

	@Column(name = "user_code", nullable = false)
	private String userCode;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "status_cd", nullable = false)
	private String statusCd;

	// bi-directional many-to-one association to Project
	@OneToMany(mappedBy = "owner")
	private List<Project> ownedProjects;

	// bi-directional many-to-one association to Project
	@OneToMany(mappedBy = "updatedUser")
	private List<Project> updatedProjects;

	public RdUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStatusCd() {
		return this.statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public List<Project> getOwnedProjects() {
		return this.ownedProjects;
	}

	public void setOwnedProjects(List<Project> ownedProjects) {
		this.ownedProjects = ownedProjects;
	}

	public Project addOwnedProject(Project ownedProject) {
		getOwnedProjects().add(ownedProject);
		ownedProject.setOwner(this);

		return ownedProject;
	}

	public Project removeOwnedProject(Project ownedProject) {
		getOwnedProjects().remove(ownedProject);
		ownedProject.setOwner(null);

		return ownedProject;
	}

	public List<Project> getUpdatedProjects() {
		return this.updatedProjects;
	}

	public void setUpdatedProjects(List<Project> updatedProjects) {
		this.updatedProjects = updatedProjects;
	}

	public Project addUpdatedProject(Project updatedProject) {
		getUpdatedProjects().add(updatedProject);
		updatedProject.setUpdatedUser(this);

		return updatedProject;
	}

	public Project removeUpdatedProject(Project updatedProject) {
		getUpdatedProjects().remove(updatedProject);
		updatedProject.setUpdatedUser(null);

		return updatedProject;
	}

	public RdUser(String userCode, String firstName, String lastName, String statusCd) {
		super();
		this.userCode = userCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.statusCd = statusCd;
	}

	@Override
	public String toString() {
		return "RdUser [userId=" + userId + ", userCode=" + userCode + ", firstName=" + firstName + ", lastName="
				+ lastName + ", statusCd=" + statusCd + ", ownedProjects=" + ownedProjects + ", updatedProjects="
				+ updatedProjects + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		result = prime * result + ((ownedProjects == null) ? 0 : ownedProjects.hashCode());
		result = prime * result + ((statusCd == null) ? 0 : statusCd.hashCode());
//		result = prime * result + ((updatedProjects == null) ? 0 : updatedProjects.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RdUser other = (RdUser) obj;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.firstName))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
//		if (ownedProjects == null) {
//			if (other.ownedProjects != null)
//				return false;
//		} else if (!ownedProjects.equals(other.ownedProjects))
//			return false;
		if (statusCd == null) {
			if (other.statusCd != null)
				return false;
		} else if (!statusCd.equals(other.statusCd))
			return false;
//		if (updatedProjects == null) {
//			if (other.updatedProjects != null)
//				return false;
//		} else if (!updatedProjects.equals(other.updatedProjects))
//			return false;
//		if (userId != other.userId)
//			return false;
		return true;
	}

}