package com.privateblog.config;

import java.util.UUID;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.power.common.model.CommonResult;
import com.power.common.util.DateTimeUtil;

//全局的返回数据自动转换
@RestControllerAdvice("com.privateblog.controller")
class RestResponseHandler implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
			ServerHttpResponse serverHttpResponse) {
		// 如果返回值已经是 CommonResult，则不做处理直接返回
		if (body instanceof CommonResult) {
			return body;
		}
		// 否则的话封装成 CommonResult 再返回
		CommonResult commonResult = CommonResult("200", "OK").setResult(body);
		// 如果controller层中返回的类型是String，我们还需要特殊处理下（将CommonResult对象转回String）
		if (body instanceof String) {
			// 这里我使用 FastJSON 进行转换
			return JSON.toJSONString(commonResult);
		}
		return commonResult;
	}

	private CommonResult CommonResult(String code, String message) {
		CommonResult result = new CommonResult();
		result.setCode(code);
		result.setMessage(message);
		result.setSuccess(true);
		result.setTimestamp(DateTimeUtil.nowStrTime());
		result.setTraceId(UUID.randomUUID().toString());
		return result;
	}

}