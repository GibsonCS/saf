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
import org.springframework.ui.Model;

import javax.swing.*;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private User user;

    public List<User> getAll() {

        return userRepository.findAll();
    }

    public void save(User user) {
        if (!userRepository.existsById(user.getId())) {
            userRepository.save(user);
        }
    }

    public void setPassword(@NonNull Long id, @NonNull String password) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
        }
    }

    public User getUserUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }
        User userFound = userRepository.findById(user.getId()).orElse(null);

        userFound.setNomeCompleto(user.getNomeCompleto());
        userFound.setUsername(user.getUsername());
        userFound.setEmail(user.getEmail());
        userFound.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userFound);

//        User userUpdated = userRepository.findById(id).orElse(null);
//
//        userUpdated.setRoles(user.getRoles());
//        userUpdated.setPassword(user.getPassword());
//        userUpdated.setNomeCompleto(user.getNomeCompleto());
//        userUpdated.setUsername(user.getUsername());
//        userRepository.save(userUpdated);
    }

    public void saveUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles = roleRepository.findAll();

        Set<Role> roleSet = new HashSet<>();
        roles.forEach(role -> {
            if (role.getName().equalsIgnoreCase("ROLE_STUDENT")){
                roleSet.add(role);
            }
        });

        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
