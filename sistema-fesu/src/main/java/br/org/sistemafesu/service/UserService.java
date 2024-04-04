package br.org.sistemafesu.service;

import br.org.sistemafesu.entity.Role;
import br.org.sistemafesu.repository.RoleRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;
import lombok.NonNull;

import javax.swing.*;
import java.util.*;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    @Autowired
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public void setPassword(@NonNull Long id, @NonNull String password) {
        User user = repository.findById(id).orElse(null);

        if (user != null) {
            user.setPassword(encoder.encode(password));
            repository.save(user);
        }
    }

    public User getUserUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User update(@NonNull Long id, @NonNull User model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        return repository.save(model);
    }

    public void saveUser(User user) {
        if (!repository.existsById(user.getId())) {
            user.setPassword(encoder.encode(user.getPassword()));
            List<Role> roles = new ArrayList<>();
            roles = roleRepository.findAll();

            Set<Role> roleSet = new HashSet<>();
            roleSet.add(roles.get(1));
            user.setRoles(roleSet);
            repository.save(user);
        }
    }

    @Override
    public User save(User model) {
        model.setPassword(encoder.encode(model.getPassword()));

        return super.save(model);
    }
}
