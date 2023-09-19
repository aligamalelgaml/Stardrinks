package fastrack.stardrinks.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fastrack.stardrinks.dto.OrderDTO;
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

@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<OrderDTO> add(@Valid @RequestBody OrderDTO orderDTO)
    {
        return ResponseEntity.ok(orderService.save(orderDTO));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO orderDTO, @PathVariable int orderId) {
        // Sanity check to ensure request body order id matches correct route.
        if (orderId != orderDTO.getId()) {
            throw new OrderMismatchException("Order ID mismatch!", orderId, orderDTO.getId());
        }

        return ResponseEntity.ok(orderService.update(orderDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int orderId) {
        return ResponseEntity.ok(this.orderService.getOrderById(orderId));
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable int orderId) {
        this.orderService.delete(orderId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUser(@PathVariable int userId) {
        return ResponseEntity.ok(this.orderService.getOrderByUser(userId));
    }

}
