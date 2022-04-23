package com.wolvesflix.servlet.access;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.data.DataGenerator;
import com.wolvesflix.entity.User;
import com.wolvesflix.helper.ServletHelper;
import com.wolvesflix.helper.XCookie;
import com.wolvesflix.helper.XScope;

@WebServlet("/access/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;
	private DataGenerator data;

	public SigninServlet() {
		super();
		data = new DataGenerator();
		servletControl = new ServletHelper();
	}

	UserDAO userDao = new UserDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletControl.toSignin();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = doSignin(request, response);
		if (user != null) {
			XScope.setSession("user", user);
			Boolean remember = request.getParameter("remember") != null;
			if (remember) {
				String cookieId = data.generateCookieID();
				XCookie.add("cookieId", cookieId, (24 * 30));
				user.setCookieId(cookieId);
				userDao.update(user);
			}
			response.sendRedirect(request.getContextPath() + "/home/index");
		} else {
			request.setAttribute("message", "Sai tài khoản hoặc mật khẩu");
			servletControl.toSignin();
		}
	}

	private User doSignin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return userDao.checkLogin(username, password);
	}

}
