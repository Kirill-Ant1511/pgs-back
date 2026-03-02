package pal.comp.pgsbackend.exception;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception e) {
        log.error("Error: {}\nType Error: {}", e.getMessage(), e.getClass());
        var exceptionDto = new ExceptionDto(
                "Ошибка сервера" + e.getClass(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDto);
    }

    @ExceptionHandler(exception = EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Error: {}", e.getMessage());
        var exceptionDto = new ExceptionDto(
                "Сущность не найдена",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }


    @ExceptionHandler(exception = DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> handleUniqueErrorException(Exception e) {
        log.error("Error: {}", e.getMessage());
        var exceptionDto = new ExceptionDto(
                "Ошибка уникальности, проверьте не существует ли такой сущности",
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }
}
