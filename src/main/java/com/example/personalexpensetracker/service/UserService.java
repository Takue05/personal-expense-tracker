package com.example.personalexpensetracker.service;

import com.example.personalexpensetracker.model.UserEntity;
import com.example.personalexpensetracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private final UsersRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {

    this.userRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }


  // Method to register a new user
  public UserEntity registerUser(UserEntity user) {  // Use UserEntity
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }
  // Method to retrieve user by ID
  public Optional<UserEntity> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  // Method to retrieve user by username
  public Optional<UserEntity> getUserByEmail(String  email){
    return userRepository.findByEmail(email);
  }

  // Method to retrieve all users
  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }

  // Method to update user details
  public UserEntity updateUser(Long userId, UserEntity updatedUser) {
    if (userRepository.existsById(userId)) {
      updatedUser.setId(userId); // Ensure the ID is set for the update
      return userRepository.save(updatedUser);
    }
    throw new RuntimeException("User not found");
  }

  // Method to change user password
  public void changePassword(Long userId, String newPassword) {
    Optional<UserEntity> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      UserEntity user = userOptional.get();
      user.setPassword(passwordEncoder.encode(newPassword));
      userRepository.save(user);
    } else {
      throw new RuntimeException("User not found");
    }
  }

  // Method to delete a user
  public void deleteUser(Long userId) {
    if (userRepository.existsById(userId)) {
      userRepository.deleteById(userId);
    } else {
      throw new RuntimeException("User not found");
    }
  }

}


