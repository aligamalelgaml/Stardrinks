package fastrack.stardrinks.controller;

import fastrack.stardrinks.model.ShopOrder;
import fastrack.stardrinks.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopOrders")
public class OrderController {

    ShopOrderService orderService;

    @Autowired
    public OrderController(ShopOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public void add(@RequestBody ShopOrder order)
    {
        orderService.save(order);
    }

    @GetMapping("")
    public String test() {
        return "Hello!";
    }
}
