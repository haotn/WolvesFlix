package com.wolvesflix.servlet.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.FavoriteDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Favorite;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;
import com.wolvesflix.helper.ServletHelper;
import com.wolvesflix.helper.XScope;

@WebServlet()
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;


	public FavoriteServlet() {
		super();
		servletControl = new ServletHelper();
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}



}
