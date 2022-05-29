/**
 * 
 */
package ticketTraceWebApp.vo;

/**
 * @author 738566
 *
 */

import java.util.ArrayList;

public class TicketTraceVO
{
  private String UserId;
  private String UserName;
  private String Callid;
  private String TicketStatus;
  private String Location;
  private String Department;
  private String TicketLoggedDate;
  private String Priority;
  private String OfficeCode;
  private String ProblemDescription;
  private String Solution;
  private String TicketSolveDate;
  private ArrayList ActivityList;

  public TicketTraceVO()
  {
    $init$();
  }
  private void $init$() { this.UserId = "";
    this.UserName = "";
    this.Callid = "";
    this.TicketStatus = "";
    this.Location = "";
    this.Department = "";
    this.TicketLoggedDate = "";
    this.Priority = "";
    this.OfficeCode = "";
    this.ProblemDescription = "";
    this.Solution = "";
    this.TicketSolveDate = "";

    this.ActivityList = null; }

  public void setUserId(String userId) {
    this.UserId = userId;
  }

  public String getUserId()
  {
    return this.UserId;
  }

  public void setUserName(String userName) {
    this.UserName = userName;
  }

  public String getUserName()
  {
    return this.UserName;
  }

  public void setCallid(String callid) {
    this.Callid = callid;
  }

  public String getCallid()
  {
    return this.Callid;
  }

  public void setTicketStatus(String ticketStatus) {
    this.TicketStatus = ticketStatus;
  }

  public String getTicketStatus()
  {
    return this.TicketStatus;
  }

  public void setLocation(String location) {
    this.Location = location;
  }

  public String getLocation()
  {
    return this.Location;
  }

  public void setDepartment(String department) {
    this.Department = department;
  }

  public String getDepartment()
  {
    return this.Department;
  }

  public void setTicketLoggedDate(String ticketLoggedDate) {
    this.TicketLoggedDate = ticketLoggedDate;
  }

  public String getTicketLoggedDate()
  {
    return this.TicketLoggedDate;
  }

  public void setPriority(String priority) {
    this.Priority = priority;
  }

  public String getPriority()
  {
    return this.Priority;
  }

  public void setOfficeCode(String officeCode) {
    this.OfficeCode = officeCode;
  }

  public String getOfficeCode()
  {
    return this.OfficeCode;
  }

  public void setProblemDescription(String problemDescription) {
    this.ProblemDescription = problemDescription;
  }

  public String getProblemDescription()
  {
    return this.ProblemDescription;
  }

  public void setSolution(String solution) {
    this.Solution = solution;
  }

  public String getSolution()
  {
    return this.Solution;
  }

  public void setTicketSolveDate(String ticketSolveDate) {
    this.TicketSolveDate = ticketSolveDate;
  }

  public String getTicketSolveDate()
  {
    return this.TicketSolveDate;
  }

  public void setActivityList(ArrayList activityList) {
    this.ActivityList = activityList;
  }

  public ArrayList getActivityList()
  {
    return this.ActivityList;
  }
}