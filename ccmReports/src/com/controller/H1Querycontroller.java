package com.controller;

import com.bean.H1QueryBean;
import com.dao.propertiesDao;
import com.service.Service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class H1Querycontroller
 */
@WebServlet("/H1Querycontroller")
public class H1Querycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public H1Querycontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		String action = request.getParameter("action");
		ArrayList<H1QueryBean> H1QueryList = new ArrayList<H1QueryBean>(); 
		Service service =new Service();
		if(action.equals("H1List")){
			try{
			H1QueryList = service.fetchdetailsfromDB();
			if (H1QueryList != null) {
				
			
			request.setAttribute("alldata", H1QueryList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/H1Query.jsp");
			rd.forward(request, response);
			logger.info("InfoMessage:Sending Data to H1query jsp for view  Status:success");
			}
			else {
				logger.error("ErroMessage:ArrayList return is null or empty  Status:failure" +"Exception:ArrayLis is null"); 
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
			
			
		}catch(Exception e){
			logger.error("ErroMessage:please check try block of H1Query controlller  Status:failure" +"Exception:" + e.getStackTrace()); 
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage: ServletException or IO exception  Status:failure" +"Exception:"+e1.getStackTrace()); 
				// TODO Auto-generated catch block
				
			}
			}
	}
	else {
		logger.error("ErroMessage:action from jsp not recevied in controller  Status:failure" +"Exception:check jsp and controller of H1query"); 
		RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage:ServletException or IOException  Status:failure" +"Exception:"+e.getStackTrace()); 
			// TODO Auto-generated catch block
			
		}	
		}
	}
}
