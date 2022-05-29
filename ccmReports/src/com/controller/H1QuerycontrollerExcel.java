package com.controller;

import com.bean.H1QueryBean;
import com.dao.propertiesDao;
import com.service.Service;

import java.io.FileOutputStream;
import java.io.IOException;
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


/**
 * Servlet implementation class H1QuerycontrollerExcel
 */
@WebServlet("/H1QuerycontrollerExcel")
public class H1QuerycontrollerExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public H1QuerycontrollerExcel() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		 org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class);  
		String action = request.getParameter("action");
		ArrayList<H1QueryBean> H1QueryList = new ArrayList<H1QueryBean>(); 
		

		Service service =new Service();
		if(action.equals("H1List")){
			try{
			H1QueryList = service.fetchdetailsfromDB();
			if(H1QueryList != null){
			request.setAttribute("alldata", H1QueryList);
			
			
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=H1Query.xls");
			
			
			
			FileOutputStream fileOut = new FileOutputStream("H1Query.xls");	
			
			H1QueryList = service.fetchdetailsfromDB();
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet = workbook.createSheet("excel sheet created");
			HSSFRow row;
			int rowcount = 0;
			row = sheet.createRow(rowcount);
			row.createCell(0).setCellValue("TICKET NO");
			row.createCell(1).setCellValue("SP USERNAME");
			row.createCell(2).setCellValue("DEPARTMENT");
			for (H1QueryBean bean : H1QueryList) {
				row = sheet.createRow(++rowcount);
				
				row.createCell(0).setCellValue(bean.getTicketNo()+"");
				row.createCell(1).setCellValue(bean.getSPUsername()+"");
				row.createCell(2).setCellValue(bean.getDepartment()+"");
				
			}
			

			 //(FileOutputStream outputStream = new FileOutputStream("H1Query.xlsx")) {
				 workbook.close();
				 workbook.write(response.getOutputStream());
		         fileOut.close();
		         logger.info("InfoMessage:Excel sheet created of H1query  Status:success");
		     //}
			
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
			
			
			
			
			
			
			
			

			/*RequestDispatcher rd = request.getRequestDispatcher("H1Query.jsp");
			rd.forward(request, response);*/
		}catch(Exception e){
			logger.error("ErroMessage:request not forwarded to H1Query jsp  Status:failure " +"Exception:"+e.getStackTrace()); 
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage:request not forwarded to H1Query jsp  Status:failure " +"Exception:"+e1.getStackTrace()); 
				
			}
			}
	}
	else {
		logger.error("ErroMessage:request not forwarded to H1Query jsp  Status:failure " +"Exception:please check H1query controller and dao"); 
		RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage:request not forwarded to H1Query jsp  Status:failure " +"Exception:"+e.getStackTrace()); 
			
		}	
		}

		}	
	}

