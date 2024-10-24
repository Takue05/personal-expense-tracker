package com.example.personalexpensetracker.controller;

import com.example.personalexpensetracker.model.UserEntity;
import com.example.personalexpensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  // Endpoint to register a new user
  @PostMapping("/register")
  public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
    UserEntity createdUser = userService.registerUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  // Endpoint to retrieve user by ID
  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
    Optional<UserEntity> user = userService.getUserById(id);
    return user.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
    Optional<UserEntity> user = userService.getUserByEmail(email);
    return user.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());}

  // Endpoint to retrieve all users
  @GetMapping
  public ResponseEntity<List<UserEntity>> getAllUsers() {
    List<UserEntity> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  // Endpoint to update user details
  @PutMapping("/{id}")
  public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {
    try {
      UserEntity user = userService.updateUser(id, updatedUser);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to change user password
  @PutMapping("/{id}/password")
  public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
    try {
      userService.changePassword(id, newPassword);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to delete a user
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
