package io.sample.attendance.global.advice;

import io.sample.attendance.global.event.ThrowsException;
import io.sample.attendance.global.exception.BusinessException;
import io.sample.attendance.global.response.ErrorCode;
import io.sample.attendance.global.response.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final ApplicationEventPublisher applicationEventPublisher;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException exception, HttpServletRequest request) {
        applicationEventPublisher.publishEvent(ThrowsException.of(exception, request));
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(ErrorResponse.of(exception, request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unexpectedException(Exception exception, HttpServletRequest request) {
        ErrorCode errorCode = ErrorCode.UNEXPECTED_ERROR;
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(ErrorResponse.of(errorCode, request));
    }

    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        MethodArgumentTypeMismatchException.class,
        HttpRequestMethodNotSupportedException.class,
        HttpMediaTypeNotAcceptableException.class,
        HttpMediaTypeNotSupportedException.class,
        MissingPathVariableException.class,
        MissingServletRequestParameterException.class
    })
    public ResponseEntity<ErrorResponse> invalidRequestException(Exception exception, HttpServletRequest request) {
        ErrorCode errorCode = ErrorCode.valueOf(exception);
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(ErrorResponse.of(errorCode, request));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> inValidArgumentException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ErrorCode errorCode = ErrorCode.valueOf(exception);
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(ErrorResponse.of(errorCode, request, exception.getFieldErrors()));
    }
}
