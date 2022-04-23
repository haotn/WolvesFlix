package com.wolvesflix.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wolvesflix.helper.FormValidator;
import com.wolvesflix.helper.HttpFilter;
import com.wolvesflix.helper.RRShare;

@WebFilter(filterName = "app", urlPatterns = { "/*" })
public class AppFilter implements HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.getServletContext().setAttribute("rootpath", request.getContextPath());
		String lang = request.getParameter("lang");
		if (FormValidator.isTextNotEmpty(lang)) {
			request.getSession().setAttribute("lang", lang);
		}
		RRShare.add(request, response);
		chain.doFilter(request, response);
	}
}
