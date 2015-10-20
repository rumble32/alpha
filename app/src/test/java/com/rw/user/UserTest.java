package com.rw.user;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rw.config.Application;
import com.rw.entity.User;
import com.rw.repository.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@Transactional
public class UserTest {

	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	@Autowired
	private UserRepository userRepository;

	private final String phoneNumber = "114";
	
	@Before
	public void initTest() {
		// set up the database, create basic structure for testing
		logger.info("initTest");
		User user = new User();
		user.setFirstName("tag");
		user.setLastName("zhang");
		user.setPhone(phoneNumber);
		userRepository.save(user);
	}

	/**
	 * 手机号重复注册
	 */
	@Test(expected=DataIntegrityViolationException.class)
	public void saveDuplicatedPhone() {
		
		User user = new User();
		user.setFirstName("tag");
		user.setLastName("zhang");
		user.setPhone(phoneNumber);
		userRepository.save(user);
	}
	
	/**
	 * 更新User信息
	 */
	@Test
	public void update() {
		logger.info("update");
		
		String firstName = "foo";
		
		User user = userRepository.findByPhone(phoneNumber);
		user.setFirstName(firstName);
		user = userRepository.save(user);
		assertEquals(firstName, user.getFirstName());
	}
	
	@After
	public void endTest() {
		logger.info("endTest");
	}
}
