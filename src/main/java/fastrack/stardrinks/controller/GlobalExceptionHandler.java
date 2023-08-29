package fastrack.stardrinks.controller;

import fastrack.stardrinks.exceptions.InventoryNotFoundException;
import fastrack.stardrinks.exceptions.OrderMismatchException;
import fastrack.stardrinks.exceptions.OrderNotFoundException;
import fastrack.stardrinks.exceptions.StockNotSufficientException;
import fastrack.stardrinks.response.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StockNotSufficientResponse> handleException(StockNotSufficientException exc) {

        StockNotSufficientResponse response = new StockNotSufficientResponse();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage("Available stock is not sufficient, available stock: " + exc.getAvailableStock());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<OrderNotFoundResponse> handleException(OrderNotFoundException exc) {

        OrderNotFoundResponse response = new OrderNotFoundResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("Order not found, provided order ID: " + exc.getOrderID());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<InventoryNotFoundResponse> handleException(InventoryNotFoundException exc) {

        InventoryNotFoundResponse response = new InventoryNotFoundResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("Inventory UUID not found, provided inventory UUID: " + exc.getId());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OrderMismatchResponse> handleException(OrderMismatchException exc) {

        OrderMismatchResponse response = new OrderMismatchResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(String.format("Order ID mismatch! Provided body order object ID (%d) does not have the expected URI ID (%d)!", exc.getBodyId(), exc.getUriId()));
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationFailedResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                System.out.println(fieldName + ": " + errorMessage );
                System.out.println();
                errors.put(fieldName, errorMessage);
            } else {
                String errorMessage = error.getDefaultMessage();
                errors.put("globalError", errorMessage);
            }
        });

        ValidationFailedResponse response = new ValidationFailedResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(errors.toString());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
