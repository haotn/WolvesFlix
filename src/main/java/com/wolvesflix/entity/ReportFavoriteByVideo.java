package com.wolvesflix.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "ReportFavoriteByVideo", procedureName = "TK__VIDEO_FAVORITE", resultClasses = {
				ReportFavoriteByVideo.class })

})
@Entity
public class ReportFavoriteByVideo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1174061311856718445L;
	@Id
	private String title;
	private Long likeCount;
	private Date newest;
	private Date oldest;

	public ReportFavoriteByVideo() {
	}

	public ReportFavoriteByVideo(String title, Long likeCount, Date newest, Date oldest) {
		super();
		this.title = title;
		this.likeCount = likeCount;
		this.newest = newest;
		this.oldest = oldest;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Date getNewest() {
		return newest;
	}

	public void setNewest(Date newest) {
		this.newest = newest;
	}

	public Date getOldest() {
		return oldest;
	}

	public void setOldest(Date oldest) {
		this.oldest = oldest;
	}

}
