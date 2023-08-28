package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Integer> {
}
