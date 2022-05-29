package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bean.ReopenedBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class ReopenedController
 */
@WebServlet("/ReopenedController")
public class ReopenedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReopenedController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		String action = request.getParameter("action");
		
		if(action.equals("ReopenedList")){
			request.setAttribute("controllerName", "ReopenedController");
			RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("List")){
			try{
				String fromdate =  request.getParameter("fromDate");
				String todate =  request.getParameter("toDate");
				/*System.out.println("From Date :/ " +fromdate);
				System.out.println("To Date :/ " +todate);*/
				String modifieFromDate = fromdate.replace("-", "/");
				String modifieToDate = todate.replace("-", "/");
				java.util.Date frmdt= new SimpleDateFormat("yyyy/MM/dd").parse(modifieFromDate);
				java.util.Date todt = new SimpleDateFormat("yyyy/MM/dd").parse(modifieToDate);
				//System.out.println("dt:" +dt );
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String convFromdate = format.format(frmdt);
				String convtodate = format.format(todt);
				/*System.out.println("Converted From Date"+convFromdate);
				System.out.println("Converted To Date"+convtodate);*/
				dateBean.setUserEnteredFromdate(convFromdate);
				dateBean.setUserEnteredTodate(convtodate);	
	
		
		
		ArrayList<ReopenedBean> ReopenedList = new ArrayList<ReopenedBean>(); 
		Service service =new Service();
			ReopenedList = service.ReopenedService();
			if (ReopenedList!=null & ReopenedList.size()!=0) {
	
			request.setAttribute("alldata", ReopenedList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/Reopened.jsp");
			rd.forward(request, response);
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of Reopened  Status:success");
			}
			
			else {
				logger.error("ErroMessage:dasta returned from  ReopenedDao is either empty or null  Status:failure " +"Exception:please check controller and dao of Reopened");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage:request not forwarded to Reopened jsp  Status:failure " +"Exception:" +e1.getStackTrace());
				e1.printStackTrace();
			}
			}
	}
	else {
		RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage:request not forwarded to Reopened jsp  Status:failure " +"Exception:" +e.getStackTrace());
			
		}	
		}
	}
}
