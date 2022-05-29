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

import com.bean.ResolvedbyTCS_OFWeeklyBean;
import com.bean.dateBean;
import com.dao.propertiesDao;
import com.service.Service;

/**
 * Servlet implementation class ResolvedbyTCS_OFWeeklyControllerExcel
 */
@WebServlet("/ResolvedbyTCS_OFWeeklyControllerExcel")
public class ResolvedbyTCS_OFWeeklyControllerExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolvedbyTCS_OFWeeklyControllerExcel() {
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
		
		
		if(action.equals("ResolvedbyTCS_OFWeeklyList")){
			request.setAttribute("controllerName", "ResolvedbyTCS_OFWeeklyControllerExcel");
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
		
		ArrayList<ResolvedbyTCS_OFWeeklyBean> ResolvedbyTCS_OFWeeklyList = new ArrayList<ResolvedbyTCS_OFWeeklyBean>(); 
		Service service =new Service();
		
			
			ResolvedbyTCS_OFWeeklyList = service.ResolvedbyTCS_OFWeeklyService();
			request.setAttribute("alldata", ResolvedbyTCS_OFWeeklyList);
			
			
			
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ResolvedbyTCS_OFWeekly.xls");
			
			
			
			FileOutputStream fileOut = new FileOutputStream("ResolvedbyTCS_OFWeekly.xls");	
			
			ResolvedbyTCS_OFWeeklyList = service.ResolvedbyTCS_OFWeeklyService();
			if (ResolvedbyTCS_OFWeeklyList!=null & ResolvedbyTCS_OFWeeklyList.size()!=0) {
				
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet = workbook.createSheet("excel sheet created");
			HSSFRow row;
			int rowcount = 0;
			row = sheet.createRow(rowcount);
			row.createCell(0).setCellValue("Ticket NO");
			row.createCell(1).setCellValue("Solved Date");
			row.createCell(2).setCellValue("Assigned On");
			row.createCell(3).setCellValue("Ticket Log Date");
			row.createCell(4).setCellValue("Problem Category");
			row.createCell(5).setCellValue("Problem Type");
			row.createCell(6).setCellValue("Problem Item");
			row.createCell(7).setCellValue("Problem Summary");
			row.createCell(8).setCellValue("Problem Description");
			row.createCell(9).setCellValue("Ticket Logged UserID");
			row.createCell(10).setCellValue("Ticket Logged Username");
			row.createCell(11).setCellValue("Sp UserId");
			row.createCell(12).setCellValue("Sp UserName");
			row.createCell(13).setCellValue("Role");
			row.createCell(14).setCellValue("Department");
			row.createCell(15).setCellValue("Status");
			row.createCell(16).setCellValue("User Office Code");
			row.createCell(17).setCellValue("Priority");
			row.createCell(18).setCellValue("Severity");
			row.createCell(19).setCellValue("Customer Group Name");
			row.createCell(20).setCellValue("Call Classification");
			row.createCell(21).setCellValue("Sp Call Classification");
			
			for (ResolvedbyTCS_OFWeeklyBean bean : ResolvedbyTCS_OFWeeklyList) {
				row = sheet.createRow(++rowcount);
				
			    row.createCell(0).setCellValue(bean.getTicketNO()+"");
			    row.createCell(1).setCellValue(bean.getSolvedDate()+"");
			    row.createCell(2).setCellValue(bean.getAssignedDate()+"");
			    row.createCell(3).setCellValue(bean.getTicketLogDate()+"");
			    row.createCell(4).setCellValue(bean.getClosedDate()+"");
			    row.createCell(5).setCellValue(bean.getProblemCategory()+"");
			    row.createCell(6).setCellValue(bean.getProblemType()+"");
			    row.createCell(7).setCellValue(bean.getProblemItem()+"");
			    row.createCell(8).setCellValue(bean.getProblemSummary()+"");
			    row.createCell(9).setCellValue(bean.getProblemDescription()+"");
			    row.createCell(10).setCellValue(bean.getTicketLoggedUserID()+"");
			    row.createCell(11).setCellValue(bean.getTicketLoggedUsername()+"");
			    row.createCell(12).setCellValue(bean.getSpUserId()+"");
			    row.createCell(13).setCellValue(bean.getSpUserName()+"");
			    row.createCell(14).setCellValue(bean.getRole()+"");
			    row.createCell(15).setCellValue(bean.getDepartment()+"");
			    row.createCell(16).setCellValue(bean.getStatus()+"");
			    row.createCell(17).setCellValue(bean.getUserOfficeCode()+"");
			    row.createCell(18).setCellValue(bean.getPriority()+"");
			    row.createCell(19).setCellValue(bean.getSeverity()+"");
			    row.createCell(20).setCellValue(bean.getCustomerGroupName()+"");
			    row.createCell(21).setCellValue(bean.getCallClassification()+"");
			    row.createCell(22).setCellValue(bean.getSpCallClassification()+"");
			    
			
			}
			
			workbook.close();
			workbook.write(response.getOutputStream());
	         fileOut.close();
	         logger.info("InfoMessage:excel sheet generated of ResolvedbyTCS_OFWeekly   Status:success");
			}
			else {
				logger.error("ErroMessage:data received from ResolvedbyTCS_OFWeekly dao is either empty or null " +"Exception:please check ResolvedbyTCS_OFWeekly dao and controller");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/errorPage.jsp");
				rd.forward(request, response);	
				}
		/*	RequestDispatcher rd = request.getRequestDispatcher("ResolvedbyTCS_OFWeekly.jsp");
			rd.forward(request, response);*/
			}catch(Exception e){
			logger.error("ErroMessage:request not forwarded to ResolvedbyTCS_OFWeekly jsp " +"Exception:"+e.getStackTrace()); 	
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
			rd.forward(request, response);
			}
			}
	else {
		logger.error("ErroMessage:action not received from ResolvedbyTCS_OFWeekly dao " +"Exception:please check ResolvedbyTCS_OFWeekly jsp and controller");
		RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
		rd.forward(request, response);	
		}
	}
}
