package fastrack.stardrinks.dao;

import fastrack.stardrinks.entity.CoffeeBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoffeeBeanDAO extends JpaRepository<CoffeeBean, UUID> {
}
