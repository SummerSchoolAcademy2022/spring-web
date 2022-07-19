package com.example.springwebtraining.repository;

import com.example.springwebtraining.model.DUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DUserRepository extends JpaRepository<DUser, Long> {
    Optional<DUser> findByEmail(String email);
}
