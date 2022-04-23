package com.wolvesflix.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "ReportShare", procedureName = "TK__SHARE", resultClasses = {
				ReportShare.class }, parameters = { @StoredProcedureParameter(name = "ID", type = Long.class) }) })

@Entity
public class ReportShare {
	@Id
	private String username;
	private String fullname;
	private String useremail;
	private String mailto;
	private Date shareDate;

	public ReportShare() {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.username + " - " + this.fullname + " - " + this.useremail + " - " + this.mailto + " - "
				+ this.shareDate;
	}

	public ReportShare(String username, String fullname, String useremail, String mailto, Date shareDate) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.useremail = useremail;
		this.mailto = mailto;
		this.shareDate = shareDate;
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

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getMailto() {
		return mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

}
