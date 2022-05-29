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

import com.bean.NewlyOpenedHOBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;


/**
 * Servlet implementation class NewlyOpenedHOController
 */
@WebServlet("/NewlyOpenedHOController")
public class NewlyOpenedHOController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewlyOpenedHOController() {
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
		ArrayList<NewlyOpenedHOBean> NewlyOpenedHOList = new ArrayList<NewlyOpenedHOBean>(); 
		Service service =new Service();
		if(action.equals("NewlyOpenedHOList")){
			request.setAttribute("controllerName", "NewlyOpenedHOController");
			RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
			try {
				rd.forward(request, response);
				 logger.info("InfoMessage:sending data to NewlyOpenedHO jsp  Status:success");
			} catch (ServletException | IOException e) {
				logger.error("ErroMessage:servlet or I/O exception please check NewlyOpenHoController  " +"Exception:"+e.getStackTrace()); 
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
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String convFromdate = format.format(frmdt);
				String convtodate = format.format(todt);
				/*System.out.println("Converted From Date"+convFromdate);
				System.out.println("Converted To Date"+convtodate);
*/				
				dateBean.setUserEnteredFromdate(convFromdate);
				dateBean.setUserEnteredTodate(convtodate);
								
		
			NewlyOpenedHOList = service.NewlyOpenedHOService();
			if (NewlyOpenedHOList != null & NewlyOpenedHOList.size()!=0) {
			
			request.setAttribute("alldata", NewlyOpenedHOList);
			RequestDispatcher rd = request.getRequestDispatcher("jsp/NewlyOpenedHO.jsp");
			rd.forward(request, response);
			}
			
			else{
				logger.error("ErroMessage:data received from NewlyOpenHodao is either null or empty  Status:failure " +"Exception:check NewlyOpenDao and controller");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);
			}
			
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage:servlet or I/O exception please check NewlyOpenHoController  Status:failure " +"Exception:"+e1.getStackTrace());
				
			}
			}
		}
	
	}
}
