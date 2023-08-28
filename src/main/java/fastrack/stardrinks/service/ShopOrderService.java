package fastrack.stardrinks.service;


import fastrack.stardrinks.model.OrderItem;
import fastrack.stardrinks.model.ShopOrder;
import fastrack.stardrinks.repository.InventoryDAO;
import fastrack.stardrinks.repository.ShopOrderDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderService {
    ShopOrderDAO shopOrderDAO;
    InventoryDAO inventoryDAO;

    @Autowired
    public ShopOrderService(ShopOrderDAO shopOrderDAO, InventoryDAO inventoryDAO) {
        this.shopOrderDAO = shopOrderDAO;
        this.inventoryDAO = inventoryDAO;
    }

    @Transactional
    public void save(ShopOrder order) {
        shopOrderDAO.save(order);

        for(OrderItem orderItem : order.getOrderItems()) {
            inventoryDAO.reduceStockByProductId(orderItem.getProductId(), orderItem.getOrderQuantity());
        }
    }
}
