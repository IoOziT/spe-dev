package fr.sdv.spe_dev.configuration;

import fr.sdv.spe_dev.entities.User;
import fr.sdv.spe_dev.entities.UserRole;
import fr.sdv.spe_dev.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User %s  not found".formatted(email)));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
