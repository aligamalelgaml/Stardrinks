package fastrack.stardrinks.controller;

import fastrack.stardrinks.exceptions.OrderMismatchException;
import fastrack.stardrinks.model.Order;
import fastrack.stardrinks.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/orders")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Order> add(@Valid @RequestBody Order order)
    {
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> update(@Valid @RequestBody Order order, @PathVariable int orderId) {
        if (orderId != order.getId()) {
            throw new OrderMismatchException("Order ID mismatch!", orderId, order.getId());
        }

        orderService.update(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(this.orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        return new ResponseEntity<>(this.orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable int orderId) {
        this.orderService.delete(orderId);
    }
}
