package com.wolvesflix.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "View.findAll", query = "SELECT o FROM View o"),
		@NamedQuery(name = "View.countByMonth", query = "SELECT COUNT(o) FROM View o WHERE MONTH(o.viewDate) = MONTH(GETDATE())") })
@Entity
@Table(name = "`VIEW`")
public class View {
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
	@Column(name = "VIEWDATE")
	@Temporal(TemporalType.DATE)
	private Date viewDate;

	public View() {
	}

	public View(User user, Video video, Date viewDate) {
		super();
		this.user = user;
		this.video = video;
		this.viewDate = viewDate;
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

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

}
