package fastrack.stardrinks.service;


import fastrack.stardrinks.exceptions.OrderNotFoundException;
import fastrack.stardrinks.model.OrderItem;
import fastrack.stardrinks.model.Order;
import fastrack.stardrinks.repository.InventoryDAO;
import fastrack.stardrinks.repository.OrderDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    OrderDAO orderDAO;
    InventoryService inventoryService;

    @Autowired
    public OrderService(OrderDAO orderDAO, InventoryService inventoryService) {
        this.orderDAO = orderDAO;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public void save(Order order) {
        orderDAO.save(order);

        for(OrderItem orderItem : order.getOrderItems()) {
            inventoryService.reduceStockByProductId(orderItem.getProductId(), orderItem.getItemQuantity());
        }
    }

    public List<Order> getAllOrders() {
        return this.orderDAO.findAll();
    }

    public Order getOrderById(int id) {
        Optional<Order> order = this.orderDAO.findById(id);

        if(order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundException("Order with ID " + id + " not found", id);
        }
    }
}
