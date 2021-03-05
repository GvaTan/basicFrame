package com.zhys.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.zhys.exception.BusinessException;
import com.zhys.exception.DefaultErrorResult;
import com.zhys.exception.handler.BaseGlobalExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @desc 统一异常处理器
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

	/** 处理400类异常 */
	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public DefaultErrorResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
		return super.handleConstraintViolationException(e, request);
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public DefaultErrorResult handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
		return super.handleConstraintViolationException(e, request);
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public DefaultErrorResult handleBindException(BindException e, HttpServletRequest request) {
		return super.handleBindException(e, request);
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public DefaultErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		return super.handleMethodArgumentNotValidException(e, request);
	}

	/** 处理自定义异常 */
	@Override
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<DefaultErrorResult> handleBusinessException(BusinessException e, HttpServletRequest request) {
		return super.handleBusinessException(e, request);
	}

	/** 处理运行时异常 */
	@Override
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public DefaultErrorResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		//TODO 可通过邮件、微信公众号等方式发送信息至开发人员、记录存档等操作
		return super.handleRuntimeException(e, request);
	}

}
