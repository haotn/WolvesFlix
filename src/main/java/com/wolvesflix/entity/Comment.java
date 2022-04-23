package com.wolvesflix.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "Comment.findAll", query = "SELECT o FROM Comment o"),
		@NamedQuery(name = "Comment.findMinId", query = "SELECT MIN(o.id) FROM Comment o"),
		@NamedQuery(name = "Comment.findMaxId", query = "SELECT MAX(o.id) FROM Comment o") ,
		@NamedQuery(name = "Comment.findByUserId", query = "SELECT o FROM Comment o WHERE o.user.id=:userId")
})

@Entity
@Table(name = "COMMENT")
public class Comment {
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
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "COMMENTDATE")
	@Temporal(TemporalType.DATE)
	private Date commentDate;
	@Column(name = "SHOW")
	private Boolean show;
	@OneToMany(mappedBy = "comment")
	private List<Reply> replys;

	public Comment() {
	}

	public Comment(User user, Video video, String content, Date commentDate, Boolean show) {
		super();
		this.user = user;
		this.video = video;
		this.content = content;
		this.commentDate = commentDate;
		this.show = show;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	/**
	 * @return the replys
	 */
	public List<Reply> getReplys() {
		return replys;
	}

	/**
	 * @param replys the replys to set
	 */
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

}
