package com.controller;


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


import com.bean.aginingWithIPBean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class controller
 */
@WebServlet("/AgingwithIPcontrollerExcel")
public class AgingwithIPcontrollerExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgingwithIPcontrollerExcel() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)   {
		org.apache.log4j.Logger logger = Logger.getLogger(propertiesDao.class); 
		String action = request.getParameter("action");
		Service serve = new Service();
		if (action.equals("List")){
			ArrayList<aginingWithIPBean> AgingwithipList = new ArrayList<aginingWithIPBean>();
		try{
			
			//FileOutputStream fileOut = new FileOutputStream("aging with ip.xls");
			AgingwithipList = serve.AgingwithipList();
			if (AgingwithipList != null && AgingwithipList!=null) {
				
			/*System.out.println("AgingwithipList  " + "returned from service");*/
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=agingwithip.xlsx");
			FileOutputStream fileOut = new FileOutputStream("agingwithip.xlsx");	
			
	HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet = workbook.createSheet("Aging with IP");
			HSSFRow row;
			int rowcount = 0;
			row = sheet.createRow(rowcount);
			row.createCell(0).setCellValue("TICKET NO");
			row.createCell(1).setCellValue("TICKET LOGGED DATE ");
			row.createCell(2).setCellValue("ASSSIGNED ON");
			row.createCell(3).setCellValue("PROBLEM CATEGORY");
			row.createCell(4).setCellValue("PROBLEM TYPE");
			row.createCell(5).setCellValue("PROBLEM ITEM");
			row.createCell(6).setCellValue("PROBLEM SUMMARY");
			row.createCell(7).setCellValue("PROBLEM DESCRIPTION");
			row.createCell(8).setCellValue("TICKET LOGGED USERNAME");
			row.createCell(9).setCellValue("PERSON RESPONSIBLE USER ID");
			row.createCell(10).setCellValue("PERSON RESPONSIBLE USERNAME");
			row.createCell(11).setCellValue("ROLE");
			row.createCell(12).setCellValue("PENDING TO WIP");
			row.createCell(13).setCellValue("DEPARTMENT");
			row.createCell(14).setCellValue("STATUS");
			row.createCell(15).setCellValue("USER OFFICE CODE");
			row.createCell(16).setCellValue("PRIORITY");
			row.createCell(17).setCellValue("SEVERITY");
			row.createCell(18).setCellValue("CUSTOMER GROUP NAME");
			row.createCell(19).setCellValue("CALL CLASSIFICATION");
			row.createCell(20).setCellValue("SP CALL  CLASSIFICATION");
			row.createCell(21).setCellValue("NO OF AGING DAYS");
			
			for (aginingWithIPBean b : AgingwithipList) {
				row = sheet.createRow(++rowcount);
				
				row.createCell(0).setCellValue(b.getTicketNO() +"");
			    row.createCell(1).setCellValue(b.getTicketLogDate() +"");
			    row.createCell(2).setCellValue(b.getAssignedOn() +"");
			    row.createCell(3).setCellValue(b.getProblemCategory() +"");
			    row.createCell(4).setCellValue(b.getProblemType() +"");
			    row.createCell(5).setCellValue(b.getProblemItem() +"");
			    row.createCell(6).setCellValue(b.getProblemSummary() +"");
			    row.createCell(7).setCellValue(b.getProblemDescription() +"");
			    row.createCell(8).setCellValue(b.getTicketLoggedUserID() +"");
			    row.createCell(9).setCellValue(b.getTicketLoggedUsername()+"");
			    row.createCell(10).setCellValue(b.getPersonResponsibleUserId() +"");
			    row.createCell(11).setCellValue(b.getPersonResponsibeUserName() +"");
			    row.createCell(12).setCellValue(b.getRole() +"");
			    row.createCell(13).setCellValue(b.getPendingToWIP() +"");
			    row.createCell(14).setCellValue(b.getDepartment() +"");
			    row.createCell(15).setCellValue(b.getStatus() +"");
			    row.createCell(16).setCellValue(b.getUserOfficeCode() +"");
			    row.createCell(17).setCellValue(b.getPriority() +"");
			    row.createCell(18).setCellValue(b.getSeverity() +"");
			    row.createCell(19).setCellValue(b.getCustomerGroupName() +"");
			    row.createCell(20).setCellValue(b.getCallClassification() +"");
			    row.createCell(21).setCellValue(b.getSpCallClassification() +"");
			    row.createCell(22).setCellValue(b.getNoOfAgingDays() +"");
			}
			

			// try (FileOutputStream outputStream = new FileOutputStream("aging with ip.xlsx")) {
		         //workbook.write(fileOut);
			 workbook.close();
			 workbook.write(response.getOutputStream());
	         fileOut.close();
	         fileOut.flush();
	         logger.info("InfoMessage:Excel sheet created of aginging with ip  Status:success");
	           //}
			}
			else {
				logger.error("ErroMessage:arraylist return null from Agingwithipdao  Status:failure" +"Exception:e1.getStackTrace()");
				RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
				rd.forward(request, response);	
				}
			
			
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				logger.error("ErroMessage:servelet or I/O exception in Agingwithipexcel controller  Status:failure" +"Exception:e1.getStackTrace()");
			}
			}
	}
	else {
		RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("ErroMessage: action return nul from jsp for Agingwithip  Status:failure" +"Exception:e.getStackTrace()");
		}	
		}
	}
}
