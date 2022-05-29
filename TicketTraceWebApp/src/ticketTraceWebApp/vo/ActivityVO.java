/**
 * 
 */
package ticketTraceWebApp.vo;

/**
 * @author 738566
 *
 */

public class ActivityVO
{
  private String Callid;
  private String SeqNosForActivities;
  private String PersonResponsibleName;
  private String ActivityLogDescription;
  private String ActivityLoggedDate;

  public ActivityVO()
  {
    $init$();
  }
  private void $init$() {
    this.SeqNosForActivities = "";
    this.PersonResponsibleName = "";
    this.ActivityLogDescription = "";
    this.ActivityLoggedDate = "";
  }
  public void setCallid(String callid) {
    this.Callid = callid;
  }

  public String getCallid()
  {
    return this.Callid;
  }

  public void setSeqNosForActivities(String seqNosForActivities) {
    this.SeqNosForActivities = seqNosForActivities;
  }

  public String getSeqNosForActivities()
  {
    return this.SeqNosForActivities;
  }

  public void setPersonResponsibleName(String personResponsibleName) {
    this.PersonResponsibleName = personResponsibleName;
  }

  public String getPersonResponsibleName()
  {
    return this.PersonResponsibleName;
  }

  public void setActivityLogDescription(String activityLogDescription) {
    this.ActivityLogDescription = activityLogDescription;
  }

  public String getActivityLogDescription()
  {
    return this.ActivityLogDescription;
  }

  public void setActivityLoggedDate(String activityLoggedDate) {
    this.ActivityLoggedDate = activityLoggedDate;
  }

  public String getActivityLoggedDate()
  {
    return this.ActivityLoggedDate;
  }
}