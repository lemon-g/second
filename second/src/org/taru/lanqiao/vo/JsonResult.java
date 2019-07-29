package org.taru.lanqiao.vo;

import java.io.Serializable;

public class JsonResult implements Serializable {
	private Object data;
	private String message;
	private String status;
	public JsonResult(String status,String message,Object data){
		this.status=status;
		this.message=message;
		this.data=data;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
