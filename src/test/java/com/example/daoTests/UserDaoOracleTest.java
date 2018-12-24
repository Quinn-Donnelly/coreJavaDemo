package com.example.daoTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.dao.UserDao;
import com.example.dao.UserDaoOracle;
import com.example.models.User;

public class UserDaoOracleTest {
	
	private static UserDao dao;
	private static User user;
	
	@BeforeClass
	public static void setup() {
		dao = UserDaoOracle.getDao();
	}
	
	@Before
	public void createUser() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		user = new User(1L, "Joe", "Blow", roles, "joe26", "joe.blow@gmail.com", "123");
	}
	
	@After
	public void clearInternalDatabase() {
		((UserDaoOracle) dao).setInteralDatabase(new LinkedList<User>());
	}

	@Test
	public void addsUser() {
		dao.addUser(user);
		Boolean exists = ((UserDaoOracle) dao).getInteralDataBase().contains(user);
		assertTrue("User should be stored in the Database once added", exists);
	}
	
	@Test
	public void returnsAddedUser() {
		try {			
			User returnedUser = dao.addUser(user).get();
			assertEquals(user, returnedUser);
		} catch (NoSuchElementException e) {
			fail("Empty Optional Returned");
		}
	}
	
	@Test
	public void returnsUserById() {
		dao.addUser(user);
		try {			
			User returnedUser = dao.getUserById(user.getId()).get();
			assertEquals("User returned doesn't match the one added", user, returnedUser);
		} catch (NoSuchElementException e) {			
			fail("Unabel to get stored user by id");
		} catch (NullPointerException e) {
			fail("Optional not being returned");
		}
	}
	
	@Test
	public void hasEmptyOptionalWhenUserNotFound() {
		try {
			dao.getUserById(1L).get();			
		} catch (NullPointerException e) {
			fail("Optional not being returned");
		} catch (NoSuchElementException e) {
			// Passes test
			return;
		}
		
		fail("Should have thrown NoSuchElementException");
	}

}
