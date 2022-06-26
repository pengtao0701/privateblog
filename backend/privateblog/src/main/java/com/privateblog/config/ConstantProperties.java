package com.privateblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class ConstantProperties {
	
	public static String origin;

	public static String sessionVcode;
	
	public static String tokenPrivateKey;
	
	public static String imgUpload;
	

	@Value("${com.privateblog.origin}")
	public void setOrigin(String origin) {
		ConstantProperties.origin = origin;
	}
	
	@Value("${com.privateblog.sessionVcode}")
	public void setSessionVcode(String sessionVcode) {
		ConstantProperties.sessionVcode = sessionVcode;
	}
	
	@Value("${com.privateblog.tokenPrivateKey}")
	public void setTokenPrivateKey(String tokenPrivateKey) {
		ConstantProperties.tokenPrivateKey = tokenPrivateKey;
	}
	
	@Value("${com.privateblog.imgUpload}")
	public void setImgUpload(String imgUpload) {
		ConstantProperties.imgUpload = imgUpload;
	}
}
