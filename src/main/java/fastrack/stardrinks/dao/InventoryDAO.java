package fastrack.stardrinks.dao;

import fastrack.stardrinks.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryDAO extends JpaRepository<Inventory, UUID> {
}
