package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.Goodie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoodieDAO extends JpaRepository<Goodie, UUID> {
}
