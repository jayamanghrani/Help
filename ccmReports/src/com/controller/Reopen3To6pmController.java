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

import com.bean.Reopen3To6pmBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class Reopen3To6pmController
 */
@WebServlet("/Reopen3To6pmController")
public class Reopen3To6pmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reopen3To6pmController() {
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
			
			Service service =new Service();
			if(action.equals("Reopen3To6pmList")){
				request.setAttribute("controllerName", "Reopen3To6pmController");
				RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					logger.error("ErroMessage:request not forwarded to Reopen3To6pm jsp  Status:failure " +"Exception:" +e.getStackTrace()); 
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
					dateBean.setUserEnteredFromdate(convFromdate + " 00:00:10");
					dateBean.setUserEnteredTodate(convtodate +" 17:35:01");	
		
		 
		 ArrayList<Reopen3To6pmBean> Reopen3To6pmList = new ArrayList<Reopen3To6pmBean>(); 
			//Service service =new Service();
			
				
				Reopen3To6pmList = service.Reopen3To6pmService();
				if (Reopen3To6pmList != null & Reopen3To6pmList.size() != 0) {
					
				
				request.setAttribute("alldata", Reopen3To6pmList);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/Reopen3To6pm.jsp");
				rd.forward(request, response);
				logger.info("InfoMessage:Data successfully received from dao and send to jsp  of Reopen3To6pm  Status:success");
				}
				else {
					logger.error("ErroMessage:data returned from dao is either empty or null of reopen3to6pm  Status:failure " +"Exception:please check dao and controller of reopen3to6"); 
					RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
					rd.forward(request, response);	
					}
				}catch(Exception e){
					RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
					try {
						rd.forward(request, response);
					} catch (ServletException | IOException e1) {
						logger.error("ErroMessage:request not forwarded to Reopen3To6pm jsp  Status:failure " +"Exception:" +e.getStackTrace()); 
						
					}
					}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:action not retrived from jsp  Status:failure " +"Exception: check jsp and controller of reopen3to6pm");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					logger.error("ErroMessage:request not forwarded to Reopen3To6pm jsp  Status:failure " +"Exception:" +e.getStackTrace()); 
					
				}	
				}
			}
	}


