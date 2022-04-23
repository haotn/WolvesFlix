package com.wolvesflix.data;

import java.util.List;

import com.wolvesflix.dao.GenreDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.dao.VideoGenreDAO;
import com.wolvesflix.entity.Genre;
import com.wolvesflix.entity.Video;
import com.wolvesflix.entity.VideoGenre;

public class VideoGenreHelper {
	private static VideoDAO videoDao = new VideoDAO();
	private static GenreDAO genreDao = new GenreDAO();
	private static VideoGenreDAO vdgDao = new VideoGenreDAO();

	private static VideoGenre generateVideoGenre(Video video, Genre genre) {
		VideoGenre vdg = new VideoGenre();
		vdg.setVideo(video);
		vdg.setGenre(genre);
		return vdg;
	}

	public static Boolean createVideoGenres() {
		List<Video> videos = videoDao.findAll();
		Genre music = genreDao.findByID(1l);
		Genre love = genreDao.findByID(2l);
		for (Video vd : videos) {
			vdgDao.create(generateVideoGenre(vd, music));
			vdgDao.create(generateVideoGenre(vd, love));
		}
		return true;
	}
}
