package com.controller;

import java.io.FileOutputStream;
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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bean.dateBean;
import com.bean.updated_6pm_OF_3To6Bean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class updated_6pm_OF_3To6ControllerExcel
 */
@WebServlet("/updated_6pm_OF_3To6ControllerExcel")
public class updated_6pm_OF_3To6ControllerExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updated_6pm_OF_3To6ControllerExcel() {
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
		
		
		//System.out.println("in here");
		if(action.equals("updated_6pm_OF_3To6List")){
			request.setAttribute("controllerName", "updated_6pm_OF_3To6ControllerExcel");
			RequestDispatcher rd = request.getRequestDispatcher("date.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("List")){
			try{
				String fromdate =  request.getParameter("fromDate");
				String todate =  request.getParameter("toDate");
			/*	System.out.println("From Date :/ " +fromdate);
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
				dateBean.setUserEnteredTodate(convtodate+" 15:00:10");	
		
		
		
		
		
		
		
		
		
		
		
		ArrayList<updated_6pm_OF_3To6Bean> updated_6pm_OF_3To6List = new ArrayList<updated_6pm_OF_3To6Bean>(); 
		Service service =new Service();
		
			//try{
			updated_6pm_OF_3To6List = service.updated_6pm_OF_3To6Service();
			if (updated_6pm_OF_3To6List!=null & updated_6pm_OF_3To6List.size()!=0) {
				
			request.setAttribute("alldata", updated_6pm_OF_3To6List);
			
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=updated_6pm_OF_3To6.xls");
			
			
			
			FileOutputStream fileOut = new FileOutputStream("updated_6pm_OF_3To6.xls");	
			
			updated_6pm_OF_3To6List = service.updated_6pm_OF_3To6Service();
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet = workbook.createSheet("excel sheet created");
			HSSFRow row;
			int rowcount = 0;
			row = sheet.createRow(rowcount);
			row.createCell(0).setCellValue("Ticket NO");
			row.createCell(1).setCellValue("Problem Summary");
		
			
			for (updated_6pm_OF_3To6Bean bean : updated_6pm_OF_3To6List) {
				row = sheet.createRow(++rowcount);
				
			    row.createCell(0).setCellValue(bean.getTicketNo()+"");
			    row.createCell(1).setCellValue(bean.getProblemSummary()+"");
			    
			    
			
			}
			

			 //try (FileOutputStream outputStream = new FileOutputStream("updated_6pm_OF_3To6.xlsx")) {
				 workbook.close();
				 workbook.write(response.getOutputStream());
		         fileOut.close();
		         logger.info("InfoMessage:excel sheet generated of SentForClarification   Status:success");
		    // }
			}
			else {
				logger.error("ErroMessage:data received from updated_6pm_OF_3To6 dao is either empty or null " +"Exception:please check updated_6pm_OF_3To6 dao and controller");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
			
			
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("updated_6pm_OF_3To6.jsp");
			rd.forward(request, response);*/
			}catch(Exception e){
				logger.error("ErroMessage:request not forwarded to updated_6pm_OF_3To6 jsp " +"Exception:"+e.getStackTrace());
				RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
				rd.forward(request, response);
				}
		}
		else {
			logger.error("ErroMessage:action not received from updated_6pm_OF_3To6 dao " +"Exception:please check updated_6pm_OF_3To6 jsp and controller");
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
			rd.forward(request, response);	
			}
		}
	



	}


