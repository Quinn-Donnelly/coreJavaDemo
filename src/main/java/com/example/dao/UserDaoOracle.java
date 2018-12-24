package com.example.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.models.User;

public class UserDaoOracle implements UserDao {
	
	private static final Logger logger = LogManager.getLogger(UserDaoOracle.class);
	
	private static UserDao dao;
	LinkedList<User> users;
	
	private UserDaoOracle() {
		users = new LinkedList<User>();
	}
	
	public static UserDao getDao() {
		if (dao == null) {
			dao = new UserDaoOracle();
		}
		
		return dao;
	}

	public Optional<User> addUser(User user) {
		logger.traceEntry();
		
		try {			
			users.add(user);
			logger.info("Added user to database", user);
		} catch (Exception e) {
			return Optional.empty();
		}
		
		return logger.traceExit(Optional.of(user));
	}

	@Override
	public Optional<User> getUserById(Long id) {
		for (User u:users) {
			if (id == u.getId()) {
				return Optional.of(u);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<Boolean> deleteUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<ArrayList<User>> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}


	
	/*
	 * These methods are here since there is no database at play to mock
	 * These should only be called from the tests
	 */
	public LinkedList<User> getInteralDataBase() {
		return users;
	}

	public void setInteralDatabase(LinkedList<User> users) {
		this.users = users;
	}
	
}
