package fastrack.stardrinks.dao;

import fastrack.stardrinks.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderDAO extends JpaRepository<ShopOrder, Integer> {
}
