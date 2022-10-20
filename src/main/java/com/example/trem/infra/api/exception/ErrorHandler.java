package com.example.trem.infra.api.exception;

import com.example.trem.domain.delivery.exception.DeliveryException;
import com.example.trem.domain.drone.exception.DroneException;
import com.example.trem.domain.video.exception.VideoException;
import com.example.trem.useCase.shared.exception.ConflictException;
import com.example.trem.useCase.shared.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorHandler {

  private Map<String, Object> getErrorMap(String message) {
    Map<String, Object> errorMap = new HashMap<>();

    errorMap.put("success", false);
    errorMap.put("message", message);

    return errorMap;
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  @ResponseBody
  public ExceptionMap handleNotFoundException(NotFoundException e) {
    return new ExceptionMap(e);
  }

  @ExceptionHandler({
          DroneException.class,
          DeliveryException.class,
          VideoException.class
  })
  @ResponseStatus(BAD_REQUEST)
  @ResponseBody
  public ExceptionMap handleDroneException(DroneException e) {
    return new ExceptionMap(e);
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(CONFLICT)
  @ResponseBody
  public ExceptionMap handleConflictException(ConflictException e) {
    return new ExceptionMap(e);
  }

//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(INTERNAL_SERVER_ERROR)
//  @ResponseBody
//  public ExceptionMap handleException(Exception e) {
//    return new ExceptionMap(
//            HttpServerErrorException.create(
//                    INTERNAL_SERVER_ERROR,
//                    "Internal Server Error",
//                    null,
//                    null,
//                    null)
//    );
//  }

}
