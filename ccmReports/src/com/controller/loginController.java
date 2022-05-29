package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bean.H1QueryBean;
import com.util.WebUtils;



/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	  
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		org.apache.log4j.Logger logger = Logger.getLogger(loginController.class);
		String log4jConfigFile = "D:\\joginder\\ccm\\Joginder\\Joginder\\2\\2\\excel\\src\\log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		String username = request.getParameter("Username");
		String password = request.getParameter("psw");
		
			
		if (username.equals("CcmUser") & password.equals("Ccm_Nia@1234") ) {
			 logger.info("InfoMessage:Login Id and password match  Status:success");
			 /*WebUtils w = new WebUtils();   this is for reading header  
			 w.getHeadersInfo(request);*/
			/*RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
			try {
				rd.forward(request, response);
				}
			catch (ServletException | IOException e) {
				logger.error("ErroMessage:Servelet and I/O exception in login controller  Status:failure " +"Exception:"+e.getStackTrace()); 
				}*/
			 try {
				response.sendRedirect("homepage.jsp");
			} catch (IOException e) {
				logger.error("ErroMessage:Servelet and I/O exception in login controller  Status:failure " +"Exception:"+e.getStackTrace());
			}
			} 
		else {
			
			logger.error("ErroMessage:login ID and password not match  Status:failure " +"Exception:please check login controller");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				ArrayList<String> daataa = new ArrayList<String>(); 
				daataa.add("<center><h1>Invalid Credentials</h1></center>");
				request.setAttribute("hi", daataa);
				rd.forward(request, response);
				
			} catch (IOException | ServletException e) {
				logger.error("ErroMessage:ErroMessage:Servelet and I/O exception in login controller  Status:failure " +"Exception:"+e.getStackTrace());
			}
		}
		
		
	}

}
