package com.example.personalexpensetracker.repository;

import com.example.personalexpensetracker.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long > {
  Optional<UserEntity> findByEmail(String email);

}
