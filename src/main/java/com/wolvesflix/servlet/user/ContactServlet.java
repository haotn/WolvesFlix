package com.wolvesflix.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.helper.SendMail;
import com.wolvesflix.helper.ServletHelper;

@WebServlet("/home/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public ContactServlet() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletControl.toContact();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		SendMail sendMail = new SendMail();
		sendMail.setMailFrom(email);
		sendMail.setMailTo("haotnpc01545@fpt.edu.vn");
		sendMail.setSubject(subject);
		sendMail.setContent("From: " + fullname + "\n" + content);
		sendMail.sendEmail();
		response.sendRedirect(request.getContextPath() + "/home/contact");
	}

}
