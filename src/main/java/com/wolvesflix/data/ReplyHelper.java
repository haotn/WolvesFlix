package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wolvesflix.dao.CommentDAO;
import com.wolvesflix.dao.ReplyDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.Reply;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;

public class ReplyHelper {
	private static VideoDAO videoDao = new VideoDAO();
	private static UserDAO userDao = new UserDAO();
	private static ReplyDAO replyDao = new ReplyDAO();
	private static CommentDAO commentDao = new CommentDAO();
	private static DataGenerator dataGenerator = new DataGenerator();

	/**
	 * Generate Reply
	 * 
	 * @return Reply
	 */
	public static Reply generateReply() {
		// Generate new Reply
		Reply reply = new Reply();
		/**
		 * Get Entity ID
		 */
		Long videoId = dataGenerator.randVideoId();
		Long userId = dataGenerator.randUserId();
		Long commendId = dataGenerator.randCommentId();
		/**
		 * Set properties for Reply
		 */
		int quote = dataGenerator.randomMinMax(0, 1);
		switch (quote) {
		case 0:
			reply.setHasQuote(false);
			break;
		case 1:
			reply.setHasQuote(true);
			break;
		}
		reply.setShow(true);

		Comment comment = null;
		User user = null;
		Video video = null;
		/**
		 * Select from database while null
		 */
		while (user == null) {
			userId = dataGenerator.randUserId();
			user = userDao.findByID(userId);
		}
		while (video == null) {
			videoId = dataGenerator.randVideoId();
			video = videoDao.findByID(videoId);
		}
		while (comment == null) {
			commendId = dataGenerator.randCommentId();
			comment = commentDao.findByID(commendId);
		}
		reply.setContent(dataGenerator.generateDescription(100));
		Date cmdate = comment.getCommentDate();
		Date rpDate = generateReplyDate(cmdate);
		reply.setReplyDate(rpDate);
		reply.setComment(comment);
		reply.setUser(user);
		reply.setVideo(video);
		return reply;
	}

	private static Date generateReplyDate(Date cmDate) {
		Date date = dataGenerator.generateDate(2022, 2022);
		while (date.before(cmDate)) {
			date = dataGenerator.generateDate(2022, 2022);
		}
		return date;
	}

	/**
	 * Generate List Reply with amount
	 * 
	 * @param size
	 * @return List<Reply>
	 */
	public static List<Reply> generateReplys(int size) {
		List<Reply> replys = new ArrayList<Reply>();
		for (int i = 0; i < size; i++) {
			replys.add(generateReply());
		}
		return replys;
	}

	/**
	 * Create List Reply
	 * 
	 * @param List<Reply>
	 */

	public static void createReplys(List<Reply> replys) {
		for (Reply reply : replys) {
			replyDao.create(reply);
		}
	}

}
