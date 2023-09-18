package fastrack.stardrinks.service;


import fastrack.stardrinks.dto.OrderDTO;
import fastrack.stardrinks.dto.OrderItemDTO;
import fastrack.stardrinks.exceptions.OrderNotFoundException;
import fastrack.stardrinks.model.OrderItem;
import fastrack.stardrinks.model.Order;
import fastrack.stardrinks.model.User;
import fastrack.stardrinks.repository.OrderDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderDAO orderDAO;
    ProductService productService;
    UserService userService;
    InventoryService inventoryService;

    @Autowired
    public OrderService(OrderDAO orderDAO, ProductService productService, UserService userService, InventoryService inventoryService) {
        this.orderDAO = orderDAO;
        this.productService = productService;
        this.userService = userService;
        this.inventoryService = inventoryService;
    }

    public OrderService() {
    }

    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {

        Order newOrder = this.mapToEntity(orderDTO);

        orderDAO.save(newOrder);

        for (OrderItem orderItem : newOrder.getOrderItems()) {
            inventoryService.reduceStockByProductId(orderItem.getProduct().getId(), orderItem.getItemQuantity());
        }

        return newOrder.mapToDto();
    }

    @Transactional
    public OrderDTO update(OrderDTO orderDTO) {

        int id = orderDTO.getId();
        Optional<Order> existingOrder = orderDAO.findById(id);

        if (existingOrder.isPresent()) {
            for(OrderItem orderItem : existingOrder.get().getOrderItems()) {
                inventoryService.addStockByProductId(orderItem.getProduct().getId(), orderItem.getItemQuantity());
            }
        } else {
            throw new OrderNotFoundException("Order update failed! Order not found. Provided ID: " + id, id);
        }

        return this.save(orderDTO);
    }

    @Transactional
    public void delete(int orderId) {
        // TODO: Consider adding flag for deletion instead of hard delete
        this.orderDAO.findById(orderId).ifPresent(order -> this.orderDAO.delete(order));
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = this.orderDAO.findAll();
        return orders.stream().map(Order::mapToDto).toList();
    }

    public OrderDTO getOrderById(int id) {
        Order order = this.orderDAO.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found", id));
        return order.mapToDto();
    }

    public List<OrderDTO> getOrderByUser(int userId) {
        List<Order> orders = this.orderDAO.findByUserId(userId);

        return orders.stream().map(Order::mapToDto).toList();
    }

    /**
     * Maps order requests to order entities.
     * @param orderDTO Order DTO
     * @return Order entity
     */
    private Order mapToEntity(OrderDTO orderDTO) {
        User user = this.userService.findById(orderDTO.getUserId());
        List<OrderItem> orderItems = this.getRequestOrderItems(orderDTO.getOrderItems());

        return Order.builder().user(user).orderItems(orderItems).build();
    }

    /**
     * Utility function that extracts OrderItems objects from a list of OrderItems data transfer objects.
     * @param requestOrderItems Order items in data transfer representation.
     * @return List of actual order item entities.
     */
    private List<OrderItem> getRequestOrderItems(List<OrderItemDTO> requestOrderItems) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO requestItem : requestOrderItems) {
            orderItems.add(OrderItem.builder().product(this.productService.findProductById(requestItem.getProductId())).itemQuantity(requestItem.getItemQuantity()).build());
        }

        return orderItems;
    }

}
