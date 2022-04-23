package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.List;

import com.wolvesflix.dao.CommentDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;

public class CommentHelper {
	private static VideoDAO videoDao = new VideoDAO();
	private static UserDAO userDao = new UserDAO();
	private static CommentDAO commentDao = new CommentDAO();
	private static DataGenerator dataGenerator = new DataGenerator();

	/**
	 * Generate Comment
	 * 
	 * @return Comment
	 */
	public static Comment generateComment() {
		Comment comment = new Comment();
		Long videoId = dataGenerator.randVideoId();
		Long userId = dataGenerator.randUserId();
		comment.setCommentDate(dataGenerator.generateDate(2010, 2022));
		comment.setContent(dataGenerator.generateDescription(100));
		comment.setShow(true);
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
		comment.setUser(user);
		comment.setVideo(video);
		return comment;
	}

	/**
	 * Generate List Comment
	 * 
	 * @param size
	 * @return List<Comment>
	 */

	public static List<Comment> generateComments(int size) {
		List<Comment> comments = new ArrayList<Comment>();
		for (int i = 0; i < size; i++) {
			comments.add(generateComment());
		}
		return comments;
	}

	/**
	 * Create List Comment
	 * 
	 * @param comments
	 */
	public static Boolean createComments(List<Comment> comments) {
		for (Comment comment : comments) {
			commentDao.create(comment);
		}
		return true;
	}

}
