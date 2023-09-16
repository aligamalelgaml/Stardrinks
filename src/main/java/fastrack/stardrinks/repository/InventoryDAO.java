package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Inventory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

public interface InventoryDAO extends JpaRepository<Inventory, Integer> {

    @Modifying
    @Query("UPDATE Inventory i SET i.stock = i.stock - :amount WHERE i.product.id = :productId")
    void reduceStockByProductId(@Param("productId") UUID productId, @Param("amount") int amount);

    @Modifying
    @Query("UPDATE Inventory i SET i.stock = i.stock + :amount WHERE i.product.id = :productId")
    void addStockByProductId(@Param("productId") UUID productId, @Param("amount") int amount);

    Optional<Inventory> findByProductId(UUID productId);

    Optional<Inventory> findByProductPrice(BigDecimal price);
}
