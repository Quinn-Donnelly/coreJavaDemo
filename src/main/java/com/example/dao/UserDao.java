package com.example.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.example.models.User;

public interface UserDao {
	public Optional<User> addUser(User user);
	public Optional<User> getUserById(Long id);
	public Optional<ArrayList<User>> getAllUsers();
	public Optional<Boolean> deleteUserById(Long id);
}
