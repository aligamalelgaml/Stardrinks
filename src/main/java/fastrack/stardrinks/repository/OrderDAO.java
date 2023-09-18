package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    public List<Order> findByUserId(int userId);
}
