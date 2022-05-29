package com.tcs.umsrole.vo.bean;

import java.util.List;

public class LdapFileBean {
	
	String dc;
	String givenname;
	String orclactivestartdate;
	String modifytimestamp;
	List<String>ObjectClass;
	List<String>authpassword_oid;
	List<String>authpassword_orclcommonpwd;
    String userpassword;
    String createtimestamp;
    String ou;
    String uid;
    String cn;
    String orclpassword;
    String pwdchangedtime;
    String orclguid;
    String modifiersname;
    String creatorsname;
    String orclnormdn;
    String obpasswordchangeflag;
    String sn;
    String oblogintrycount;
    String orcldefaultprofilegroup;
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getGivenname() {
		return givenname;
	}
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}
	public String getOrclactivestartdate() {
		return orclactivestartdate;
	}
	public void setOrclactivestartdate(String orclactivestartdate) {
		this.orclactivestartdate = orclactivestartdate;
	}
	public String getModifytimestamp() {
		return modifytimestamp;
	}
	public void setModifytimestamp(String modifytimestamp) {
		this.modifytimestamp = modifytimestamp;
	}
	public List<String> getObjectClass() {
		return ObjectClass;
	}
	public void setObjectClass(List<String> objectClass) {
		ObjectClass = objectClass;
	}
	public List<String> getAuthpassword_oid() {

		return authpassword_oid;
	}
	public void setAuthpassword_oid(List<String> authpassword_oid) {
		this.authpassword_oid = authpassword_oid;
	}
	public List<String> getAuthpassword_orclcommonpwd() {
		return authpassword_orclcommonpwd;
	}
	public void setAuthpassword_orclcommonpwd(
			List<String> authpassword_orclcommonpwd) {
		this.authpassword_orclcommonpwd = authpassword_orclcommonpwd;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getCreatetimestamp() {
		return createtimestamp;
	}
	public void setCreatetimestamp(String createtimestamp) {
		this.createtimestamp = createtimestamp;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getOrclpassword() {
		return orclpassword;
	}
	public void setOrclpassword(String orclpassword) {
		this.orclpassword = orclpassword;
	}
	public String getPwdchangedtime() {
		return pwdchangedtime;
	}
	public void setPwdchangedtime(String pwdchangedtime) {
		this.pwdchangedtime = pwdchangedtime;
	}
	public String getOrclguid() {
		return orclguid;
	}
	public void setOrclguid(String orclguid) {
		this.orclguid = orclguid;
	}
	public String getModifiersname() {
		return modifiersname;
	}
	public void setModifiersname(String modifiersname) {
		this.modifiersname = modifiersname;
	}
	public String getCreatorsname() {
		return creatorsname;
	}
	public void setCreatorsname(String creatorsname) {
		this.creatorsname = creatorsname;
	}
	public String getOrclnormdn() {
		return orclnormdn;
	}
	public void setOrclnormdn(String orclnormdn) {
		this.orclnormdn = orclnormdn;
	}
	public String getObpasswordchangeflag() {
		return obpasswordchangeflag;
	}
	public void setObpasswordchangeflag(String obpasswordchangeflag) {
		this.obpasswordchangeflag = obpasswordchangeflag;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getOblogintrycount() {
		return oblogintrycount;
	}
	public void setOblogintrycount(String oblogintrycount) {
		this.oblogintrycount = oblogintrycount;
	}
	public String getOrcldefaultprofilegroup() {
		return orcldefaultprofilegroup;
	}
	public void setOrcldefaultprofilegroup(String orcldefaultprofilegroup) {
		this.orcldefaultprofilegroup = orcldefaultprofilegroup;
	}
	

	public LdapFileBean(String dc, String givenname,
			String orclactivestartdate, String modifytimestamp,
			List<String> objectClass, List<String> authpassword_oid,
			List<String> authpassword_orclcommonpwd, String userpassword,
			String createtimestamp, String ou, String uid, String cn,
			String twofa, String orclpassword, String pwdchangedtime,
			String orclguid, String modifiersname, String creatorsname,
			String orclnormdn, String obpasswordchangeflag, String sn,
			String oblogintrycount, String orcldefaultprofilegroup) {
		super();
		this.dc = dc;
		this.givenname = givenname;
		this.orclactivestartdate = orclactivestartdate;
		this.modifytimestamp = modifytimestamp;
		ObjectClass = objectClass;
		this.authpassword_oid = authpassword_oid;
		this.authpassword_orclcommonpwd = authpassword_orclcommonpwd;
		this.userpassword = userpassword;
		this.createtimestamp = createtimestamp;
		this.ou = ou;
		this.uid = uid;
		this.cn = cn;
		this.orclpassword = orclpassword;
		this.pwdchangedtime = pwdchangedtime;
		this.orclguid = orclguid;
		this.modifiersname = modifiersname;
		this.creatorsname = creatorsname;
		this.orclnormdn = orclnormdn;
		this.obpasswordchangeflag = obpasswordchangeflag;
		this.sn = sn;
		this.oblogintrycount = oblogintrycount;
		this.orcldefaultprofilegroup = orcldefaultprofilegroup;
	}


	public LdapFileBean() {
		super();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LdapFileBean [dc=" + dc + ", givenname=" + givenname
				+ ", orclactivestartdate=" + orclactivestartdate
				+ ", modifytimestamp=" + modifytimestamp + ", ObjectClass="
				+ ObjectClass + ", authpassword_oid=" + authpassword_oid
				+ ", authpassword_orclcommonpwd=" + authpassword_orclcommonpwd
				+ ", userpassword=" + userpassword + ", createtimestamp="
				+ createtimestamp + ", ou=" + ou + ", uid=" + uid + ", cn="
				+ cn + ", orclpassword=" + orclpassword + ", pwdchangedtime="
				+ pwdchangedtime + ", orclguid=" + orclguid
				+ ", modifiersname=" + modifiersname + ", creatorsname="
				+ creatorsname + ", orclnormdn=" + orclnormdn
				+ ", obpasswordchangeflag=" + obpasswordchangeflag + ", sn="
				+ sn + ", oblogintrycount=" + oblogintrycount
				+ ", orcldefaultprofilegroup=" + orcldefaultprofilegroup + "]";
	}
    
    
    
}
