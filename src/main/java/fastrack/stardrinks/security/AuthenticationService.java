package fastrack.stardrinks.security;

import fastrack.stardrinks.dto.LoginDTO;
import fastrack.stardrinks.dto.RegisterDTO;
import fastrack.stardrinks.model.User;
import fastrack.stardrinks.model.base.Role;
import fastrack.stardrinks.repository.UserDAO;
import fastrack.stardrinks.response.security.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    // TODO: Possibly rewrite to utilize UserService class instead of directly using userDAO.
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterDTO request) {
        User newUser = User.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        userDAO.save(newUser);

        String jwt = jwtUtil.generateToken(newUser);

        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userDAO.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Could not login! User not found!"));

        String jwt = jwtUtil.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();
    }
}
