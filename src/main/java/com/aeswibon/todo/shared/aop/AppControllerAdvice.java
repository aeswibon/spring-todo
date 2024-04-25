package com.aeswibon.todo.shared.aop;

import com.aeswibon.todo.shared.adapter.ResponseAdapter;
import com.aeswibon.todo.shared.dto.ErrorDTO;
import com.aeswibon.todo.shared.dto.ErrorResponseDTO;
import com.aeswibon.todo.shared.error.IError;
import com.aeswibon.todo.shared.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AppControllerAdvice {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> defaultErrorHandler(
      HttpServletRequest request,
      Exception e
  ) {
    int status;
    String type;
    String reason;
    String entity;

    ErrorDTO errorDTO = new ErrorDTO();

    if (e instanceof AppException) {
      IError error = ((AppException) e).getError();
      Throwable rootException = ((AppException) e).getRootException();
      status = error.getHttpStatusCode();
      type = error.getType();
      entity = error.getEntity();
      reason = error.getReason();
      errorDTO.setType(type);
      errorDTO.setReason(reason);
      if (rootException != null) {
        log.error("Custom App Exception", rootException);
      }
    } else {
      log.error("Unchecked Exception", e);
      type = "internal_server_error";
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      reason = "unexpected_error";
      entity = "base";
      errorDTO.setType(type);
      errorDTO.setReason(reason);
    }

    return ResponseEntity
        .status(status)
        .body(ResponseAdapter.createFailureDTO(entity, errorDTO));
  }
}
