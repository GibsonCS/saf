package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role_name);
}
