package com.alura.ForoHub.domain.repository;

import com.alura.ForoHub.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Users, Long> {
    UserDetails findByLogin(String username);
}
