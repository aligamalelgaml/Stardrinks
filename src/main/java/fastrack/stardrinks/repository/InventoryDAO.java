package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryDAO extends JpaRepository<Inventory, UUID> {
}
