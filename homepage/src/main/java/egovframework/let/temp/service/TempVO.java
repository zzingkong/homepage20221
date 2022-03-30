package egovframework.let.temp.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TempVO implements Serializable {

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
