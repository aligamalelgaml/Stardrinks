package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.base.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDAO extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    List<Product> findByStartMonth(LocalDate startMonth);

    List<Product> findByEndMonth(LocalDate endMonth);
}
