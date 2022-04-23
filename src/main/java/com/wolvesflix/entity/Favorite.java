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
import javax.persistence.UniqueConstraint;

@NamedQueries({ @NamedQuery(name = "Favorite.findAll", query = "SELECT o FROM Favorite o"),
		@NamedQuery(name = "Favorite.findByUserIdAndVideoId", query = "SELECT o FROM Favorite o WHERE o.user.id=:userId AND o.video.id=:videoId"),
		@NamedQuery(name = "Favorite.countByMonth", query = "SELECT COUNT(o) FROM Favorite o WHERE MONTH(o.likeDate) = MONTH(GETDATE())") })

@Entity
@Table(name = "FAVORITE", uniqueConstraints = { @UniqueConstraint(columnNames = { "USERID", "VIDEOID" }) })
public class Favorite {
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
	@Column(name = "LIKEDATE")
	@Temporal(TemporalType.DATE)
	private Date likeDate;

	public Favorite() {
	}

	public Favorite(User user, Video video, Date likeDate) {
		super();
		this.user = user;
		this.video = video;
		this.likeDate = likeDate;
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

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

}
