package fastrack.stardrinks.service;

import fastrack.stardrinks.exceptions.InventoryNotFoundException;
import fastrack.stardrinks.exceptions.StockNotSufficientException;
import fastrack.stardrinks.model.Inventory;
import fastrack.stardrinks.repository.InventoryDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private InventoryDAO inventoryDAO;

    @Autowired
    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    @Transactional
    public void reduceStockByProductId(UUID id, int amount) {
        Optional<Inventory> item = this.inventoryDAO.findByProductId(id);

        if(item.isPresent()) {
            int stock = item.get().getStock();

            if(stock - amount >= 0) {
                this.inventoryDAO.reduceStockByProductId(id, amount);
            } else {
                throw new StockNotSufficientException("Stock not sufficient!", stock);
            }

        } else {
            throw new InventoryNotFoundException("Inventory UUID not found!", id);
        }
    }

    public void addStockByProductId(UUID id, int amount) {
        Optional<Inventory> item = this.inventoryDAO.findById(id);

        if(item.isPresent()) {
            this.inventoryDAO.addStockByProductId(id, amount);
        } else {
            throw new InventoryNotFoundException("Inventory UUID not found!", id);
        }
    }

    public void save(Inventory product) {
        this.inventoryDAO.save(product);
    }
}
