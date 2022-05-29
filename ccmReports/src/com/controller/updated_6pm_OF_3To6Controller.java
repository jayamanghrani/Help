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

import com.bean.dateBean;
import com.bean.updated_6pm_OF_3To6Bean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class updated_6pm_OF_3To6Controller
 */
@WebServlet("/updated_6pm_OF_3To6Controller")
public class updated_6pm_OF_3To6Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updated_6pm_OF_3To6Controller() {
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
		
		
		if(action.equals("updated_6pm_OF_3To6List")){
			request.setAttribute("controllerName", "updated_6pm_OF_3To6Controller");
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
				dateBean.setUserEnteredFromdate(convFromdate+" 17:35:10");
				dateBean.setUserEnteredTodate(convtodate+" 17:35:10");	
				
		ArrayList<updated_6pm_OF_3To6Bean> updated_6pm_OF_3To6List = new ArrayList<updated_6pm_OF_3To6Bean>(); 
		Service service =new Service();
		
			updated_6pm_OF_3To6List = service.updated_6pm_OF_3To6Service();
			if (updated_6pm_OF_3To6List!=null & updated_6pm_OF_3To6List.size()!=0) {
				
			request.setAttribute("alldata", updated_6pm_OF_3To6List);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/updated_6pm_OF_3To6.jsp");
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of updated_6pm_OF_3To6  Status:success");
			rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:data received from  updated_6pm_OF_3To6 dao is either empty or null  Status:failure " +"Exception:please check updated_6pm_OF_3To6 dao and controller");
				rd.forward(request, response);	
				}
			}catch(Exception e){
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:request not forwarded to updated_6pm_OF_3To6 jsp  Status:failure " +"Exception:" +e.getStackTrace());
				rd.forward(request, response);
				}
		}
		else {
			logger.error("ErroMessage:action not received from updated_6pm_OF_3To6 jsp  Status:failure " +"Exception:please check updated_6pm_OF_3To6 controller and dao");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			rd.forward(request, response);	
			}
		}
	



	}


