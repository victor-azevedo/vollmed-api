package br.com.victor.vollmed.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class HandleError {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleErrorNotFound() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handleErrorSQLUniqueValidation(DataIntegrityViolationException ex) {
    String sqlState = getSqlState(ex);

    if (sqlState.equals("23505")) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseSqlErrorDto(ex));
    }

    return ResponseEntity.badRequest().body(new ResponseSqlErrorDto(ex));
  }

  private static String getSqlState(DataIntegrityViolationException ex) {
    Throwable rootCause = ex.getRootCause();

    if (rootCause instanceof SQLException) {
      return ((SQLException) rootCause).getSQLState();
    }

    return "";
  }

  private static String getDetailFromSQlException(DataIntegrityViolationException ex) {
    String message = ex.getMessage();
    int detailStartIndex = message.indexOf("Detail:");
    int detailEndIndex = message.indexOf(".]", detailStartIndex);

    if (detailStartIndex != -1 && detailEndIndex != -1) {
      return message.substring(detailStartIndex, detailEndIndex);
    }

    return message;
  }

  private record ResponseSqlErrorDto(String message) {
    private ResponseSqlErrorDto(DataIntegrityViolationException ex) {
      this(getDetailFromSQlException(ex));
    }
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleErrorUnprocessableEntity(MethodArgumentNotValidException ex) {
    var erros = ex.getFieldErrors();

    return ResponseEntity.unprocessableEntity()
        .body(erros.stream().map(FieldErrorsDto::new).toList());
  }

  private record FieldErrorsDto(String field, String message) {
    public FieldErrorsDto(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }
}
