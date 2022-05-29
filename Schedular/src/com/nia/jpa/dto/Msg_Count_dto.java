package com.nia.jpa.dto;

import java.io.Serializable;

public class Msg_Count_dto implements Serializable {
 protected String msgType;
 protected String count;
 protected String date;
 protected String status;
 
public String getMsgType() {
	return msgType;
}
public void setMsgType(String msgType) {
	this.msgType = msgType;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
