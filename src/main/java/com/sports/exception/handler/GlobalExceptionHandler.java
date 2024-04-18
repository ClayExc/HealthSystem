package com.sports.exception.handler;

import com.sports.common.ResponseCode;
import com.sports.common.ServerResponse;
import com.sports.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常处理器 使用 @RestControllerAdvice + @ExceptionHandler 注解方式实现全局异常处理
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * 处理自定义异常
     * */
    @ExceptionHandler(CustomException.class)
    public ServerResponse handlerException(CustomException e){
        //打印异常信息
        logger.error("####异常信息:{}####",e.getMessage());
        return new ServerResponse(e.getMessage());
    }

    /*
     * 参数错误异常
     * */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ServerResponse handlerException(Exception e){
        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
            BindingResult result = validException.getBindingResult();
            StringBuffer errorMsg = new StringBuffer();
            if (result.hasErrors()){
                List<ObjectError> errors = result.getAllErrors();
                errors.forEach(p ->{
                    FieldError fieldError = (FieldError) p;
                    errorMsg.append(fieldError.getDefaultMessage()).append(",");
                    logger.error("### 请求参数错误: {" + fieldError.getObjectName() + "}, field{" + fieldError.getField() +
                            "}, errorMessage {" + fieldError.getDefaultMessage() + "}");
                });
            }
        }else if ( e instanceof BindException){
            BindException bindException = (BindException) e;
            if (bindException.hasErrors()){
                logger.error("### 请求参数错误： {}",bindException.getAllErrors());
            }
        }
        return new ServerResponse(ResponseCode.PARAM_IS_INVALID);
    }

    /*
     * 处理所有不可知异常
     * */
    @ExceptionHandler(Exception.class)
    public ServerResponse handleOtherException(Exception e){
        //打印日常堆栈信息
        e.printStackTrace();
        //打印异常信息
        logger.error("### 不可知异常:{} ###",e.getMessage());
        return new ServerResponse(ResponseCode.SYSTEM_INNER_ERROR);
    }
}
