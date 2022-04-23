package com.wolvesflix.servlet.access;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.entity.User;
import com.wolvesflix.helper.FormValidator;
import com.wolvesflix.helper.RRShare;
import com.wolvesflix.helper.SendMail;
import com.wolvesflix.helper.ServletHelper;

@WebServlet("/access/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public SignupServlet() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletControl.toSignup();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean success = doSignup();
		if (success) {
			servletControl.toSignin();
		}
	}

	private Boolean doSignup() {
		User user = new User();
		try {
			BeanUtils.populate(user, RRShare.request().getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (validUser() != null) {
			user.setAccept(true);
			user.setAdmin(false);
			user.setAvatar("user-circle.svg");
			user.setRegisterDate(new Date());
			UserDAO userDao = new UserDAO();
			SendMail mail = new SendMail();
			mail.setMailFrom("wolvesflix@gmail.com");
			mail.setMailTo(user.getEmail());
			mail.setSubject("Chào mừng tham gia WolvesFlix");
			mail.setContent("<h2>Chào mừng bạn đến với WolvesFlix</h2>"
					+ "<p>Hãy truy cập vào WolvesFlix để có những phút giây thư giản!</p>");
			mail.sendEmail();
			userDao.create(user);
			return true;
		}
		return false;
	}

	private User validUser() {
		HttpServletRequest request = RRShare.request();
		Boolean isValid = true;
		User user = new User();
		String confirmPassword = request.getParameter("confirm-password");
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
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
			if (!FormValidator.isTextNotEmpty(user.getPassword())) {
				isValid = false;
				request.setAttribute("validPassword", "Vui lòng nhập mật khẩu");
			} else if (!FormValidator.isTextLengthGreaterOrEquals(user.getPassword(), 6)) {
				isValid = false;
				request.setAttribute("validPassword", "Mật khẩu phải từ 6 ký tự và không có khoảng trắng");
			} else if (FormValidator.isTextContainsSpase(user.getPassword())) {
				isValid = false;
				request.setAttribute("validPassword", "Mật khẩu không được chứa khoảng trắng");
			}
			if (!FormValidator.isTextNotEmpty(confirmPassword)) {
				isValid = false;
				request.setAttribute("validConfirmPassword", "Vui lòng xác nhận mật khẩu");
			} else if (!FormValidator.isTextEquals(user.getPassword(), confirmPassword)) {
				isValid = false;
				request.setAttribute("validConfirmPassword", "Xác nhận mật khẩu không khớp");
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
			request.setAttribute("confirmPassword", confirmPassword);
			servletControl.toSignup();
			return null;
		}

	}

}
