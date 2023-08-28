package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Inventory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface InventoryDAO extends JpaRepository<Inventory, UUID> {

    @Modifying
    @Query("UPDATE Inventory i SET i.stock = i.stock - :amount WHERE i.id = :productId")
    void reduceStockByProductId(@Param("productId") UUID productId, @Param("amount") int amount);

//    public default int reduceStockByProductId(UUID productId, int amount) {
//        return entityManager.createQuery(
//                        "UPDATE Inventory i SET i.quantity = i.quantity - :amount WHERE i.id = :productId")
//                .setParameter("amount", amount)
//                .setParameter("productId", productId)
//                .executeUpdate();
//    }
}
