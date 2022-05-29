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

import com.bean.ReturnAfterClarificationBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class ReturnAfterClarificationController
 */
@WebServlet("/ReturnAfterClarificationController")
public class ReturnAfterClarificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnAfterClarificationController() {
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

		if(action.equals("ReturnAfterClarificationList")){
			request.setAttribute("controllerName", "ReturnAfterClarificationController");
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
/*				System.out.println("Converted From Date"+convFromdate);
				System.out.println("Converted To Date"+convtodate);*/
				dateBean.setUserEnteredFromdate(convFromdate);
				dateBean.setUserEnteredTodate(convtodate);	
		
		
		
		
		ArrayList<ReturnAfterClarificationBean> ReturnAfterClarificationList = new ArrayList<ReturnAfterClarificationBean>(); 
		Service service =new Service();
		
			ReturnAfterClarificationList = service.ReturnAfterClarificationService();
			if (ReturnAfterClarificationList!=null & ReturnAfterClarificationList.size()!=0) {
				
			request.setAttribute("alldata", ReturnAfterClarificationList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/ReturnAfterClarification.jsp");
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of ReturnAfterClarification  Status:success");
			rd.forward(request, response);}
			else {
				logger.error("ErroMessage:data received from  ReturnAfterClarification dao is either empty or null  Status:failure " +"Exception:please check ReturnAfterClarification dao and controller");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
			}catch(Exception e){
				logger.error("ErroMessage:request not forwarded to ReturnAfterClarification jsp  Status:failure " +"Exception:" +e.getStackTrace());
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);
				}
		}
		else {
			logger.error("ErroMessage:action not received from ReturnAfterClarification jsp  Status:failure " +"Exception:please check ReturnAfterClarification controller and dao");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			rd.forward(request, response);	
			}
		}
	}


