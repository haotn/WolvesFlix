package com.wolvesflix.entity;

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

@NamedQueries({ @NamedQuery(name = "VideoGenre.findAll", query = "SELECT o FROM VideoGenre o"),
		@NamedQuery(name = "VideoGenre.findByVideoIdAndGenreId", query = "SELECT o FROM VideoGenre o WHERE o.video.id=:videoId AND o.genre.id=:genreId") })

@Entity
@Table(name = "VIDEOGENRE")
public class VideoGenre {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VIDEOID")
	private Video video;
	@ManyToOne
	@JoinColumn(name = "GENREID")
	private Genre genre;

	@Override
	public String toString() {
		return this.genre.getGenreName();
	}

	public VideoGenre() {
	}

	public VideoGenre(Video video, Genre genre) {
		super();
		this.video = video;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
