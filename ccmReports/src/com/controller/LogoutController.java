package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		org.apache.log4j.Logger logger = Logger.getLogger(loginController.class);
		String action = request.getParameter("action");
		
		if (action.equals("logoutaction")) {
			RequestDispatcher rd = request.getRequestDispatcher("http://eamapst1:8888/oam/server/logout");
			logger.info("InfoMessage:logout successfully  Status:success");
			rd.forward(request, response);
			//response.sendRedirect("http://eamapst1:8888/oam/server/logout?end_url=http://emapst1.newindia.co.in:8889/ccmReports/login.jsp");*/ 
		} /*else {
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
			logger.error("ErroMessage:reues for logout is not appropiate  Status:failure " +"Exception:failure");
			rd.forward(request, response);
		}*/
	}

}
