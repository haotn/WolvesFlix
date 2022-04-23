package com.wolvesflix.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Genre.findAll", query = "SELECT o FROM Genre o") })

@Entity
@Table(name = "GENRE")
public class Genre {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "GENRENAME")
	private String genreName;
	@OneToMany(mappedBy = "genre")
	List<VideoGenre> videoGenres;

	@Override
	public String toString() {
		return this.id + " - " + this.genreName;
	}

	public Genre() {
	}

	public Genre(String genreName) {
		super();
		this.genreName = genreName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public List<VideoGenre> getVideoGenres() {
		return videoGenres;
	}

	public void setVideoGenres(List<VideoGenre> videoGenres) {
		this.videoGenres = videoGenres;
	}

}
