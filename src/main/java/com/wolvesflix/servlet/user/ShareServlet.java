package com.wolvesflix.servlet.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Share;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;
import com.wolvesflix.helper.SendMail;
import com.wolvesflix.helper.ServletHelper;

@WebServlet({})
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public ShareServlet() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

	
	}

	private Boolean checkIsLogin(HttpServletRequest request) {
		User user = (User) request.getServletContext().getAttribute("user");
		if (user != null) {
			return true;
		}
		return false;
	}

}
