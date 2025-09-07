package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.org.sistemafesu.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT e FROM User e LEFT JOIN FETCH e.roles WHERE e.username= (:username)")
    public User findByUsername(@Param("username")String username);
}
