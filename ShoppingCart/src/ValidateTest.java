import static org.junit.Assert.*;
import model.Userprofile;

import org.junit.Test;

import customTools.DBUtil;


public class ValidateTest {

	@Test
	public void testValidateUser() {
		System.out.println("Test if user login details are correct");
		assertTrue(DBUtil.validateUser("neha@gmail.com", "password"));
	}

	@Test
	public void testEmailExists() {
		System.out.println("Test if user email exists");
		assertTrue(DBUtil.emailExists("neha@gmail.com"));
	}

	@Test
	public void testUserPayExist() {
		System.out.println("Test if user pay details exists");
		Userprofile user = DBUtil.getUser("neha@gmail.com");
		assertTrue(DBUtil.userPayExist(user));
	}

}
