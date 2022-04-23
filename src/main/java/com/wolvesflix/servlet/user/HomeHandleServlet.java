package com.wolvesflix.servlet.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wolvesflix.dao.FavoriteDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Favorite;
import com.wolvesflix.entity.Share;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.Video;
import com.wolvesflix.helper.FormValidator;
import com.wolvesflix.helper.RRShare;
import com.wolvesflix.helper.SendMail;
import com.wolvesflix.helper.ServletHelper;
import com.wolvesflix.helper.XCookie;
import com.wolvesflix.helper.XScope;

@WebServlet({ "/home/index", "/home/profile" })
public class HomeHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;
	private FavoriteDAO dao;

	public HomeHandleServlet() {
		super();
		dao = new FavoriteDAO();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String param1 = request.getParameter("action");
		String action = "";
		String subaction = "";
		String param2 = request.getParameter("sub-action");
		if (param1 != null) {
			action = param1;
		}
		if (param2 != null) {
			subaction = param2;
		}
		if (uri.contains("index")) {
			switch (action) {
			case "update":
				break;
			case "watch":
				switch (subaction) {
				case "like":
					doFavorite();
					break;
				case "share":
					doShare();
					break;
				case "clear-history":
					servletControl.toDetail();
					break;
				default:
					servletControl.toDetail();
					break;
				}
				break;
			case "like":
				doFavorite();
				break;
			case "share":
				doShare();
				break;
			default:
				servletControl.toHome();
				break;
			}
		} else if (uri.contains("profile")) {
			switch (action) {
			case "like":
				doFavorite();
				break;
			case "share":
				doShare();
				break;
			default:
				servletControl.toProfile();
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String param1 = request.getParameter("action");
		String param2 = request.getParameter("sub-action");
		String action = "";
		String subaction = "";
		if (param1 != null) {
			action = param1;
		}
		if (param2 != null) {
			subaction = param2;
		}
		if (uri.contains("profile")) {
			switch (action) {
			case "share":
				doShare();
				break;
			case "update":
				doEditProfile();
				break;
			case "change-password":
				changePass();
				break;
			default:
				doGet(request, response);
				break;
			}
		} else if (uri.contains("index")) {
			switch (action) {
			case "watch":
				switch (subaction) {
				case "share":
					doShare();
					break;
				default:
					servletControl.toHome();
					break;
				}
				break;
			case "share":
				doShare();
				break;
			default:
				servletControl.toHome();
				break;
			}

		}
	}

	public Boolean doEditProfile() {
		boolean toSignin = false;
		User user = validUser();
		if (user != null) {
			UserDAO dao = new UserDAO();
			User entity = User.parseUser(XScope.getSesion("user"));
			if (!entity.getUsername().equals(user.getUsername()) && !entity.getEmail().equals(user.getEmail())) {
				toSignin = true;
			}
			entity.setFullname(user.getFullname());
			entity.setUsername(user.getUsername());
			entity.setEmail(user.getEmail());
			dao.update(entity);
			XScope.setSession("user", dao.findByID(user.getId()));
			if (toSignin) {
				servletControl.toSignin();
			} else {
				servletControl.toProfile();
			}
		}
		return true;
	}

	private User validUser() {
		HttpServletRequest request = RRShare.request();
		Boolean isValid = true;
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		User onSession = User.parseUser(XScope.getSesion("user"));
		user.setId(onSession.getId());
		if (user != null) {
			if (!FormValidator.isTextNotEmpty(user.getFullname())) {
				isValid = false;
				request.setAttribute("validFullname", "Vui lòng nhập họ tên");
			} else if (!FormValidator.isTextContainsSpase(user.getFullname())) {
				isValid = false;
				request.setAttribute("validFullname", "Họ tên phải có từ 2 từ trở lên");
			}
			if (!FormValidator.isTextNotEmpty(user.getEmail())) {
				isValid = false;
				request.setAttribute("validEmail", "Vui lòng nhập Email");
			} else if (!FormValidator.validateEmail(user.getEmail())) {
				isValid = false;
				request.setAttribute("validEmail", "Email không đúng định dạng");
			} else if (!FormValidator.emailIsNotDuplicate(user)) {
				isValid = false;
				request.setAttribute("validEmail", "Email đã được sử dụng bởi người khác");
			}
			if (!FormValidator.isTextNotEmpty(user.getUsername())) {
				isValid = false;
				request.setAttribute("validUsername", "Vui lòng nhập tên đăng nhập");
			} else if (FormValidator.isTextContainsSpase(user.getUsername())) {
				isValid = false;
				request.setAttribute("validUsername", "Tên đăng nhập không được chứa khoảng trắng");
			} else if (!FormValidator.isTextLengthGreaterOrEquals(user.getUsername(), 6)) {
				isValid = false;
				request.setAttribute("validUsername", "Tên đăng nhập quá ngắn");
			} else if (!FormValidator.userNameIsNotDuplicate(user)) {
				isValid = false;
				request.setAttribute("validUsername", "Tên đăng nhập đã được sử dụng");
			}
		}
		if (isValid) {
			return user;
		} else {
			request.setAttribute("form", user);
			servletControl.toProfile();
			return null;
		}

	}

	public Boolean changePass() {
		User user = User.parseUser(XScope.getSesion("user"));
		HttpServletRequest request = RRShare.request();
		String currentPass = request.getParameter("currentPass");
		String newPass = request.getParameter("newPass");
		String confirm = request.getParameter("confirm");
		boolean valid = true;
		if (currentPass.equals(user.getPassword())) {
			if (newPass.length() > 6) {
				if (newPass.equals(confirm)) {
					user.setPassword(newPass);
					UserDAO dao = new UserDAO();
					dao.update(user);
				} else {
					request.setAttribute("failConfirm", "Xác nhận mật khẩu không đúng!");
					valid = false;
				}
			} else {
				request.setAttribute("failNewPass", "Mật khẩu phải từ 6 ký tự!");
				valid = false;
			}
		} else {
			request.setAttribute("failCurrentPass", "Sai mật khẩu!");
			valid = false;
		}
		servletControl.toProfile();
		return valid;

	}

	protected void doFavorite() throws IOException {
		User user = User.parseUser(XScope.getSesion("user"));
		String watchIdString = RRShare.request().getParameter("watch-id");
		Long watchId = 0l;
		if (watchIdString != null) {
			watchId = Long.parseLong(watchIdString);
		}
		VideoDAO videoDao = new VideoDAO();
		String uri = RRShare.request().getRequestURI();
		String param = RRShare.request().getParameter("like-id");
		String likeIdString = "";
		if (param != null) {
			likeIdString = param;
		}
		Long likeId = 0l;
		if (likeIdString != null) {
			likeId = Long.parseLong(likeIdString);
		}
		Video video = videoDao.findByID(likeId);
		boolean exist = false;
		for (Favorite f : video.getFavorites()) {
			if (f.getUser().getId() == user.getId()) {
				exist = true;
				dao.remove(f.getId());
				break;
			}
		}
		if (!exist) {
			createFavorite(user, video);
		}
		if (uri.contains("profile")) {
			RRShare.response().sendRedirect(RRShare.request().getContextPath() + "/home/profile");
		} else if (RRShare.request().getParameter("action").equals("watch")) {
			RRShare.response()
					.sendRedirect(RRShare.request().getContextPath() + "/home/index?action=watch&watch-id=" + watchId);
		} else {
			RRShare.response().sendRedirect(RRShare.request().getContextPath() + "/home/index");
		}

	}

	protected void doShare() throws IOException {
		String uri = RRShare.request().getRequestURI();
		String action = RRShare.request().getParameter("action");
		Long videoId = Long.parseLong(RRShare.request().getParameter("share-id"));
		VideoDAO videoDao = new VideoDAO();
		Video video = videoDao.findByID(videoId);
		User user = User.parseUser(RRShare.request().getSession().getAttribute("user"));
		String mailFrom = user.getEmail();
		String mailTo = RRShare.request().getParameter("mailTo");
		String subject = "Chia sẻ video từ " + mailFrom + "";
		String content = "<h4>Xin chào " + mailTo.substring(0, mailTo.indexOf("@") - 1) + ", "
				+ mailFrom.substring(0, mailFrom.indexOf("@") - 1)
				+ " đã chia sẻ cho bạn một video từ trang web WolvesFlix.com </h4>"
				+ "<p>Bạn truy cập vào đường dẫn đính kèm để xem video nhé!</p> </br>"
				+ "<p>Chúc bạn có những phút giây thư giản!</p></br>" + "Link: "
				+ "http://localhost:8080/PC01545/home/index?action=watch&watch-id=" + videoId;
		SendMail sender = new SendMail();
		sender.setContent(content);
		sender.setMailFrom(mailFrom);
		sender.setMailTo(mailTo);
		sender.setSubject(subject);
		sender.sendEmail();
		createShare(user, video, mailTo);
		if (uri.contains("index")) {
			if (action.equals("watch")) {
				Long currentVideo = Long.parseLong(RRShare.request().getParameter("watch-id"));
				servletControl.toDetail();
//				RRShare.response().sendRedirect(
//						RRShare.request().getContextPath() + "/home/index?action=watch&watch-id=" + currentVideo);
			} else {
				RRShare.response().sendRedirect(RRShare.request().getContextPath() + "/home/index");
			}
		} else if (uri.contains("profile")) {
			RRShare.response().sendRedirect(RRShare.request().getContextPath() + "/home/profile");
		}
	}

	protected void createFavorite(User user, Video video) {
		Favorite favorite = new Favorite();
		favorite.setUser(user);
		favorite.setVideo(video);
		favorite.setLikeDate(new Date());
		dao.create(favorite);
	}

	protected void createShare(User user, Video video, String email) {
		Share share = new Share();
		share.setEmail(email);
		share.setUser(user);
		share.setVideo(video);
		share.setShareDate(new Date());
	}
}
