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

import com.bean.ReturnAfterClarificationBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;



/**
 * Servlet implementation class ReturnAfterClarificationControllerExcel
 */
@WebServlet("/ReturnAfterClarificationControllerExcel")
public class ReturnAfterClarificationControllerExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnAfterClarificationControllerExcel() {
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
		
		if(action.equals("ReturnAfterClarificationList")){
			request.setAttribute("controllerName", "ReturnAfterClarificationControllerExcel");
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
		
		
		
		ArrayList<ReturnAfterClarificationBean> ReturnAfterClarificationList = new ArrayList<ReturnAfterClarificationBean>(); 
		Service service =new Service();
		
			//try{
			ReturnAfterClarificationList = service.ReturnAfterClarificationService();
			if (ReturnAfterClarificationList!=null & ReturnAfterClarificationList.size()!=0) {
				
			
			request.setAttribute("alldata", ReturnAfterClarificationList);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ReturnAfterClarification.xls");
			
			FileOutputStream fileOut = new FileOutputStream("Return After Clarification.xls");	
			ReturnAfterClarificationList = service.ReturnAfterClarificationService();
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet = workbook.createSheet("excel sheet created");
			HSSFRow row;
			int rowcount = 0;
			row = sheet.createRow(rowcount);
			row.createCell(0).setCellValue("Ticket NO");
			row.createCell(1).setCellValue("Assigned On");
			row.createCell(2).setCellValue("Ticket Log Date");
			row.createCell(3).setCellValue("Problem Category");
			row.createCell(4).setCellValue("Problem Type");
			row.createCell(5).setCellValue("Problem Item");
			row.createCell(6).setCellValue("Problem Summary");
			row.createCell(7).setCellValue("Problem Description");
			row.createCell(8).setCellValue("Ticket  Logged UserID");
			row.createCell(9).setCellValue("Ticket Logged UserName");
			row.createCell(10).setCellValue("SpUser ID");
			row.createCell(11).setCellValue("Sp UserName");
			row.createCell(12).setCellValue("Role");
			row.createCell(13).setCellValue("Department");
			row.createCell(14).setCellValue("Status");
			row.createCell(15).setCellValue("User Office Code");
			row.createCell(16).setCellValue("Priority");
			row.createCell(17).setCellValue("Severity");
			row.createCell(18).setCellValue("Customer Group Name");
			row.createCell(19).setCellValue("Call Classification");
			row.createCell(20).setCellValue("Sp Call Classification");
			for (ReturnAfterClarificationBean bean : ReturnAfterClarificationList) {
				row = sheet.createRow(++rowcount);
				
			    row.createCell(0).setCellValue(bean.getTicketNO()+"");
			    row.createCell(1).setCellValue(bean.getAssignedOn()+"");
			    row.createCell(2).setCellValue(bean.getTicketLoggedDate()+"");
			    row.createCell(3).setCellValue(bean.getProblemCategory()+"");
			    row.createCell(4).setCellValue(bean.getProblemType()+"");
			    row.createCell(5).setCellValue(bean.getProblemItem()+"");
			    row.createCell(6).setCellValue(bean.getProblemSummary()+"");
			    row.createCell(7).setCellValue(bean.getProblemDescription()+"");
			    row.createCell(8).setCellValue(bean.getTicketLoggedUserID()+"");
			    row.createCell(9).setCellValue(bean.getTicketLoggedUsername()+"");
			    row.createCell(10).setCellValue(bean.getSpUserId()+"");
			    row.createCell(11).setCellValue(bean.getSpUserName()+"");
			    row.createCell(12).setCellValue(bean.getRole()+"");
			    row.createCell(13).setCellValue(bean.getDepartment()+"");
			    row.createCell(14).setCellValue(bean.getStatus()+"");
			    row.createCell(15).setCellValue(bean.getUserOfficeCode()+"");
			    row.createCell(16).setCellValue(bean.getPriority()+"");
			    row.createCell(17).setCellValue(bean.getSeverity()+"");
			    row.createCell(18).setCellValue(bean.getCustomerGroupName()+"");
			    row.createCell(19).setCellValue(bean.getCallClassification()+"");
			    row.createCell(20).setCellValue(bean.getSpCallClassification()+"");
				
			}
			

			 //try (FileOutputStream outputStream = new FileOutputStream("ReturnAfterClarificationList.xlsx")) {
			 workbook.close();
			 workbook.write(response.getOutputStream());
	         fileOut.close();
	         logger.info("InfoMessage:excel sheet generated of ReturnAfterClarification   Status:success");
		     //}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				logger.error("ErroMessage:data received from ReturnAfterClarification dao is either empty or null " +"Exception:please check ReturnAfterClarification dao and controller");
				rd.forward(request, response);	
				}
			
			
			
		/*	RequestDispatcher rd = request.getRequestDispatcher("ReturnAfterClarification.jsp");
			rd.forward(request, response);*/
			}catch(Exception e){
				logger.error("ErroMessage:request not forwarded to ReturnAfterClarification jsp " +"Exception:"+e.getStackTrace()); 
				RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
				rd.forward(request, response);
				}
		}
		else {
			logger.error("ErroMessage:action not received from ReturnAfterClarification dao " +"Exception:please check ReturnAfterClarification jsp and controller");
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
			rd.forward(request, response);	
			}
		}
	}


