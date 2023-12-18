package br.org.sistemafesu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.org.sistemafesu.entity.Role;
import br.org.sistemafesu.repository.RoleRepository;

@Configuration
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createRoleIfNotExistsRole(String name) {
        String role_name = "ROLE_" + name;
        Role roleExists = roleRepository.findByName(role_name);

        if (roleExists == null) {
            Role role = new Role();
            role.setName(role_name);

            return roleRepository.save(role);
        }

        return roleExists;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
