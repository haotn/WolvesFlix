package com.wolvesflix.servlet.access;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.helper.XCookie;
import com.wolvesflix.helper.XScope;

@WebServlet("/access/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		XScope.removeSession("user");
		String cookie = XCookie.get("cookieId", null);
		XCookie.add("cookieId", cookie, 0);
		response.sendRedirect(request.getContextPath() + "/access/signin");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
