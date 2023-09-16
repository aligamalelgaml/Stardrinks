package fastrack.stardrinks.service;


import fastrack.stardrinks.dto.OrderDTO;
import fastrack.stardrinks.dto.RegisterDTO;
import fastrack.stardrinks.model.OrderItem;
import fastrack.stardrinks.model.User;
import fastrack.stardrinks.model.base.Role;
import fastrack.stardrinks.repository.UserDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    @Transactional
    public User save(RegisterDTO user) {
        return this.userDAO.save(User.builder().firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).password(passwordEncoder.encode(user.getPassword())).role(Role.USER).build());
    }

    public User findById(int id) {
        return userDAO.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find user using provided ID: %d", id)));
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find user using provided email: %s", email)));
    }
}
