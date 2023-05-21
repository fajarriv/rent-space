package id.ac.ui.cs.advprog.rentingandbooking.exceptions.advice;

import id.ac.ui.cs.advprog.rentingandbooking.exceptions.ErrorTemplate;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.reservation.ReservationIdDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            SpaceIdDoesNotExistException.class,
            SpaceNameDoesNotExistException.class,
            SpaceCategoryDoesNotExistException.class,
            ReservationIdDoesNotExistException.class,
            SpaceTypeDoesNotExistException.class
    })
    public ResponseEntity<Object> notExistHandler(Exception exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorTemplate baseException = new ErrorTemplate(
                exception.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(baseException, status);
    }

    @ExceptionHandler(value = {SpaceNameAlreadyExistException.class, SpaceCategoryAlreadyExistException.class, SpaceDoesNotAvailableException.class})
    public ResponseEntity<Object> alreadyExistHandler(Exception exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTemplate baseException = new ErrorTemplate(
                exception.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(baseException, status);
    }

}
