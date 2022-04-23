package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.dao.ViewDAO;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;
import com.wolvesflix.entity.View;

public class ViewHelper {
	/**
	 * Generate DAO
	 */
	private static UserDAO userDao = new UserDAO();
	private static VideoDAO videoDao = new VideoDAO();
	private static ViewDAO viewDao = new ViewDAO();
	private static DataGenerator dataGenerator = new DataGenerator();

	/**
	 * Generate View
	 * 
	 * @return View
	 */
	public static View generateView() {
		View view = new View();
		Long videoId = dataGenerator.randVideoId();
		Long userId = dataGenerator.randUserId();
		Date date = dataGenerator.generateDate(2010, 2022);
		User user = userDao.findByID(userId);
		Video video = videoDao.findByID(videoId);
		while (user == null) {
			userId = dataGenerator.randUserId();
			user = userDao.findByID(userId);
		}
		while (video == null) {
			videoId = dataGenerator.randVideoId();
			video = videoDao.findByID(videoId);
		}
		view.setUser(user);
		view.setVideo(video);
		view.setViewDate(date);
		return view;
	}

	/**
	 * Generate Views
	 * 
	 * @param int size
	 * @return List<View>
	 */
	public static List<View> generateViews(int size) {
		List<View> views = new ArrayList<View>();
		for (int i = 0; i < size; i++) {
			views.add(generateView());
		}
		return views;
	}

	/**
	 * Create List View
	 * 
	 * @param List<View>
	 */
	public static void createViews(List<View> views) {
		for (View view : views) {
			viewDao.create(view);
		}
	}

}
