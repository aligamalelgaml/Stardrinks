package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.CoffeeBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoffeeBeanDAO extends JpaRepository<CoffeeBean, UUID> {
}
