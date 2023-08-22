package fastrack.stardrinks.dao;

import fastrack.stardrinks.entity.Goodie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoodieDAO extends JpaRepository<Goodie, UUID> {
}
