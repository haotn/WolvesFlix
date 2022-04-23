package com.wolvesflix.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.entity.User;

public class FormValidator {
	/**
	 * Regex Email
	 */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Validate Email
	 * 
	 * @param emailStr
	 * @return is valid
	 */
	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	/**
	 * Validate fullname
	 * 
	 * @param fullname
	 * @return isvalid
	 */
	public static Boolean isTextContainsSpase(String text) {
		if (!text.trim().contains(" ")) {
			return false;
		}
		return true;
	}

	public static Boolean isTextNotEmpty(String text) {
		if (text == null) {
			return false;
		} else if (text.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	public static Boolean isTextLengthEquals(String text, int length) {
		if (text.length() != length) {
			return false;
		}
		return true;
	}

	public static Boolean isTextLengthGreaterOrEquals(String text, int length) {
		if (text.trim().length() < length) {
			return false;
		}
		return true;
	}

	public static Boolean isTextLengthLessOrEquals(String text, int length) {
		if (text.trim().length() > length) {
			return false;
		}
		return true;
	}

	public static Boolean isTextEquals(String text1, String text2) {
		if (!text1.equals(text2)) {
			return false;
		}
		return true;
	}

	public static Boolean emailIsNotDuplicate(User userUpdate) {
		UserDAO userDao = new UserDAO();
		for (User user : userDao.findAll()) {
			if (userUpdate.getId() == null) {
				if (userUpdate.getEmail().trim().equals(user.getEmail())) {
					return false;
				}
			} else {
				if (user.getId() != userUpdate.getId()) {
					if (userUpdate.getEmail().trim().equals(user.getEmail())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static Boolean userNameIsNotDuplicate(User userUpdate) {
		UserDAO userDao = new UserDAO();
		List<User> list = userDao.findAll();
		for (int i = 0; i < list.size(); i++) {
			if (userUpdate.getId() != list.get(i).getId()) {
				if (list.get(i).getUsername().equals(userUpdate.getUsername())) {
					return false;
				}
			}

		}
		return true;
	}

	public static Boolean validUserForSignUp(User user) {
		String confirmPassword = RRShare.request().getParameter("confirm-password");
		Boolean isValid = true;
		if (!isTextNotEmpty(user.getFullname())) {
			isValid = false;
			RRShare.request().setAttribute("validFullname", "Vui lòng nhập h�? tên");
		} else if (!isTextContainsSpase(user.getFullname())) {
			isValid = false;
			RRShare.request().setAttribute("validFullname", "Họ? tên phải có từ 2 từ trở lên");
		}
		if (!isTextNotEmpty(user.getEmail())) {
			isValid = false;
			RRShare.request().setAttribute("validEmail", "Vui lòng nhập Email");
		} else if (!validateEmail(user.getEmail())) {
			isValid = false;
			RRShare.request().setAttribute("validEmail", "Email không đúng định dạng");
		} else if (emailIsNotDuplicate(User.parseUser(XScope.getSesion("user")))) {
			isValid = false;
			RRShare.request().setAttribute("validEmail", "Email đã được sử dụng bởi ngư�?i khác");
		}
		if (!isTextNotEmpty(user.getPassword())) {
			isValid = false;
			RRShare.request().setAttribute("validPassword", "Vui lòng nhập mật khẩu");
		} else if (!isTextLengthGreaterOrEquals(user.getPassword(), 6)) {
			isValid = false;
			RRShare.request().setAttribute("validPassword", "Mật khẩu phải từ 6 ký tự và không có khoảng trắng");
		} else if (isTextContainsSpase(user.getPassword())) {
			isValid = false;
			RRShare.request().setAttribute("validPassword", "Mật khẩu không được chứa khoảng trắng");
		}
		if (!isTextNotEmpty(confirmPassword)) {
			isValid = false;
			RRShare.request().setAttribute("validConfirmPassword", "Vui lòng xác nhận mật khẩu");
		} else if (!isTextEquals(user.getPassword(), confirmPassword)) {
			isValid = false;
			RRShare.request().setAttribute("validConfirmPassword", "Xác nhận mật khẩu không khớp");
		}
		if (!isTextNotEmpty(user.getUsername())) {
			isValid = false;
			RRShare.request().setAttribute("validUsername", "Vui lòng nhập tên đăng nhập");
		} else if (isTextContainsSpase(user.getUsername())) {
			isValid = false;
			RRShare.request().setAttribute("validUsername", "Tên đăng nhập không được chứa khoảng trắng");
		} else if (!isTextLengthGreaterOrEquals(user.getUsername(), 6)) {
			isValid = false;
			RRShare.request().setAttribute("validUsername", "Tên đăng nhập quá ngắn");
		} else if (userNameIsNotDuplicate(user)) {
			isValid = false;
			RRShare.request().setAttribute("validUsername", "Tên đăng nhập đã được sử dụng");
		}
		if (!isValid) {
			RRShare.request().setAttribute("form", user);
			RRShare.request().setAttribute("confirmPassword", confirmPassword);
			ServletHelper servletControl = new ServletHelper();
			servletControl.toSignup();
		}
		return isValid;
	}

	public static Boolean validForEditProfile(User user) {
		HttpServletRequest rq = RRShare.request();
		Boolean isValid = true;
		if (!isTextNotEmpty(user.getFullname())) {
			isValid = false;
			rq.setAttribute("validFullname", "Vui lòng nhập h�? tên");
		} else if (!isTextContainsSpase(user.getFullname())) {
			isValid = false;
			rq.setAttribute("validFullname", "H�? tên phải có từ 2 từ trở lên");
		}
		if (!isTextNotEmpty(user.getEmail())) {
			isValid = false;
			rq.setAttribute("validEmail", "Vui lòng nhập Email");
		} else if (!validateEmail(user.getEmail())) {
			isValid = false;
			rq.setAttribute("validEmail", "Email không đúng định dạng");
		} else if (emailIsNotDuplicate(User.parseUser(XScope.getSesion("user")))) {
			isValid = false;
			rq.setAttribute("validEmail", "Email đã được sử dụng bởi ngư�?i khác");
		}
		if (!isTextNotEmpty(user.getUsername())) {
			isValid = false;
			rq.setAttribute("validUsername", "Vui lòng nhập tên đăng nhập");
		} else if (!isTextContainsSpase(user.getUsername())) {
			isValid = false;
			rq.setAttribute("validUsername", "Tên đăng nhập không được chứa khoảng trắng");
		} else if (!isTextLengthGreaterOrEquals(user.getUsername(), 6)) {
			isValid = false;
			rq.setAttribute("validUsername", "Tên đăng nhập quá ngắn");
		} else if (!userNameIsNotDuplicate(user)) {
			isValid = false;
			rq.setAttribute("validUsername", "Tên đăng nhập đã được sử dụng");
		}
		if (!isValid) {
			RRShare.request().setAttribute("form", user);
			ServletHelper servletControl = new ServletHelper();
			servletControl.toProfile();
		}
		return isValid;
	}
}
