package com.wolvesflix.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.wolvesflix.dao.CommentDAO;
import com.wolvesflix.dao.FavoriteDAO;
import com.wolvesflix.dao.GenreDAO;
import com.wolvesflix.dao.ReportDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.dao.ViewDAO;
import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.ReportFavoriteByVideo;
import com.wolvesflix.entity.ReportShare;
import com.wolvesflix.entity.ReportUserLikeVideo;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;
import com.wolvesflix.entity.VideoGenre;
import com.wolvesflix.entity.View;
import com.wolvesflix.recommendator.Recommendator;

public class ServletHelper {
	private String mainPath;
	private String adminPath;
	private VideoDAO videoDao;
	private GenreDAO genreDao;
	private FavoriteDAO favoriteDao;
	private UserDAO userDao;
	private CommentDAO commentDao;
	private ViewDAO viewDao;
	private ReportDAO reportDao;
	public static int homeCurrentPage = 1;
	public static int videoManagePage = 1;
	public static int userManagePage = 1;
	public static int commentUsermanagePage = 1;

	public ServletHelper() {
		mainPath = "/WEB-INF/views/user/index.jsp";
		adminPath = "/WEB-INF/views/admin/index.jsp";
		videoDao = new VideoDAO();
		genreDao = new GenreDAO();
		favoriteDao = new FavoriteDAO();
		userDao = new UserDAO();
		commentDao = new CommentDAO();
		viewDao = new ViewDAO();
		reportDao = new ReportDAO();
	}

