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

@NamedQueries({ @NamedQuery(name = "Reply.findAll", query = "SELECT o FROM Reply o") })
@Entity
@Table(name = "REPLY")
public class Reply {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "COMMENTID")
	private Comment comment;
	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;
	@ManyToOne
	@JoinColumn(name = "VIDEOID")
	private Video video;
	@Column(name = "HASQUOTE")
	private Boolean hasQuote;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "REPLYDATE")
	private Date replyDate;
	@Column(name = "SHOW")
	private Boolean show;

	public Reply() {
	}

	public Reply(Comment comment, User user, Video video, Boolean hasQuote, String content, Date replyDate,
			Boolean show) {
		super();
		this.comment = comment;
		this.user = user;
		this.video = video;
		this.hasQuote = hasQuote;
		this.content = content;
		this.replyDate = replyDate;
		this.show = show;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	public Boolean getHasQuote() {
		return hasQuote;
	}

	public void setHasQuote(Boolean hasQuote) {
		this.hasQuote = hasQuote;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

}
