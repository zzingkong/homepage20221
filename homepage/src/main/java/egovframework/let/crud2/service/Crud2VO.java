package egovframework.let.crud2.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

public class Crud2VO extends ComDefaultVO implements Serializable {

	//게시판 ID
	private String crudId;
	//게시물명
	private String crudSj;
	//내용
	private String crudCn;
	//사용자명
	private String userNm;
	//최초등록시점
	private java.util.Date frstRegistPnttm;
	
	public String getCrudId() {
		return crudId;
	}
	public void setCrudId(String crudId) {
		this.crudId = crudId;
	}
	public String getCrudSj() {
		return crudSj;
	}
	public void setCrudSj(String crudSj) {
		this.crudSj = crudSj;
	}
	public String getCrudCn() {
		return crudCn;
	}
	public void setCrudCn(String crudCn) {
		this.crudCn = crudCn;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public java.util.Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(java.util.Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	
	
	
}
