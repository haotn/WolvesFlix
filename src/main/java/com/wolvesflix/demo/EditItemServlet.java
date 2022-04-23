package com.wolvesflix.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.GenreDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Video;
import com.wolvesflix.entity.VideoGenre;
import com.wolvesflix.helper.ServletHelper;

@WebServlet("/adsd")
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public EditItemServlet() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GenreDAO genreDao = new GenreDAO();
		VideoDAO vdDao = new VideoDAO();
		String uri = request.getRequestURI();
		Video video = null;
		String actionType = null;
		String btnText = "Thêm";
		if (uri.contains("create-video")) {
			actionType = "create-video";
		} else if (uri.contains("update-video")) {

		} else if (uri.contains("edit-video")) {
			actionType = "update-video";
			btnText = "Lưu";
			// Get Id Video
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			video = vdDao.findByID(Long.parseLong(id));
		}
		List<Long> formGenresId = new ArrayList<Long>();
		for (VideoGenre g : video.getVideoGenres()) {
			formGenresId.add(g.getGenre().getId());
		}
		request.setAttribute("actionType", actionType);
		request.setAttribute("form", video);
		request.setAttribute("formGenreIds", video);
		request.setAttribute("btnText", btnText);
		request.setAttribute("page", "add-item");
		request.setAttribute("genres", genreDao.findAll());
		request.setAttribute("url", request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/views/admin/index-admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
