package com.wolvesflix.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@NamedQueries({ @NamedQuery(name = "Video.findAll", query = "SELECT o FROM Video o "),
		@NamedQuery(name = "Video.findByYoutubeId", query = "SELECT o FROM Video o WHERE o.youtubeID=:youtubeId"),
		@NamedQuery(name = "Video.findMinId", query = "SELECT MIN(o.id) FROM Video o"),
		@NamedQuery(name = "Video.findMaxId", query = "SELECT MAX(o.id) FROM Video o"),
		@NamedQuery(name = "Video.findToShow", query = "SELECT o FROM Video o WHERE o.active=true"),
		@NamedQuery(name = "Video.findToShowByKeyword", query = "SELECT o FROM Video o WHERE o.active=true and o.title LIKE :keyword"),
		@NamedQuery(name = "Video.countAddByMonth", query = "SELECT COUNT(o) FROM Video o WHERE MONTH(o.addDate) = MONTH(GETDATE())"),
		@NamedQuery(name = "Video.findByKeywordAndSortByTitle", query = "SELECT o FROM Video o WHERE o.title LIKE :keyword ORDER BY o.title ASC"),
		@NamedQuery(name = "Video.findByKeywordAndSortById", query = "SELECT o FROM Video o WHERE o.title LIKE :keyword ORDER BY o.id ASC"),
		@NamedQuery(name = "Video.findByKeywordAndSortByViews", query = "SELECT o FROM Video o WHERE o.title LIKE :keyword  ORDER BY SIZE(o.views) ASC"),
		@NamedQuery(name = "Video.findByKeywordAndSortByAddDate", query = "SELECT o FROM Video o WHERE o.title LIKE :keyword  ORDER BY o.addDate DESC") })

@NamedNativeQueries({ @NamedNativeQuery(name = "Video.topViews", query = "SELECT * FROM VIDEO WHERE ID IN "
		+ "(SELECT TOP 5  V.ID FROM [VIDEO] V JOIN [VIEW] VS ON V.ID=VS.VIDEOID "
		+ "GROUP BY V.ID, V.TITLE, V.POSTER, V.YOUTUBEID, V.DESCRIPTION, V.ADDDATE, "
		+ "V.ACTIVE HAVING V.ACTIVE=1 ORDER BY COUNT(V.ID) DESC)", resultClass = Video.class) })
@Entity
@Table(name = "VIDEO")
public class Video {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "YOUTUBEID")
	private String youtubeID;
	@Column(name = "POSTER")
	private String poster;
	@Column(name = "ADDDATE")
	private Date addDate = new Date();
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "ACTIVE")
	private Boolean active;
	@OneToMany(mappedBy = "video")
	private List<Favorite> favorites;
	@OneToMany(mappedBy = "video")
	private List<Comment> comments;
	@OneToMany(mappedBy = "video")
	private List<Share> shares;
	@OneToMany(mappedBy = "video")
	private List<View> views;
	@OneToMany(mappedBy = "video")
	private List<VideoGenre> videoGenres;
	@OneToMany(mappedBy = "video")
	private List<Reply> replys;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + " - " + this.title + " - " + this.description + " - " + this.poster + " - " + this.youtubeID
				+ " - " + this.active + " - ";
	}

	public Video() {
	}

	public Video(String title, String youtubeID, String poster, Date addDate, String description, Boolean active) {
		super();
		this.title = title;
		this.youtubeID = youtubeID;
		this.poster = poster;
		this.addDate = addDate;
		this.description = description;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYoutubeID() {
		return youtubeID;
	}

	public void setYoutubeID(String youtubeID) {
		this.youtubeID = youtubeID;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public List<VideoGenre> getVideoGenres() {
		return videoGenres;
	}

	public void setVideoGenres(List<VideoGenre> videoGenres) {
		this.videoGenres = videoGenres;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

}
