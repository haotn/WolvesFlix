package com.wolvesflix.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo")
public class demoajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public demoajax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/demo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int numb1 = Integer.parseInt(request.getParameter("numb1"));
		int numb2 = Integer.parseInt(request.getParameter("numb2"));
		int result = numb1 + numb2;
		System.out.println(result);
		response.getWriter().write(result);
//		doGet(request, response);
	}

}
