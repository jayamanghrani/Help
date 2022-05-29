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

import com.bean.Reopen10To3pmBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class Reopen10To3pmController
 */
@WebServlet("/Reopen10To3pmController")
public class Reopen10To3pmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reopen10To3pmController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		String action = request.getParameter("action");
		Service service =new Service();
		if(action.equals("Reopen10To3pmList")){
			request.setAttribute("controllerName", "Reopen10To3pmController");
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
				dateBean.setUserEnteredFromdate(convFromdate + " 00:00:10");
				dateBean.setUserEnteredTodate(convtodate +" 15:00:01");
				
		
		
		ArrayList<Reopen10To3pmBean> Reopen10To3pmList = new ArrayList<Reopen10To3pmBean>(); 
			
			
			Reopen10To3pmList = service.Reopen10To3pmService();
			if (Reopen10To3pmList!=null & Reopen10To3pmList.size() != 0) {
				
			
			request.setAttribute("alldata", Reopen10To3pmList);
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of Reopen10To3pm  Status:success");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/Reopen10To3pm.jsp");
			rd.forward(request, response);}
			else {
				logger.error("ErroMessage:dasta returned from  Reopen10To3pmDao is either empty or null  Status:failure " +"Exception:please check controller and dao of Reopen10To3pm");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
			}catch(Exception e){
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					logger.error("ErroMessage:request not forwarded to Reopen10To3pm jsp  Status:failure " +"Exception:" +e1.getStackTrace());
					}
				}
		
			}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				logger.error("ErroMessage:request not forwarded to Reopen10To3pm jsp  Status:failure " +"Exception:" +e.getStackTrace());
				}	
			}
		}
	}


