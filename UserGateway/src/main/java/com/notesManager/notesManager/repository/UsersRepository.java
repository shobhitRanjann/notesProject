package com.notesManager.notesManager.repository;

import com.notesManager.notesManager.entity.Role;
import com.notesManager.notesManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email) throws RuntimeException;

    User findByRole(Role role);
}
