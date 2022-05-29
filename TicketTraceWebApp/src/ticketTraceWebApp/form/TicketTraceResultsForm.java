/**
 * 
 */
package ticketTraceWebApp.form;

/**
 * @author 738566
 *
 */

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

public class TicketTraceResultsForm extends ActionForm
{
  private ArrayList ticketTraceResultList;
  private int counter;
  private String ticketno;
  private String ticketFrom;
  private String ticketTo;
  private String startDate;
  private String endDate;

  public TicketTraceResultsForm()
  {
    $init$();
  }
  private void $init$() { this.ticketTraceResultList = null;

    this.ticketno = "";
    this.ticketFrom = "";
    this.ticketTo = "";
    this.startDate = "";
    this.endDate = ""; }

  public void setTicketTraceResultList(ArrayList ticketTraceResultList)
  {
    this.ticketTraceResultList = ticketTraceResultList;
  }

  public ArrayList getTicketTraceResultList()
  {
    return this.ticketTraceResultList;
  }

  public void setCounter(int counter)
  {
    this.counter = counter;
  }

  public int getCounter()
  {
    return this.counter;
  }

  public void setTicketno(String ticketno) {
    this.ticketno = ticketno;
  }

  public String getTicketno()
  {
    return this.ticketno;
  }

  public void setTicketFrom(String ticketFrom) {
    this.ticketFrom = ticketFrom;
  }

  public String getTicketFrom()
  {
    return this.ticketFrom;
  }

  public void setTicketTo(String ticketTo) {
    this.ticketTo = ticketTo;
  }

  public String getTicketTo()
  {
    return this.ticketTo;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getStartDate()
  {
    return this.startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getEndDate()
  {
    return this.endDate;
  }
}