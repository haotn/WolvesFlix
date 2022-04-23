package com.wolvesflix.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "ReportUserLikeVideo", procedureName = "TK__USER_FAVORITE", parameters = {
				@StoredProcedureParameter(name = "ID", type = Long.class) }, resultClasses = {
						ReportUserLikeVideo.class }) })

@Entity
public class ReportUserLikeVideo {
	@Id
	private String username;
	private String fullname;
	private String email;
	private Date likeDate;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.username + " - " + this.fullname + " - " + this.email + " - " + this.likeDate;
	}

	public ReportUserLikeVideo() {

	}

	public ReportUserLikeVideo(String username, String fullname, String email, Date likeDate) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.likeDate = likeDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
}
