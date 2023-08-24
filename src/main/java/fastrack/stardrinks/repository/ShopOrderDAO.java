package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderDAO extends JpaRepository<ShopOrder, Integer> {
}
