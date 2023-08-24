package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrinkDAO extends JpaRepository<Drink, UUID> {
}
