package com.ecommerce.admin.model;

public class ResponseBean {
	
	private Integer status;
	private Object result;
	public ResponseBean(Integer status, Object result) {
		super();
		this.status = status;
		this.result = result;
	}
	public ResponseBean() {
		// TODO Auto-generated constructor stub
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResponseBean [status=" + status + ", result=" + result + "]";
	}
	
	

}
