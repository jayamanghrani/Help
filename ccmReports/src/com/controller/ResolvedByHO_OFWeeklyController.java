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

import com.bean.ResolvedByHO_OFWeeklyBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class ResolvedByHO_OFWeeklyController
 */
@WebServlet("/ResolvedByHO_OFWeeklyController")
public class ResolvedByHO_OFWeeklyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolvedByHO_OFWeeklyController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		if(action.equals("ResolvedByHO_OFWeeklyList")){
			request.setAttribute("controllerName", "ResolvedByHO_OFWeeklyController");
			RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				logger.error("ErroMessage:request not forwarded to Reopen10To3pm jsp  Status:failure " +"Exception:" +e.getStackTrace());
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
		
		
		
		
		
		
		
		ArrayList<ResolvedByHO_OFWeeklyBean> ResolvedByHO_OFWeeklyList = new ArrayList<ResolvedByHO_OFWeeklyBean>(); 
		Service service =new Service();
	
		
			ResolvedByHO_OFWeeklyList = service.ResolvedByHO_OFWeeklyService();
			if (ResolvedByHO_OFWeeklyList!=null & ResolvedByHO_OFWeeklyList.size()!=0) {
			request.setAttribute("alldata", ResolvedByHO_OFWeeklyList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/ResolvedByHO_OFWeekly.jsp");
			rd.forward(request, response);
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of ResolvedByHO_OFWeekly  Status:success");}
			else {
				logger.error("ErroMessage:dasta returned from  ResolvedByHO_OFWeeklyDao is either empty or null  Status:failure " +"Exception:please check controller and dao of ResolvedByHO_OFWeekly");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage:request not forwarded to ResolvedByHO_OFWeekly jsp  Status:failure " +"Exception:" +e1.getStackTrace());
			}
			}
	}
	else {
		RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage:request not forwarded to ResolvedByHO_OFWeekly jsp  Status:failure " +"Exception:" +e.getStackTrace());
		}	
		}
	}
}
