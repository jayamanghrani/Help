/**
 * 
 */
package reports.action;

/**
 * @author 738566
 *
 */

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import ticketTraceWebApp.form.TicketTraceResultsForm;
import ticketTraceWebApp.vo.ActivityVO;
import ticketTraceWebApp.vo.TicketTraceVO;

public class TicketTrace extends DispatchAction
{
  public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    System.out.println(" - Action - TicketTrace - unspecified ::: ");
    HttpSession session = request.getSession();
    TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;
    ticketTraceResultsForm.setTicketno("");
    ticketTraceResultsForm.setTicketFrom("");
    ticketTraceResultsForm.setTicketTo("");
    ticketTraceResultsForm.setStartDate("");
    ticketTraceResultsForm.setEndDate("");
    ticketTraceResultsForm.setTicketTraceResultList(null);
    session.setAttribute("DISPLAYRESULTS", "DONOTDISPLAYRESULTS");
    session.setAttribute("DISPLAYLINKS", "DONOTDISPLAYLINKS");

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward getTicketTraceReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    System.out.println(" - Action - getTicketTraceReport - unspecified ::: ");
    ArrayList ticketTraceReport = new ArrayList();
    try
    {
      HttpSession session = request.getSession();
      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      String ticketNo = null;
      String ticketFrom = null;
      String ticketTo = null;
      String fromDate = null;
      String toDate = null;
      int intChkRadio = 0;

      ticketNo = ticketTraceResultsForm.getTicketno();
      ticketFrom = ticketTraceResultsForm.getTicketFrom();
      ticketTo = ticketTraceResultsForm.getTicketTo();
      fromDate = ticketTraceResultsForm.getStartDate();
      toDate = ticketTraceResultsForm.getEndDate();

      if (ticketNo != "") intChkRadio = 0;
      if ((ticketFrom != "") && (ticketTo != "")) intChkRadio = 1;
      if ((fromDate != "") && (toDate != "")) intChkRadio = 2;
      System.out.println("intChkRadio is" + intChkRadio);
      session.setAttribute("RADIO", Integer.valueOf(intChkRadio));

      System.out.println("ticketno : " + ticketNo + "ticketFrom : " + ticketFrom + "ticketTo : " + ticketTo + "fromDate :" + fromDate + "toDate :" + toDate);

      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@ccmnet02:1521:niaccmd", "nia_ccm", "ccm");
      OracleCallableStatement oraCallStmt = null;
      OracleCallableStatement oraCallStmtActivity = null;
      OracleResultSet deptResultSet = null;
      OracleResultSet deptResultSetActivity = null;
      String a = null;

      System.out.println("Using OracleCallableStatement / OracleResultSet");
      oraCallStmt = (OracleCallableStatement)conn.prepareCall("{call SPR_TICKETTRACE(?,?,?,?,?,?)}");
      oraCallStmt.registerOutParameter(6, -10);
      oraCallStmt.setString(1, ticketNo);
      oraCallStmt.setString(2, fromDate);
      oraCallStmt.setString(3, toDate);
      oraCallStmt.setString(4, ticketFrom);
      oraCallStmt.setString(5, ticketTo);
      oraCallStmt.execute();

      deptResultSet = (OracleResultSet)oraCallStmt.getCursor(6);
      while (deptResultSet.next()) {
        TicketTraceVO ttVO = new TicketTraceVO();
        ttVO.setUserId(deptResultSet.getString(1));
        ttVO.setUserName(deptResultSet.getString(2));
        ttVO.setCallid(deptResultSet.getString(3));
        ttVO.setTicketStatus(deptResultSet.getString(4));
        ttVO.setLocation(deptResultSet.getString(5));
        ttVO.setDepartment(deptResultSet.getString(6));
        ttVO.setTicketLoggedDate(deptResultSet.getString(7));
        ttVO.setPriority(deptResultSet.getString(8));
        ttVO.setOfficeCode(deptResultSet.getString(9));
        ttVO.setProblemDescription(deptResultSet.getString(10));
        ttVO.setSolution(deptResultSet.getString(11));
        ttVO.setTicketSolveDate(deptResultSet.getString(12));

        String strProblemDescription = ttVO.getProblemDescription();
        strProblemDescription = strProblemDescription.replaceAll("&amp;", "&");
        strProblemDescription = strProblemDescription.replaceAll("&quot;", "'");
        strProblemDescription = strProblemDescription.replaceAll("&lt;", "<");
        strProblemDescription = strProblemDescription.replaceAll("&gt;", ">");
        ttVO.setProblemDescription(strProblemDescription);

        String strTicketLoggedDate = ttVO.getTicketLoggedDate();
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-mm-yyyy h:mm a");
        if (strTicketLoggedDate != null) {
          System.out.println("strTicketLoggedDate###" + strTicketLoggedDate);
          Date date = sdfSource.parse(strTicketLoggedDate);
          strTicketLoggedDate = sdfDestination.format(date);
          ttVO.setTicketLoggedDate(strTicketLoggedDate);
          System.out.println("strTicketLoggedDate**" + strTicketLoggedDate);
        }
        String strTicketSolveDate = ttVO.getTicketSolveDate();
        if (strTicketSolveDate != null) {
          Date date1 = sdfSource.parse(strTicketSolveDate);
          strTicketSolveDate = sdfDestination.format(date1);
          ttVO.setTicketSolveDate(strTicketSolveDate);
        }

        oraCallStmtActivity = (OracleCallableStatement)conn.prepareCall("{call SPR_TICKETTRACE_ACTIVITY(?,?,?,?,?,?)}");
        oraCallStmtActivity.registerOutParameter(6, -10);

        oraCallStmtActivity.setString(1, ttVO.getCallid());
        oraCallStmtActivity.setString(2, a);
        oraCallStmtActivity.setString(3, a);
        oraCallStmtActivity.setString(4, a);
        oraCallStmtActivity.setString(5, a);

        oraCallStmtActivity.execute();

        deptResultSetActivity = (OracleResultSet)oraCallStmtActivity.getCursor(6);
        ArrayList ticketActivityDetails = new ArrayList();

        while (deptResultSetActivity.next()) {
          ActivityVO activityVO = new ActivityVO();
          activityVO.setCallid(deptResultSetActivity.getString(1));
          activityVO.setSeqNosForActivities(deptResultSetActivity.getString(2));
          activityVO.setPersonResponsibleName(deptResultSetActivity.getString(3));
          activityVO.setActivityLogDescription(deptResultSetActivity.getString(4));
          activityVO.setActivityLoggedDate(deptResultSetActivity.getString(5));
          ticketActivityDetails.add(activityVO);
        }

        ttVO.setActivityList(ticketActivityDetails);
        ticketTraceReport.add(ttVO);
        deptResultSetActivity.close();
      }
      ticketTraceResultsForm.setTicketTraceResultList(ticketTraceReport);
      ticketTraceResultsForm.setCounter(1);
      deptResultSet.close();
      conn.close();
      System.out.println("SIZE OF RESULT LIST IS :_" + ticketTraceResultsForm.getTicketTraceResultList().size());

      session.setAttribute("ticketTraceReport", ticketTraceReport);
      session.setAttribute("DISPLAYRESULTS", "DISPLAYRESULTS");

      if ((ticketTraceResultsForm.getTicketTraceResultList() != null) && 
        (ticketTraceResultsForm.getTicketTraceResultList().size() != 0)) {
        session.setAttribute("DISPLAYLINKS", "DISPLAYLINKS");
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward getFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      ticketTraceResultsForm.setCounter(1);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward getPreviousPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      int pageCounter = ticketTraceResultsForm.getCounter();
      if (pageCounter > 1)
        ticketTraceResultsForm.setCounter(pageCounter - 1);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward getNextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    try
    {
      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      int pageCounter = ticketTraceResultsForm.getCounter();
      int maxSize = ticketTraceResultsForm.getTicketTraceResultList().size();

      if (pageCounter < maxSize)
        ticketTraceResultsForm.setCounter(pageCounter + 1);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward getLastPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    try
    {
      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      int size = ticketTraceResultsForm.getTicketTraceResultList().size();

      int pages = size;
      ticketTraceResultsForm.setCounter(pages);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward generateexportpdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    try {
      System.out.println("in generateexportpdf >>>>>>>");
      HttpSession session = request.getSession();
      ArrayList ticketTraceReport = new ArrayList();

      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      ticketTraceReport = ticketTraceResultsForm.getTicketTraceResultList();
      System.out.println("ticketTraceReport size is >>>>>>>>" + ticketTraceReport.size());

      response.setContentType("application/pdf");
      response.setHeader("Content-disposition", "attachment ;filename=TicketTrace.pdf");

      String DATE_FORMAT_NOW = "MM/dd/yyyy HH:mm:ss";
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
      String systemTime = sdf.format(cal.getTime());
      DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Date today = df.parse(systemTime);
      System.out.println("todays date is " + today);

      Document document = new Document();
      ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();

      PdfWriter docWriter = null;

      docWriter = PdfWriter.getInstance(document, baosPDF);

      Font small = new Font(2, 12.0F);
      Font smallUnderline = new Font(2, 12.0F, 4);
      Font smallBold = new Font(2, 12.0F, 1);
      smallBold.setStyle("underline");
      Font bigBold = new Font(2, 16.0F, 1);
      document.open();
      System.out.println("aftetr doc open");

      int intTicketTraceReportSize = ticketTraceReport.size();
      if (ticketTraceReport != null) {
        for (int j = 0; j < intTicketTraceReportSize; j++)
        {
          TicketTraceVO ticketTraceVO = (TicketTraceVO)ticketTraceReport.get(j);
          if (ticketTraceVO != null) {
            String location = ticketTraceVO.getLocation();
            String callid = ticketTraceVO.getCallid();
            String department = ticketTraceVO.getDepartment();
            String ticketStatus = ticketTraceVO.getTicketStatus();
            String ticketLoggedDate = ticketTraceVO.getTicketLoggedDate();
            String priority = ticketTraceVO.getPriority();
            String officeCode = ticketTraceVO.getOfficeCode();
            String userId = ticketTraceVO.getUserId();
            String userName = ticketTraceVO.getUserName();
            String problemDescription = ticketTraceVO.getProblemDescription();

            document.add(new Paragraph("Ticket Trace Report-" + callid, bigBold));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Company Name : NIA", smallUnderline));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Location : " + location, smallUnderline));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Call Description: ", smallBold));
            document.add(new Paragraph(" "));
            float[] widths = { 0.15F, 0.25F, 0.15F, 0.4F, 0.15F, 0.25F };

            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(99.0F);
            table.getDefaultCell().setBorder(0);
            table.getDefaultCell().setHorizontalAlignment(0);
            table.setHorizontalAlignment(0);

            table.addCell(new Paragraph("Call id", smallUnderline));
            table.addCell(new Paragraph("Department", smallUnderline));
            table.addCell(new Paragraph("Status", smallUnderline));
            table.addCell(new Paragraph("Ticket Log Date", smallUnderline));

            table.addCell(new Paragraph("Priority", smallUnderline));
            table.addCell(new Paragraph("OfficeCode", smallUnderline));
            table.addCell(new Paragraph(callid, small));
            table.addCell(new Paragraph(department, small));
            table.addCell(new Paragraph(ticketStatus, small));
            table.addCell(new Paragraph(ticketLoggedDate, small));
            table.addCell(new Paragraph(priority, small));
            table.addCell(new Paragraph(officeCode, small));
            document.add(table);

            PdfPTable table1 = new PdfPTable(2);
            table1.getDefaultCell().setBorder(0);
            table1.getDefaultCell().setHorizontalAlignment(0);
            table1.setHorizontalAlignment(0);
            table1.addCell(new Paragraph("Ticket Logged By-User id", smallUnderline));
            table1.addCell(new Paragraph("Ticket Logged By-User Name", smallUnderline));
            table1.addCell(new Paragraph(userId, small));
            table1.addCell(new Paragraph(userName, small));
            document.add(table1);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Problem Description: ", smallBold));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(problemDescription, small));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Activity Description: ", smallBold));
            document.add(new Paragraph(" "));

            float[] widthsActivity = { 0.15F, 0.25F, 0.35F, 0.6F };

            PdfPTable activityTable = new PdfPTable(widthsActivity);
            activityTable.setWidthPercentage(90.0F);
            activityTable.getDefaultCell().setBorder(0);
            activityTable.getDefaultCell().setHorizontalAlignment(0);
            activityTable.setHorizontalAlignment(0);

            activityTable.addCell(new Paragraph("Seq No:", smallUnderline));
            activityTable.addCell(new Paragraph("Activity Date", smallUnderline));
            activityTable.addCell(new Paragraph("Person responsible", smallUnderline));
            activityTable.addCell(new Paragraph("Activity log description", smallUnderline));

            ArrayList activityList = new ArrayList();
            activityList = ticketTraceVO.getActivityList();
            int sizeList = activityList.size();
            if (activityList != null) {
              for (int k = 0; k < sizeList; k++) {
                ActivityVO activityVO = (ActivityVO)activityList.get(k);
                String seqNosForActivities = activityVO.getSeqNosForActivities();
                String activityLoggedDate = activityVO.getActivityLoggedDate();
                String personResponsibleName = activityVO.getPersonResponsibleName();
                String activityLogDescription = activityVO.getActivityLogDescription();

                activityTable.addCell(new Paragraph(seqNosForActivities, small));
                activityTable.addCell(new Paragraph(activityLoggedDate, small));
                activityTable.addCell(new Paragraph(personResponsibleName, small));
                activityTable.addCell(new Paragraph(activityLogDescription, small));
              }

            }

            document.add(activityTable);
            String ticketSolveDate = ticketTraceVO.getTicketSolveDate();
            String solution = ticketTraceVO.getSolution();
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Ticket solved Date: ", smallBold));
            document.add(new Paragraph(ticketSolveDate, small));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Solution: ", smallBold));
            document.add(new Paragraph(solution, small));
            document.add(new Paragraph(" "));

            document.newPage();
          }
        }
      }
      document.close();
      docWriter.close();

      response.setContentLength(baosPDF.size());

      ServletOutputStream sos = response.getOutputStream();
      baosPDF.writeTo(sos);
      sos.flush();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward generateexportWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    try
    {
      String DATE_FORMAT_NOW = "MM/dd/yyyy HH:mm:ss";
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
      String systemTime = sdf.format(cal.getTime());
      DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Date today = df.parse(systemTime);
      System.out.println("todays date is " + today);

      response.setContentType("application/msword");
      response.setHeader("Content-disposition", "attachment ;filename=TicketTrace" + today + ".doc");

      ArrayList ticketTraceReport = new ArrayList();
      TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;

      ticketTraceReport = ticketTraceResultsForm.getTicketTraceResultList();
      System.out.println("ticketTraceReport size is >>>>>>>>" + ticketTraceReport.size());
      Document document = new Document();
      ByteArrayOutputStream baosWord = new ByteArrayOutputStream();

      RtfWriter docWriter = null;
      docWriter = RtfWriter.getInstance(document, baosWord);
      Font small = new Font(2, 12.0F);
      Font smallUnderline = new Font(2, 12.0F, 4);
      Font smallBold = new Font(2, 12.0F, 1);
      smallBold.setStyle("underline");
      Font bigBold = new Font(2, 16.0F, 1);
      document.open();
      int intTicketTraceReportSize = ticketTraceReport.size();
      if (ticketTraceReport != null) {
        for (int j = 0; j < intTicketTraceReportSize; j++)
        {
          TicketTraceVO ticketTraceVO = (TicketTraceVO)ticketTraceReport.get(j);
          if (ticketTraceVO != null) {
            String location = ticketTraceVO.getLocation();
            String callid = ticketTraceVO.getCallid();
            String department = ticketTraceVO.getDepartment();
            String ticketStatus = ticketTraceVO.getTicketStatus();
            String ticketLoggedDate = ticketTraceVO.getTicketLoggedDate();
            String priority = ticketTraceVO.getPriority();
            String officeCode = ticketTraceVO.getOfficeCode();
            String userId = ticketTraceVO.getUserId();
            String userName = ticketTraceVO.getUserName();
            String problemDescription = ticketTraceVO.getProblemDescription();

            document.add(new Paragraph("Ticket Trace Report-" + callid, bigBold));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Company Name : NIA", smallUnderline));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Location : " + location, smallUnderline));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Call Description: ", smallBold));
            document.add(new Paragraph(" "));
            float[] widths = { 0.15F, 0.25F, 0.15F, 0.4F, 0.15F, 0.25F };

            Table table = new Table(6, 2);
            table.setDefaultCellBorder(0);
            table.setDefaultHorizontalAlignment(0);
            table.setWidth(90.0F);
            table.setWidths(widths);

            table.addCell(new Paragraph("Call id", smallUnderline));
            table.addCell(new Paragraph("Department", smallUnderline));
            table.addCell(new Paragraph("Status", smallUnderline));
            table.addCell(new Paragraph("Ticket Log Date", smallUnderline));

            table.addCell(new Paragraph("Priority", smallUnderline));
            table.addCell(new Paragraph("OfficeCode", smallUnderline));
            table.addCell(new Paragraph(callid, small));
            table.addCell(new Paragraph(department, small));
            table.addCell(new Paragraph(ticketStatus, small));

            table.addCell(new Paragraph(ticketLoggedDate, small));
            table.addCell(new Paragraph(priority, small));
            table.addCell(new Paragraph(officeCode, small));
            document.add(table);

            float[] widths1 = { 0.3F, 0.7F };
            Table table1 = new Table(2, 2);
            table1.setDefaultCellBorder(0);
            table1.setDefaultHorizontalAlignment(0);
            table1.setWidth(90.0F);
            table1.setWidths(widths1);

            table1.addCell(new Paragraph("Ticket Logged By-User id", smallUnderline));
            table1.addCell(new Paragraph("Ticket Logged By-User Name", smallUnderline));
            table1.addCell(new Paragraph(userId, small));
            table1.addCell(new Paragraph(userName, small));
            document.add(table1);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Problem Description: ", smallBold));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(problemDescription, small));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Activity Description: ", smallBold));
            document.add(new Paragraph(" "));

            float[] widthsActivity = { 0.1F, 0.25F, 0.35F, 0.6F };

            Table activityTable = new Table(4);
            activityTable.setDefaultCellBorder(0);
            activityTable.setDefaultHorizontalAlignment(0);
            activityTable.setWidth(90.0F);
            activityTable.setWidths(widthsActivity);

            activityTable.addCell(new Paragraph("Seq No:", smallUnderline));
            activityTable.addCell(new Paragraph("Activity Date", smallUnderline));
            activityTable.addCell(new Paragraph("Person responsible", smallUnderline));
            activityTable.addCell(new Paragraph("Activity log description", smallUnderline));

            ArrayList activityList = new ArrayList();
            activityList = ticketTraceVO.getActivityList();
            int intActivityList = activityList.size();
            if (activityList != null) {
              for (int k = 0; k < intActivityList; k++) {
                ActivityVO activityVO = (ActivityVO)activityList.get(k);
                String seqNosForActivities = activityVO.getSeqNosForActivities();
                String activityLoggedDate = activityVO.getActivityLoggedDate();
                String personResponsibleName = activityVO.getPersonResponsibleName();
                String activityLogDescription = activityVO.getActivityLogDescription();

                activityTable.addCell(new Paragraph(seqNosForActivities, small));
                activityTable.addCell(new Paragraph(activityLoggedDate, small));
                activityTable.addCell(new Paragraph(personResponsibleName, small));

                activityTable.addCell(new Paragraph(activityLogDescription, small));
              }

            }

            document.add(activityTable);
            String ticketSolveDate = ticketTraceVO.getTicketSolveDate();
            String solution = ticketTraceVO.getSolution();
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Ticket solved Date: ", smallBold));
            document.add(new Paragraph(ticketSolveDate, small));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Solution: ", smallBold));
            document.add(new Paragraph(solution, small));
            document.add(new Paragraph(" "));

            document.newPage();
          }

        }

      }

      document.close();
      docWriter.close();

      System.out.println("Herein export");

      response.setContentLength(baosWord.size());

      ServletOutputStream sos = response.getOutputStream();
      baosWord.writeTo(sos);
      sos.flush();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("TicketTraceSuccess");
  }

  public ActionForward reset(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    System.out.println(" - Action -  - reset ::: ");
    HttpSession session = request.getSession();
    TicketTraceResultsForm ticketTraceResultsForm = (TicketTraceResultsForm)form;
    ticketTraceResultsForm.setCounter(0);
    ticketTraceResultsForm.setEndDate("");
    ticketTraceResultsForm.setStartDate("");
    ticketTraceResultsForm.setTicketFrom("");
    ticketTraceResultsForm.setTicketno("");
    ticketTraceResultsForm.setTicketTo("");
    ticketTraceResultsForm.setTicketTraceResultList(null);
    session.setAttribute("DISPLAYRESULTS", "DONOTDISPLAYRESULTS");
    session.setAttribute("DISPLAYLINKS", "DONOTDISPLAYLINKS");

    return mapping.findForward("TicketTraceSuccess");
  }
}