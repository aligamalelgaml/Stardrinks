package fastrack.stardrinks.controller;

import fastrack.stardrinks.model.ShopOrder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopOrders")
public class OrderController {

    @PostMapping("")
    public void add(@RequestBody ShopOrder order)
    {
        System.out.println("testing " + order);
        System.out.println(order.getOrderItems());
    }

    @GetMapping("")
    public String test() {
        return "Hello!";
    }
}
