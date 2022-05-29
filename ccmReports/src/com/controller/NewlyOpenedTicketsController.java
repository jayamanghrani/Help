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

import com.bean.NewlyOpenedTicketsBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class NewlyOpenedTicketsController
 */
@WebServlet("/NewlyOpenedTicketsController")
public class NewlyOpenedTicketsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewlyOpenedTicketsController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		
		
		String action = request.getParameter("action");
		
		if(action.equals("NewlyOpenedTicketsList")){
			
			request.setAttribute("controllerName", "NewlyOpenedTicketsController");
			RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				logger.error("ErroMessage:request not forwarded to NewlyOpenedTickets jsp  Status:failure " +"Exception:"+e.getStackTrace()); 
				
			}
		}
		
		else if(action.equals("List")){
		try {
			
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
		
		
		
		ArrayList<NewlyOpenedTicketsBean> NewlyOpenedTicketsList = new ArrayList<NewlyOpenedTicketsBean>(); 
		Service service =new Service();
			NewlyOpenedTicketsList = service.NewlyOpenedTicketsService();
			if (NewlyOpenedTicketsList!=null & NewlyOpenedTicketsList.size() != 0) {
				System.out.println("sixe of AL = " +NewlyOpenedTicketsList.size());
			
			request.setAttribute("alldata", NewlyOpenedTicketsList);
			logger.info("InfoMessage:sending data to NewlyOpenedTickets jsp  Status:success");
			RequestDispatcher rd = request.getRequestDispatcher("jsp/NewlyOpenedTickets.jsp");
			rd.forward(request, response);}
			else {
				
				/*RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	*/
				try {
					response.sendRedirect("jsp/errorPage.jsp");
				} catch (IOException e1) {
					 logger.error("ErroMessage:data returnes from NewlyOpenedTicketsDao is either empty or null  Status:failure " +"Exception:check controller and dao of NewlyOpenedTickets");
				}
				}
		}catch(Exception e){
			/*RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage:request not forwarded to NewlyOpenedTickets jsp  Status:failure " +"Exception:"+e.getStackTrace()); 
				e1.getStackTrace();
			}*/
			try {
				response.sendRedirect("jsp/errorPage.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
	}
	else {
		RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage:request not forwarded to NewlyOpenedTickets jsp  Status:failure " +"Exception:" +e.getStackTrace()); 
			}	
		}
	}
}
