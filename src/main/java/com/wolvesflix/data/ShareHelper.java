package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.List;

import com.wolvesflix.dao.ShareDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Share;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;

public class ShareHelper {
	/**
	 * Generate DAO
	 */
	private static VideoDAO videoDao = new VideoDAO();
	private static UserDAO userDao = new UserDAO();
	private static ShareDAO shareDao = new ShareDAO();
	private static DataGenerator dataGenerator = new DataGenerator();

	/**
	 * Generate Share
	 * 
	 * @return Share
	 */
	public static Share generateShare() {
		Share share = new Share();
		Long videoId = dataGenerator.randVideoId();
		Long userId = dataGenerator.randUserId();
		String fullname = dataGenerator.generateFullname();
		share.setEmail(dataGenerator.generateEmail(dataGenerator.generateEmailName(fullname)));
		share.setShareDate(dataGenerator.generateDate(2010, 2022));
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
		share.setUser(user);
		share.setVideo(video);
		return share;
	}

	/**
	 * Generate List Share
	 * 
	 * @param int size
	 * @return List<Share>
	 */
	public static List<Share> generateShares(int size) {
		List<Share> shares = new ArrayList<Share>();
		for (int i = 0; i < size; i++) {
			shares.add(generateShare());
		}
		return shares;
	}

	/**
	 * Create List Share
	 * 
	 * @param List<Share>
	 */
	public static void createShares(List<Share> shares) {
		for (Share share : shares) {
			shareDao.create(share);
		}
	}

}