	public void toSignin() {
		try {
			RRShare.request().getRequestDispatcher("/WEB-INF/views/access/signin.jsp").forward(RRShare.request(),
					RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toSignup() {
		try {
			RRShare.request().getRequestDispatcher("/WEB-INF/views/access/signup.jsp").forward(RRShare.request(),
					RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toAbout() {
		try {
			setPage("about");
			RRShare.request().getRequestDispatcher(mainPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toAddItem() {
		try {
			HttpServletRequest request = RRShare.request();
			String paramAction = request.getParameter("action");
			String action = "";
			if (paramAction != null) {
				action = paramAction;
			}
			Video video = new Video();
			String actionType = null;
			String btnText = "Thêm";
			switch (action) {
			case "create":
				actionType = "create";
				break;
			case "edit":
				actionType = "update";
				btnText = "Lưu";
				// Get Id Video
				Long id = Long.parseLong(request.getParameter("edit-id"));
				video = videoDao.findByID(id);
				request.setAttribute("form", video);
				break;
			default:
				break;
			}
			request.setAttribute("actionType", actionType);
			request.setAttribute("btnText", btnText);
			request.setAttribute("genres", genreDao.findAll());
			request.setAttribute("url", request.getContextPath());
			setPage("edit-video");
			request.getRequestDispatcher(adminPath).forward(request, RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toContact() {
		try {
			setPage("contact");
			RRShare.request().getRequestDispatcher(mainPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toDashboard() {
		try {
			showDashboard();
			setPage("dashboard");
			RRShare.request().getRequestDispatcher(adminPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDashboard() {
		HttpServletRequest request = RRShare.request();
		Long views = viewDao.viewByMonth();
		Long addVideoByMonth = videoDao.countAddByMonth();
		Long userByMonth = userDao.countByMonth();
		Long likeByMonth = favoriteDao.countByMonth();
		Long videoId1 = 1l;
		Long videoId2 = 1l;
		String param = request.getParameter("action");
		String action = "";
		int currentTab = 1;
		if (param != null) {
			action = param;
		}
		switch (action) {
		case "select":
			videoId1 = Long.parseLong(request.getParameter("video-id-1"));
			videoId2 = Long.parseLong(request.getParameter("video-id-2"));
			currentTab = Integer.parseInt(request.getParameter("current-tab"));
			break;
		default:
			break;
		}
		List<ReportFavoriteByVideo> videoReport = reportDao.reportFavoriteByVideo();

		List<ReportUserLikeVideo> userReport = reportDao.reportFavoriteByUser(videoId1);

		List<ReportShare> shareReport = reportDao.reportShare(videoId2);
		List<Video> videos = videoDao.findAll();
		request.setAttribute("videos", videos);
		request.setAttribute("videoCount", addVideoByMonth);
		request.setAttribute("viewCount", views);
		request.setAttribute("userCount", userByMonth);
		request.setAttribute("likeCount", likeByMonth);
		request.setAttribute("videoReport", videoReport);
		request.setAttribute("userReport", userReport);
		request.setAttribute("shareReport", shareReport);
		request.setAttribute("currentTab", currentTab);
		request.setAttribute("currentVideo1", videoId1);
		request.setAttribute("currentVideo2", videoId2);
	}

	public void toDetail() {
		try {
			showDetails();
			setPage("detail");
			RRShare.request().getRequestDispatcher(mainPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addView(User user, Video video) {
		View view = new View();
		view.setUser(user);
		view.setVideo(video);
		view.setViewDate(new Date());
		viewDao.create(view);
	}

	public void showDetails() {
		Long videoId;
		HttpServletRequest request = RRShare.request();
		String subaction = request.getParameter("sub-action");
		if (subaction != null) {
			if (subaction.equals("clear-history")) {
				XCookie.add("history_video", "", -1);
			}
		}
		videoId = Long.parseLong(RRShare.request().getParameter("watch-id"));
		List<Video> historyVideos = new ArrayList<Video>();
		User user = User.parseUser(XScope.getSesion("user"));
		Video video = videoDao.findByID(videoId);
		String history = XCookie.get("history_video", "");
		if (history.trim().isEmpty()) {
			history += videoId;
		} else {
			String[] arr = history.split("-");
			boolean exist = false;
			for (int i = 0; i < arr.length; i++) {
				if (Long.parseLong(arr[i]) == videoId) {
					exist = true;
				}
				historyVideos.add(videoDao.findByID(Long.parseLong(arr[i])));
			}
			if (!exist) {
				history += "-" + videoId;
			}
		}
		addView(user, video);
		// Get list recommend
		List<Video> recommends = recommendator(videoId);
		XCookie.add("history_video", history, 24 * 30);
		RRShare.request().setAttribute("listReply", video.getReplys());
		RRShare.request().setAttribute("video", video);
		RRShare.request().setAttribute("listRecommend", recommends);
		RRShare.request().setAttribute("listHistory", historyVideos);
		RRShare.request().setAttribute("listComment", video.getComments());
	}

	public List<Video> recommendator(Long videoId) {
		List<Video> list = new ArrayList<Video>();
		String path = RRShare.request().getServletContext().getRealPath("/Recommendator");
		String result = Recommendator.runScript(videoId, path);
		String arr[] = result.split(";");
		for (int i = 0; i < arr.length; i++) {
			if (i > 8) {
				break;
			}
			Long id = Long.parseLong(arr[i]);
			list.add(videoDao.findByID(id));
		}
		return list;
	}

	public void toEditUser() {
		try {
			int currentPage = 1;
			// Get request uri
			String uri = RRShare.request().getRequestURI();
			// Get userId from uri
			Long userId = Long.parseLong(RRShare.request().getParameter("edit-id"));
			// Get current comment page
			String pageString = RRShare.request().getParameter("page");
			UserDAO userDao = new UserDAO();
			// Get User by id from database
			User user = userDao.findByID(userId);
			// Check current tab
			String currentTab = "profile";
			String paramTab = RRShare.request().getParameter("current-tab");
			if (paramTab != null) {
				currentTab = paramTab;
			}
			Double pageCount = (double) (user.getComments().size() / 10);
			if (pageCount == 0) {
				pageCount = (double) 1;
			} else if (pageCount - Math.floor(pageCount) > 0) {
				pageCount = Math.ceil(pageCount);
			} else if (pageCount == pageCount.intValue()) {
				pageCount = (Double) pageCount;
			} else {
				pageCount = Math.floor(pageCount);
			}

			if (pageString != null) {
				currentPage = Integer.parseInt(pageString);
			}
			List<Comment> comments = commentDao.getToTable(currentPage, userId);
			RRShare.request().setAttribute("currentTab", currentTab);
			RRShare.request().setAttribute("currentPage", currentPage);
			RRShare.request().setAttribute("lastPage", pageCount.intValue());
			RRShare.request().setAttribute("listComment", comments);
			RRShare.request().setAttribute("form", user);
			setPage("edit-user");
			RRShare.request().getRequestDispatcher(adminPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void checkPageCommentUsermanage(User user) {
//		String pagi = RRShare.request().getParameter("page");
//
//		if (pagi != null) {
//			if (pagi.equalsIgnoreCase("first")) {
//				commentUsermanagePage = 1;
//			} else if (pagi.equalsIgnoreCase("last")) {
//				Double last = Double.parseDouble(String.valueOf(pageCount));
//				commentUsermanagePage = last.intValue();
//			} else {
//				commentUsermanagePage = Integer.parseInt(pagi);
//			}
//		}
//	}

	public void toFAQ() {
		try {
			setPage("faq");
			RRShare.request().getRequestDispatcher(mainPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toForgot() {
		try {
			RRShare.request().getRequestDispatcher("/WEB-INF/views/access/forgot.jsp").forward(RRShare.request(),
					RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toHome() {
		try {
			handleShowVideoHome();
			RRShare.request().setAttribute("topViews", videoDao.topViews());
			RRShare.request().setAttribute("listGenre", genreDao.findAll());
			setPage("home");
			RRShare.request().getRequestDispatcher(mainPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleShowVideoHome() {
		// Generate variable
		String pageString = RRShare.request().getParameter("page");
		String keyword = RRShare.request().getParameter("search");
		int currentPage = 1;
		int lastPage = 1;
		Long genreId = 0l;
		if (pageString != null) {
			// If pageString not null
			currentPage = Integer.parseInt(pageString);
		}
		// Count video in database
		Double pageCount = null;

		if (FormValidator.isTextNotEmpty(keyword)) {
			// Set value for lastPage
			pageCount = (double) (videoDao.getActiveByKeyword(keyword).size() / 6);
		} else {
			pageCount = (double) (videoDao.getAllActive().size() / 6);
		}
		if (pageCount == 0) {
			pageCount = (double) 1;
		} else if (pageCount - Math.floor(pageCount) > 0) {
			pageCount = Math.ceil(pageCount);
		} else if (pageCount == pageCount.intValue()) {
			pageCount = (Double) pageCount;
		} else {
			pageCount = Math.floor(pageCount);
		}
		lastPage = pageCount.intValue();
		List<Video> videos = videoDao.getToShow(currentPage, keyword);
		RRShare.request().setAttribute("filting", genreId);
		RRShare.request().getSession().setAttribute("search", keyword);
		RRShare.request().setAttribute("currentPage", currentPage);
		RRShare.request().setAttribute("lastPage", lastPage);
		RRShare.request().setAttribute("videos", videos);
	}

	public void toProfile() {
		try {
			User user = User.parseUser(XScope.getSesion("user"));
			RRShare.request().setAttribute("form", user);
			if (user != null) {
				RRShare.request().setAttribute("favorites", user.getFavorites());
			}

			setPage("profile");
			System.out.println(user);
			RRShare.request().getRequestDispatcher(mainPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toUserManage() {
		try {
			showTableUserManager();
			List<User> list = userDao.getToTable(userManagePage);
			RRShare.request().setAttribute("listUser", list);
			setPage("user");
			RRShare.request().getRequestDispatcher(adminPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getSortUserId() {
		HttpServletRequest request = RRShare.request();
		String param = request.getParameter("sort-id");
		int id = 1;
		if (param != null) {
			id = Integer.parseInt(param);
		}
		return id;
	}

	public void showTableUserManager() {
		HttpServletRequest request = RRShare.request();
		// Get parameter
		String keyword = request.getParameter("search");
		String search = "";
		// Check if search keyword is not null
		if (keyword != null) {
			search = keyword.trim();
		}
		// Get current page
		String pageString = request.getParameter("page");
		int page = 1;

		if (pageString != null) {
			page = Integer.parseInt(pageString);
		}
		// Get sort id
		int sortId = getSortUserId();
		int size = 0;
		// Generate list to show
		List<User> users = new ArrayList<User>();
		// Get size and list
		Map<Integer, List<User>> map = userDao.findAllOrderBy(sortId, page, search);
		for (Integer key : map.keySet()) {
			size = key;
			users = map.get(key);
			break;
		}
		int lastPage = 1;
		Double pageCount = size / 10.0;
		if (pageCount - Math.floor(pageCount) > 0) {
			pageCount = Math.ceil(pageCount);
		} else if (pageCount == pageCount.intValue()) {
			pageCount = (double) pageCount.intValue();
		} else {
			pageCount = Math.floor(pageCount);
		}
		lastPage = pageCount.intValue();
		request.setAttribute("sortBy", sortId);
		request.setAttribute("searchInUserList", search);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", page);
		request.setAttribute("users", users);
	}

	public void toVideoManage() {
		try {
			showVideoManage();
			setPage("video");
			RRShare.request().setAttribute("total", videoDao.findAll().size());
			RRShare.request().getRequestDispatcher(adminPath).forward(RRShare.request(), RRShare.response());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getSortVideoId() {
		HttpServletRequest request = RRShare.request();
		String param = request.getParameter("sort-id");
		int id = 1;
		if (param != null) {
			id = Integer.parseInt(param);
		}
		request.setAttribute("sortBy", id);
		return id;
	}

	public void showVideoManage() {
		HttpServletRequest request = RRShare.request();
		// Page
		String pageString = request.getParameter("page");
		String keyword = request.getParameter("search");
		String search = "";
		if (keyword != null) {
			search = keyword;
		}
		int page = 1;
		int lastPage = 1;
		if (pageString != null) {
			page = Integer.parseInt(pageString);
		}
		Map<Integer, List<Video>> map = videoDao.findAllOrderBy(getSortVideoId(), page, search);
		List<Video> videos = new ArrayList<Video>();
		int size = 0;
		for (Integer key : map.keySet()) {
			size = key;
			videos = map.get(key);
			break;
		}
		// Page count
		Double pageCount = (double) (size / 10.0);
		if (pageCount == 0) {
			pageCount = (double) 1;
		} else if (pageCount - Math.floor(pageCount) > 0) {
			pageCount = Math.ceil(pageCount);
		} else if (pageCount == pageCount.intValue()) {
			pageCount = (double) pageCount.intValue();
		} else {
			pageCount = Math.floor(pageCount);
		}

		lastPage = pageCount.intValue();
		request.setAttribute("searchInVideoList", search);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", page);
		request.setAttribute("videos", videos);
	}

	public static void setURL() {
		RRShare.request().setAttribute("url", RRShare.request().getContextPath());
	}

	public static void setPage(String page) {
		RRShare.request().setAttribute("page", page);
	}

}
