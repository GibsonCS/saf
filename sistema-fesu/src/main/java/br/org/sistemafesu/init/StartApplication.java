package br.org.sistemafesu.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createUserIfNotExists("admin", "ADMIN", "master123", "ADM");
        createUserIfNotExists("user", "USER", "user123", "USERS");
    }

    private void createUserIfNotExists(String username, String name, String password, String role) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
