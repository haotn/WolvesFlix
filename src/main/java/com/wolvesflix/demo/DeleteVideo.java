package com.wolvesflix.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.helper.ServletHelper;

@WebServlet("/admin/video-manage/delete-video/*")
public class DeleteVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public DeleteVideo() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long videoId = Long.parseLong(request.getParameter("deleteId"));
		VideoDAO dao = new VideoDAO();
		servletControl.toVideoManage();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
