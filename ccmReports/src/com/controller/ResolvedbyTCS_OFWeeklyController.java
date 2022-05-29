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

import com.bean.ResolvedbyTCS_OFWeeklyBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class ResolvedbyTCS_OFWeeklyController
 */
@WebServlet("/ResolvedbyTCS_OFWeeklyController")
public class ResolvedbyTCS_OFWeeklyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolvedbyTCS_OFWeeklyController() {
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
		String action = request.getParameter("action");
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		if(action.equals("ResolvedbyTCS_OFWeeklyList")){
			request.setAttribute("controllerName", "ResolvedbyTCS_OFWeeklyController");
			RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
			rd.forward(request, response);
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
		
		
		
		
		
		ArrayList<ResolvedbyTCS_OFWeeklyBean> ResolvedbyTCS_OFWeeklyList = new ArrayList<ResolvedbyTCS_OFWeeklyBean>(); 
		Service service =new Service();

			
			ResolvedbyTCS_OFWeeklyList = service.ResolvedbyTCS_OFWeeklyService();
			if (ResolvedbyTCS_OFWeeklyList!=null & ResolvedbyTCS_OFWeeklyList.size()!=0) {
				
			request.setAttribute("alldata", ResolvedbyTCS_OFWeeklyList);
			logger.info("InfoMessage:data successfullly return from dao and send to jsp for ResolvedByHO_OFWeekly  Status:success");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/ResolvedbyTCS_OFWeekly.jsp");
			rd.forward(request, response);
			}
			else {
				logger.error("ErroMessage:request not forwarded to ResolvedByHO_OFWeekly jsp  Status:failure " +"Exception:");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			logger.error("ErroMessage:request not forwarded to ResolvedByHO_OFWeekly jsp  Status:failure " +"Exception:" +e.getStackTrace());
			rd.forward(request, response);
			}
	}
	else {
		RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
		logger.error("ErroMessage:action not received from ResolvedByHO_OFWeekly jsp  Status:failure " +"Exception:please check ResolvedByHO_OFWeekly controller and dao");
		rd.forward(request, response);	
		}
	}
}
