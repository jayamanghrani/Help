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

import com.bean.SentForClarificationBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class SentForClarificationController
 */
@WebServlet("/SentForClarificationController")
public class SentForClarificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SentForClarificationController() {
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


		if(action.equals("SentForClarificationList")){
			request.setAttribute("controllerName", "SentForClarificationController");
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
		
		
		
		
		
		ArrayList<SentForClarificationBean> SentForClarificationList = new ArrayList<SentForClarificationBean>(); 
		Service service =new Service();
			SentForClarificationList = service.SentForClarificationService();
			if (SentForClarificationList!=null & SentForClarificationList.size()!=0) {
				
			request.setAttribute("alldata", SentForClarificationList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/SentForClarification.jsp");
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of SentForClarification  Status:success");
			rd.forward(request, response);}
			else {
				logger.error("ErroMessage:data received from  SentForClarification dao is either empty or null  Status:failure " +"Exception:please check SentForClarification dao and controller");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
			}catch(Exception e){
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:request not forwarded to SentForClarification jsp  Status:failure " +"Exception:" +e.getStackTrace());
				rd.forward(request, response);
				}
		}
		else {
			logger.error("ErroMessage:action not received from SentForClarification jsp  Status:failure " +"Exception:please check SentForClarification controller and dao");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			rd.forward(request, response);	
			}
		}
	}


