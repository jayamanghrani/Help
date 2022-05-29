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
import com.bean.updated3pmQueryBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class updated3pmQueryController
 */
@WebServlet("/updated3pmQueryController")
public class updated3pmQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updated3pmQueryController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  

		if(action.equals("updated3pmQueryList")){
			request.setAttribute("controllerName", "updated3pmQueryController");
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
				dateBean.setUserEnteredFromdate(convFromdate+" 15:00:00");
				dateBean.setUserEnteredTodate(convtodate+" 15:00:00");	
		
		
		
		
		
		ArrayList<updated3pmQueryBean> updated3pmQueryList = new ArrayList<updated3pmQueryBean>(); 
		Service service =new Service();
		
			updated3pmQueryList = service.updated3pmQueryService();
			if (updated3pmQueryList!=null & updated3pmQueryList.size()!=0) {
			request.setAttribute("alldata", updated3pmQueryList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/updated3pmQuery.jsp");
			logger.info("InfoMessage:data successfullly return from dao and send to jsp of updated3pmQuery  Status:success");
			rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:data received from  updated3pmQuery dao is either empty or null  Status:failure " +"Exception:please check updated3pmQuery dao and controller");
				rd.forward(request, response);	
				}
			}catch(Exception e){
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:request not forwarded to updated3pmQuery jsp  Status:failure " +"Exception:" +e.getStackTrace());
				rd.forward(request, response);
				}
		}
		else {
			logger.error("ErroMessage:action not received from updated3pmQuery jsp  Status:failure " +"Exception:please check updated3pmQuery controller and dao");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			rd.forward(request, response);	
			}
		}
	}


