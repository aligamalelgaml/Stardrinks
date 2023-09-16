package fastrack.stardrinks.security;

import fastrack.stardrinks.dto.LoginDTO;
import fastrack.stardrinks.dto.RegisterDTO;
import fastrack.stardrinks.model.User;
import fastrack.stardrinks.model.base.Role;
import fastrack.stardrinks.repository.UserDAO;
import fastrack.stardrinks.response.security.AuthenticationResponse;
import fastrack.stardrinks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterDTO request) {
        User newUser = this.userService.save(request);

        String jwt = this.jwtUtil.generateToken(newUser);

        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse login(LoginDTO request) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = this.userService.findByEmail(request.getEmail());

        String jwt = this.jwtUtil.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();
    }
}
