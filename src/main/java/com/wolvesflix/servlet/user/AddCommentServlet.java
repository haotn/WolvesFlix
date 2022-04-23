package com.wolvesflix.servlet.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.CommentDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.User;
import com.wolvesflix.helper.ServletHelper;

@WebServlet("/index/add-comment/*")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private VideoDAO videoDao;
	private CommentDAO commentDao;
	private ServletHelper servletControl;

	public AddCommentServlet() {
		super();
		servletControl = new ServletHelper();
		userDao = new UserDAO();
		videoDao = new VideoDAO();
		commentDao = new CommentDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletControl.toDetail();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		Long videoId = Long.parseLong(uri.substring(uri.lastIndexOf("/") + 1));
		User user = User.parseUser(request.getSession().getAttribute("user"));
		Comment comment = new Comment();
		comment.setUser(user);
		comment.setVideo(videoDao.findByID(videoId));
		comment.setCommentDate(new Date());
		commentDao.create(comment);
		doGet(request, response);
	}

}
