package com.wolvesflix.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.AccessHistoryDAO;
import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.entity.AccessHistory;
import com.wolvesflix.entity.User;
import com.wolvesflix.helper.HttpFilter;
import com.wolvesflix.helper.XCookie;
import com.wolvesflix.helper.XScope;

@WebFilter(filterName = "auth", urlPatterns = { "/*" })
public class AuthFilter implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		checkRemember();
		String uri = request.getRequestURI();
		User user = (User) XScope.getSesion("user");
		String paramAction = request.getParameter("action");
		String action = "";
		if (paramAction != null) {
			action = paramAction;
		}
		if (user == null) {
			if (!action.equals("share") && !action.equals("like") && !uri.contains("profile")) {
				chain.doFilter(request, response);
			} else if (((action.equals("share") || action.equals("like") || uri.contains("profile")))) {
				response.sendRedirect(request.getContextPath() + "/access/signin");
			}
		} else {
			if (!user.getAdmin() && uri.contains("admin")) {
				response.sendRedirect(request.getContextPath() + uri);
			} else {
				AccessHistory entity = new AccessHistory();
				AccessHistoryDAO dao = new AccessHistoryDAO();
				entity.setAccessDate(new Date());
				entity.setUri(uri);
				entity.setUser(user);
				dao.create(entity);
				chain.doFilter(request, response);
			}
		}

//		if (!error.isEmpty() && !uri.contains("watch") && !uri.contains("faq") && !uri.contains("contact")) {
//			request.setAttribute("securi", uri);
////			String charset = "UTF-8";
////			String link = String.format(request.getContextPath() + "/access/signin?error=%s",
////					URLEncoder.encode(error, charset));
//
//		} else {
//			if (user != null && user.getAdmin()) {
//			}
//
//		}

	}

	private void checkRemember() {
		String cookieId = XCookie.get("cookieId", null);
		if (cookieId != null) {
			UserDAO dao = new UserDAO();
			User user = dao.checkRemember(cookieId);
			if (user != null) {
				XScope.setSession("user", user);
			}
		}
	}

}
