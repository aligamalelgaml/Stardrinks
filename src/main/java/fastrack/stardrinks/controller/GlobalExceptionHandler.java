package fastrack.stardrinks.controller;

import fastrack.stardrinks.exceptions.InventoryNotFoundException;
import fastrack.stardrinks.exceptions.OrderNotFoundException;
import fastrack.stardrinks.exceptions.StockNotSufficientException;
import fastrack.stardrinks.response.InventoryNotFoundResponse;
import fastrack.stardrinks.response.OrderNotFoundResponse;
import fastrack.stardrinks.response.StockNotSufficientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
