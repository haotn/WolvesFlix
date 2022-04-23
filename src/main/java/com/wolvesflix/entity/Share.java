package com.wolvesflix.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "Share.findAll", query = "SELECT o FROM Share o") })
@Entity
@Table(name = "SHARE")
public class Share {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;
	@ManyToOne
	@JoinColumn(name = "VIDEOID")
	private Video video;
	@Column(name = "EMAIL")
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name = "SHAREDATE")
	private Date shareDate;

	public Share() {
	}

	public Share(User user, Video video, String email, Date shareDate) {
		super();
		this.user = user;
		this.video = video;
		this.email = email;
		this.shareDate = shareDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

}
