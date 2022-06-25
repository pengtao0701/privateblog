package com.privateblog.common;

import com.power.common.interfaces.IMessage;

public enum SuccessCodeEnum implements IMessage {
	
	PARAM_EMPTY("200", "OK");
	
	
	private String code;
	private String message;
	
	SuccessCodeEnum(String successCode, String successMsg) {
		this.code = successCode;
		this.message = successMsg;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
