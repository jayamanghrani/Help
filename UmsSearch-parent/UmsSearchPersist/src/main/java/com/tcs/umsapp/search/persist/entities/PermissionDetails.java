package com.tcs.umsapp.search.persist.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ums_prmn_list_mst")
public class PermissionDetails {
	@Id
	@Column(name = "UPLM_PL_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String plID;
	@Column(name = "UPLM_PL_NAME")
    private String plName;
	@Column(name = "UPLM_PL_DESC")
    private String plDesc;	
	@Column(name = "UPLM_START_DATE")
    private Date stDate;
	@Column(name = "UPLM_END_DATE")
    private Date endDate;
	@Column(name = "UPLM_CREATED_BY")
    private String createdBY;
	@Column(name = "UPLM_CREATED_DATE")
	@Temporal(TemporalType.DATE)
    private Date createdDate;
	@Column(name = "UPLM_MODIFIED_BY")
    private String modifiedBY;
	@Column(name = "UPLM_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
    private Date modifiedDate;
	@Override
	public String toString() {
		return "PermissionDetails [plID=" + plID + ", plName=" + plName
				+ ", plDesc=" + plDesc + ", stDate=" + stDate + ", endDate="
				+ endDate + ", createdBY=" + createdBY + ", createdDate="
				+ createdDate + ", modifiedBY=" + modifiedBY
				+ ", modifiedDate=" + modifiedDate + "]";
	}
    /**
     * @return the plID
     */
    public String getPlID() {
        return plID;
    }
    /**
     * @param plID the plID to set
     */
    public void setPlID(String plID) {
        this.plID = plID;
    }
    /**
     * @return the plName
     */
    public String getPlName() {
        return plName;
    }
    /**
     * @param plName the plName to set
     */
    public void setPlName(String plName) {
        this.plName = plName;
    }
    /**
     * @return the plDesc
     */
    public String getPlDesc() {
        return plDesc;
    }
    /**
     * @param plDesc the plDesc to set
     */
    public void setPlDesc(String plDesc) {
        this.plDesc = plDesc;
    }
    /**
     * @return the stDate
     */
    public Date getStDate() {
        return stDate;
    }
    /**
     * @param stDate the stDate to set
     */
    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }
    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * @return the createdBY
     */
    public String getCreatedBY() {
        return createdBY;
    }
    /**
     * @param createdBY the createdBY to set
     */
    public void setCreatedBY(String createdBY) {
        this.createdBY = createdBY;
    }
    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * @return the modifiedBY
     */
    public String getModifiedBY() {
        return modifiedBY;
    }
    /**
     * @param modifiedBY the modifiedBY to set
     */
    public void setModifiedBY(String modifiedBY) {
        this.modifiedBY = modifiedBY;
    }
    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }
    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
	
}
