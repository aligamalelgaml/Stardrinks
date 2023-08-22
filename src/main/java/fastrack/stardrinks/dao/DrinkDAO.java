package fastrack.stardrinks.dao;

import fastrack.stardrinks.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrinkDAO extends JpaRepository<Drink, UUID> {
}
