package com.privateblog.common;

import com.power.common.interfaces.IMessage;

public enum ErrorCodeEnum implements IMessage {

	TOKEN_ERROR("500", "Token过期或失效，请重新登录。"), 
	LOGIN_ERROR("500", "用户名或密码不可为空"), 
	USER_ISEXIST_ERROR("500", "用户名已经存在"), 
	MAIL_ISEXIST_ERROR("500", "邮箱已经被注册过了"), 
	SIGNIN_INSERT_ERROR("500", "用户注册失败"), 
	SIGNIN_FORM_ERROR("500", "注册表单不可为空！"), 
	MAIL_ERROR("500", "邮箱格式不正确"), 
	VCODE_ERROR("500", "请输入正确的验证码"), 
	USER_Not_Found_ERROR("500", "未找到该用户"),
	REQUEST_ERROR("500", "请求失败请稍后再试"), 
	BlogSave_ERROR("500", "博客保存失败"), 
	IMAGES_UPLOAD_ERROR("500", "上传图片失败"), 
	
	PARAM_EMPTY("500", "必选参数为空"),
	PARAM_ERROR("500", "参数格式错误"),
	UNKNOWN_ERROR("9999", "系统繁忙，请稍后再试....");

	private String code;
	private String message;

	ErrorCodeEnum(String errCode, String errMsg) {
		this.code = errCode;
		this.message = errMsg;
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