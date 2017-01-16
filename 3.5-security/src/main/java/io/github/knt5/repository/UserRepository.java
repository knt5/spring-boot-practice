package io.github.knt5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.knt5.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
