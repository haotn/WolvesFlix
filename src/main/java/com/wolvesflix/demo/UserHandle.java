package com.wolvesflix.demo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
import com.wolvesflix.helper.ServletHelper;

//@WebServlet({ "/admin/user-manage/edit/*", "/admin/user-manage/banned" })
public class UserHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public UserHandle() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletControl.toEditUser();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("update")) {
			if (doUpdate(request, response)) {
				doGet(request, response);
			}
		} else if (uri.contains("change-password")) {

			if (doChangePass()) {
				doGet(request, response);
				System.err.println("DoChange pass success");
			}
		} else {
			doGet(request, response);
		}
	}

	private Boolean doUpdate(HttpServletRequest request, HttpServletResponse response) {
		User user = validProfile(request, response);
		UserDAO dao = new UserDAO();
		if (user != null) {
			User entity = dao.findByID(user.getId());
			entity.setAdmin(user.getAdmin());
			entity.setEmail(user.getEmail());
			entity.setFullname(user.getFullname());
			entity.setUsername(user.getUsername());
			if (dao.update(entity)) {
				return true;
			}
		}
		return false;
	}

	private Boolean doChangePass() {
		UserDAO userDao = new UserDAO();
		String uri = RRShare.request().getRequestURI();
		Long userId = Long.parseLong(uri.substring(uri.lastIndexOf("edit/") + 5, uri.lastIndexOf("edit/") + 6));
		User user = userDao.findByID(userId);
		String oldPass = RRShare.request().getParameter("oldPass");
		if (user.getPassword().equals(oldPass)) {
			String password = validChangePassword();
			if (password != null) {
				user.setPassword(password);
				userDao.update(user);
				return true;
			}
		}
		return false;
	}

	private String validChangePassword() {
		HttpServletRequest request = RRShare.request();
		Boolean isValid = true;
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
		if (!FormValidator.isTextNotEmpty(password)) {
			isValid = false;
			request.setAttribute("validPassword", "Vui lòng nhập mật khẩu");
		} else if (!FormValidator.isTextLengthGreaterOrEquals(password, 6)) {
			isValid = false;
			request.setAttribute("validPassword", "Mật khẩu phải từ 6 ký tự và không có khoảng trắng");
		} else if (FormValidator.isTextContainsSpase(password)) {
			isValid = false;
			request.setAttribute("validPassword", "Mật khẩu không được chứa khoảng trắng");
		}
		if (!FormValidator.isTextNotEmpty(confirmPassword)) {
			isValid = false;
			request.setAttribute("validConfirmPassword", "Vui lòng xác nhận mật khẩu");
		} else if (!FormValidator.isTextEquals(password, confirmPassword)) {
			isValid = false;
			request.setAttribute("validConfirmPassword", "Xác nhận mật khẩu không khớp");
		}
		if (isValid) {
			return password;
		} else {
			servletControl.toEditUser();
			return null;
		}
	}

	private User validProfile(HttpServletRequest request, HttpServletResponse response) {
		Boolean isValid = true;
		User user = new User();
		String confirmPassword = request.getParameter("confirm-password");
		try {
			BeanUtils.populate(user, request.getParameterMap());
			System.out.println(user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (user != null) {
			if (!FormValidator.isTextNotEmpty(user.getFullname())) {
				isValid = false;
				request.setAttribute("validFullname", "Vui lòng nhập h�? tên");
			} else if (!FormValidator.isTextContainsSpase(user.getFullname())) {
				isValid = false;
				request.setAttribute("validFullname", "H�? tên phải có từ 2 từ trở lên");
			}
			if (!FormValidator.isTextNotEmpty(user.getEmail())) {
				isValid = false;
				request.setAttribute("validEmail", "Vui lòng nhập Email");
			} else if (!FormValidator.validateEmail(user.getEmail())) {
				isValid = false;
				request.setAttribute("validEmail", "Email không đúng định dạng");
			} else if (!FormValidator.emailIsNotDuplicate( user)) {
				isValid = false;
				request.setAttribute("validEmail", "Email đã được sử dụng bởi ngư�?i khác");
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
			servletControl.toEditUser();
			return null;
		}
	}
}
