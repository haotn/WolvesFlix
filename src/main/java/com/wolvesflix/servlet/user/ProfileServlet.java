package com.wolvesflix.servlet.user;

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
import com.wolvesflix.helper.XScope;

@WebServlet("/home/asdfasdf")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public ProfileServlet() {
		super();
		servletControl = new ServletHelper();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = User.parseUser(XScope.getSesion("user"));
		request.setAttribute("form", user);
		servletControl.toProfile();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (doEditProfile()) {

		} else {
			doGet(request, response);
		}
	}

	public Boolean doEditProfile() {
		UserDAO dao = new UserDAO();
		User user = new User();
		try {
			BeanUtils.populate(user, RRShare.request().getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (FormValidator.validForEditProfile(user)) {
			User seU = User.parseUser(XScope.getSesion("user"));
			seU.setFullname(user.getFullname());
			seU.setUsername(user.getUsername());
			seU.setEmail(user.getEmail());
			dao.update(seU);
			return true;
		}
		return false;
	}

}
