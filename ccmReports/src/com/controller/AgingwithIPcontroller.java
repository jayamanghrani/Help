package com.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bean.aginingWithIPBean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class controller
 */
@WebServlet("/AgingwithIPcontroller")
public class AgingwithIPcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgingwithIPcontroller() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		String action = request.getParameter("action");
		/*System.out.println(action);*/
		Service serve = new Service();
		/*System.out.println(action + "in controller");*/
		if (action.equals("List")){
			ArrayList<aginingWithIPBean> AgingwithipList = new ArrayList<aginingWithIPBean>();
			try{
			AgingwithipList = serve.AgingwithipList();
			if(serve.AgingwithipList() != null){
			//System.out.println("AgingwithipList  " + "returned from service");
			request.setAttribute("allData", AgingwithipList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/Agingwithip.jsp");
			rd.forward(request, response);
			logger.info("InfoMessage:Sending Data to aging with controller jsp for view  Status:success");
			}
			else {
				logger.error("ErroMessage:arraylist return null from dao  Status:failure" +"Exception:e.printStackTrace()");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
		}catch(Exception e){
			logger.error("ErroMessage:check AgingwithIPcontroller  Status:failure" +"Exception:e.printStackTrace()");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e1) {
				logger.error("ErroMessage:Servelet exception in AgingwithIPcontroller  Status:failure" +"Exception:e1.printStackTrace()");
				
			} catch (IOException e1) {
				logger.error("ErroMessage:request not forwarded to AgingwithIP jsp  Status:failure" +"Exception:e1.printStackTrace()"); 
				
			}
			}
	}
	else {
		RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage: action not received from jsp  Status:failure" +"Exception:e.printStackTrace()");
		}	
		}
	}
}
