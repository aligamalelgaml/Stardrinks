package fastrack.stardrinks.repository;

import fastrack.stardrinks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
