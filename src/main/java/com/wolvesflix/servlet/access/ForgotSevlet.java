package com.wolvesflix.servlet.access;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.entity.User;
import com.wolvesflix.helper.SendMail;
import com.wolvesflix.helper.ServletHelper;

@WebServlet("/access/forgot")
public class ForgotSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;
	private UserDAO dao;

	public ForgotSevlet() {
		super();
		servletControl = new ServletHelper();
		dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletControl.toForgot();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = null;
		String email = request.getParameter("email");
		for (User item : dao.findAll()) {
			if (item.getEmail().equals(email)) {
				if (item.getAccept()) {
					user = item;
					break;
				}
			}
		}

		if (user != null) {
			SendMail sender = new SendMail();
			sender.setMailFrom("wolvesflix.support@gmail.com");
			sender.setMailTo(email);
			sender.setSubject("Lấy lại mật khẩu");
			sender.setContent("Đây là mật khẩu của bạn: " + user.getPassword());
			sender.sendEmail();
		}
		response.sendRedirect(request.getContextPath() + "/access/signin");
	}

}
