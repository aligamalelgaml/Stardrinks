package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.CoffeeBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface CoffeeBeanDAO extends JpaRepository<CoffeeBean, UUID> {
}
