package br.org.sistemafesu.init;

import java.util.ArrayList;
import java.util.List;

import br.org.sistemafesu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.org.sistemafesu.entity.Role;
import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;
import br.org.sistemafesu.service.RoleService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> admin_roles = new ArrayList<String>() {{
            add("ADMIN");
            add("USER");
        }};

        List<String> user_role = new ArrayList<String>() {{
            add("USER");
        }};

        String role = "STUDENT";

        roleService.createRoleIfNotExistsRole(role);

        createUserIfNotExists("admin", "ADMIN", "master123", admin_roles);
        createUserIfNotExists("user", "USER", "user123", user_role);
    }


    private void createUserIfNotExists(String username, String name, String password, Iterable<String> user_roles) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            user = new User();
            user.setNomeCompleto(name);
            user.setUsername(username);
            user.setPassword(encoder.encode(password));

            for (String role_name : user_roles) {
                Role role = roleService.createRoleIfNotExistsRole(role_name);

                user.getRoles().add(role);
            }

            userRepository.save(user);
        }
    }
}
