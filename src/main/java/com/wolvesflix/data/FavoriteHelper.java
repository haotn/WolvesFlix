package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.List;

import com.wolvesflix.dao.FavoriteDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Favorite;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;

public class FavoriteHelper {
	private static VideoDAO videoDao = new VideoDAO();
	private static UserDAO userDao = new UserDAO();
	private static FavoriteDAO favoriteDao = new FavoriteDAO();
	private static DataGenerator dataGenerator = new DataGenerator();
	/**
	 * Parameters
	 */
	public static final float MAX = (userDao.findAll().size()) * (videoDao.findAll().size());
	public static final float HALF = ((userDao.findAll().size()) * (videoDao.findAll().size())) / 2;
	public static final float PER10 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.1f;
	public static final float PER20 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.2f;
	public static final float PER30 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.3f;
	public static final float PER40 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.4f;
	public static final float PER60 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.6f;
	public static final float PER70 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.7f;
	public static final float PER80 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.8f;
	public static final float PER90 = ((userDao.findAll().size()) * (videoDao.findAll().size())) * 0.9f;

	/**
	 * Generate Favorite
	 * 
	 * @return Favorite
	 */
	public static Favorite generateFavorite() {
		Favorite favorite = new Favorite();
		Long videoId = dataGenerator.randVideoId();
		Long userId = dataGenerator.randUserId();
		favorite.setLikeDate(dataGenerator.generateDate(2010, 2022));
		User user = userDao.findByID(userId);
		while (user == null) {
			userId = dataGenerator.randUserId();
			user = userDao.findByID(userId);
		}
		Video video = videoDao.findByID(videoId);
		while (video == null) {
			videoId = dataGenerator.randVideoId();
			video = videoDao.findByID(videoId);
		}
		favorite.setUser(user);
		favorite.setVideo(video);
		return favorite;
	}

	/**
	 * Compare Favorites
	 * 
	 * @param f1 -> Favorite 1
	 * @param f2 -> Favorite 2
	 * @return is duplicate
	 */

	private static Boolean isDuplicate(Favorite f1, Favorite f2) {
		if (f1.getUser().getId() == f2.getUser().getId() && f1.getVideo().getId() == f2.getVideo().getId()) {
			return true;
		}
		return false;
	}

	/**
	 * Generate List Favorite
	 * 
	 * @param size
	 * @return List<Favorite>
	 */
	public static List<Favorite> generateFavorites(float size) {
		List<Favorite> favorites = new ArrayList<Favorite>();
		for (int i = 0; i < Math.floor(size); i++) {
			Favorite f = generateFavorite();
			if (favorites.size() > 0) {
				Favorite exist = null;
				for (Favorite item : favorites) {
					if (isDuplicate(f, item)) {
						exist = item;
						break;
					}
				}
				if (exist != null) {
					while (isDuplicate(f, exist)) {
						f = generateFavorite();
					}
					favorites.add(f);
				} else {
					favorites.add(f);
				}
			} else {
				favorites.add(f);
			}
		}
		return favorites;
	}

	/*
	 * Create List Favorite
	 */
	public static void createFavorites(List<Favorite> favorites) {
		for (Favorite favorite : favorites) {
			favoriteDao.create(favorite);
		}
	}

}
