package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.List;

import com.wolvesflix.dao.GenreDAO;
import com.wolvesflix.entity.Genre;

public class GenreHelper {
	private static List<Genre> getGenres() {
		List<Genre> list = new ArrayList<Genre>();
		list.add(new Genre("Âm nhạc"));
		list.add(new Genre("Tình yêu"));
		list.add(new Genre("Gia đình"));
		list.add(new Genre("Giáo dục"));
		list.add(new Genre("Phim ngắn"));
		list.add(new Genre("Khoa h�?c viễn tưởng"));
		list.add(new Genre("Khoa h�?c tự nhiên"));
		list.add(new Genre("Hành động"));
		list.add(new Genre("Kịch tính"));
		list.add(new Genre("Trinh thám"));
		list.add(new Genre("Kinh dị"));
		list.add(new Genre("Phim tài liệu"));
		return list;
	}

	public static Boolean createGenres() {
		GenreDAO dao = new GenreDAO();
		for (Genre genre : getGenres()) {
			dao.create(genre);
		}
		return true;
	}
}
