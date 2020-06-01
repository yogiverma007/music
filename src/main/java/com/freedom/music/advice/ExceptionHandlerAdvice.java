package com.freedom.music.advice;

import com.freedom.music.CacheManager;
import com.freedom.music.controller.CommentsController;
import com.freedom.music.controller.LikesController;
import com.freedom.music.controller.MusicController;
import com.freedom.music.controller.UserController;
import com.freedom.music.exception.ValidationException;
import com.freedom.music.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.freedom.music.common.constants.StringConstants.*;

@Slf4j
@RestControllerAdvice(assignableTypes = {UserController.class, MusicController.class,
        LikesController.class, CommentsController.class})
public class ExceptionHandlerAdvice {

    @Autowired
    private CacheManager cacheManager;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public BaseResponse handleException(BindException exception) {

        log.error("BindException occurred {}", exception.getMessage());
        String responseConstant = exception.getBindingResult().getFieldError().getDefaultMessage();
        return getBaseResponse(responseConstant);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleException(MethodArgumentNotValidException exception) {

        log.error("MethodArgumentNotValidException ", exception);
        String responseConstant = exception.getBindingResult().getFieldError().getDefaultMessage();
        return getBaseResponse(responseConstant);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception exception) {

        log.error("Internal Server Error: ", exception);
        if (exception instanceof DataIntegrityViolationException
                && ((DataIntegrityViolationException) exception).getRootCause().getLocalizedMessage().toUpperCase().contains("DUPLICATE")) {
            return getBaseResponse(DUPLICATE_REQUEST);
        }
        return getBaseResponse(INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public BaseResponse handleException(ValidationException exception) {

        log.error("ValidationException ", exception);
        String responseConstant = exception.getMessage();
        return getBaseResponse(responseConstant);
    }

    private BaseResponse getBaseResponse(String respConstant) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(cacheManager.findRespCodeByRespConstant(respConstant));
        response.setMessage(respConstant);
        response.setStatus(FAILURE);

        return response;
    }

}