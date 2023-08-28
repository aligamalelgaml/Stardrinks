package fastrack.stardrinks.controller;

import fastrack.stardrinks.model.Order;
import fastrack.stardrinks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Order> add(@RequestBody Order order)
    {
        orderService.save(order);
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
}
