package egovframework.let.temp2.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

public class Temp2VO extends ComDefaultVO implements Serializable {

	//임시 데이터ID
	private String tempId;
	
	//임시데이터 값  //자바에서는 getset이라고 부른다
	private String tempVal;

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getTempVal() {
		return tempVal;
	}

	public void setTempVal(String tempVal) {
		this.tempVal = tempVal;
	}
}
