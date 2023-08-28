package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface DrinkDAO extends JpaRepository<Drink, UUID> {
}
