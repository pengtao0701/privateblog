package com.privateblog.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.power.common.model.CommonResult;
import com.privateblog.common.ErrorCodeEnum;

//全局的Rest异常处理
@RestControllerAdvice
public class RestExceptionHandler {

 private static final Logger LOGGER = LogManager.getLogger(RestExceptionHandler.class);

 // 处理参数验证异常
 @ExceptionHandler(value = {MethodArgumentNotValidException.class})
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public CommonResult illegalParamsExceptionHandler(MethodArgumentNotValidException ex) {
     BindingResult bindingResult = ex.getBindingResult();
     FieldError fieldError = bindingResult.getFieldError();
     LOGGER.error("request params invalid: {}", fieldError.getDefaultMessage());
     return processBindingError(fieldError);
 }

 // 处理参数转换失败异常
 @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public CommonResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex)
 {
     String error = String.format("The parameter '%s' should be of type '%s'", ex.getName(),
             ex.getRequiredType().getSimpleName());
     return CommonResult.fail("400", error);
 }

 // 处理资源找不到异常（404）
 @ExceptionHandler(value = {NoHandlerFoundException.class})
 @ResponseStatus(HttpStatus.NOT_FOUND)
 public CommonResult noHandlerFoundException(Exception ex) {
     return CommonResult.fail("404", "Resource Not Found");
 }

 // 处理不支持当前媒体类型异常（415）
 @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
 @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
 public CommonResult handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
     StringBuilder builder = new StringBuilder();
     builder.append(ex.getContentType());
     builder.append(" media type is not supported. Supported media types are ");
     ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(","));
     return CommonResult.fail("415", builder.toString());
 }

 // 处理方法不被允许异常（405）
 @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
 @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
 public CommonResult methodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
     LOGGER.error("Error code 405: {}", ex.getMessage());
     return CommonResult.fail("405", ex.getMessage());
 }

 // 处理其他异常（错误码统一为500）
 @ExceptionHandler(value = {Exception.class})
 @ResponseStatus(HttpStatus.OK)
 public CommonResult unknownException(Exception ex) {
     LOGGER.error("Error code 500：{}", ex);
     return new CommonResult("500", ex.getMessage());
 }

 // 处理参数验证异常（转换成对应的CommonResult）
 private CommonResult processBindingError(FieldError fieldError) {
     String code = fieldError.getCode();
     LOGGER.debug("validator error code: {}", code);
     switch (code) {
         case "NotEmpty":
             return CommonResult.fail(ErrorCodeEnum.PARAM_EMPTY.getCode(),
                     fieldError.getDefaultMessage());
         case "NotBlank":
             return CommonResult.fail(ErrorCodeEnum.PARAM_EMPTY.getCode(),
                     fieldError.getDefaultMessage());
         case "NotNull":
             return CommonResult.fail(ErrorCodeEnum.PARAM_EMPTY.getCode(),
                     fieldError.getDefaultMessage());
         case "Pattern":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Min":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Max":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Length":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Range":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Email":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "DecimalMin":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "DecimalMax":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Size":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Digits":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Past":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         case "Future":
             return CommonResult.fail(ErrorCodeEnum.PARAM_ERROR.getCode(),
                     fieldError.getDefaultMessage());
         default:
             return CommonResult.fail(ErrorCodeEnum.UNKNOWN_ERROR);
     }
 }
}
