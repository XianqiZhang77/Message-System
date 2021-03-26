package com.ray.message;

import com.ray.message.dao.UserDao;
import com.ray.message.dao.UserTokenDao;
import com.ray.message.enums.ErrorCode;
import com.ray.message.exception.ServiceException;
import com.ray.message.model.User;
import com.ray.message.model.UserToken;
import com.ray.message.sevice.UserService;
import com.ray.message.utils.DateUtil;
import com.ray.message.utils.PasswordUtil;
import com.ray.message.utils.UUIDUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class DemoUserControllerTests {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserDao userDao;

	@Mock
	private UserTokenDao userTokenDao;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void verifyNoIntegrations() {
		Mockito.verifyNoMoreInteractions(userTokenDao);
	}

	@Test
	public void testLogin_verify_userTokenDao() throws ServiceException {
//		String token = UUIDUtil.getToken();
//		UserToken userToken = new UserToken(1, token, DateUtil.addDays(new Date(), 7));
		User user = new User();
		user.setUsername("1");
		user.setPassword(PasswordUtil.encrypt("123"));
		when(userDao.findByName("1")).thenReturn(user);
		userService.login("1", "123");
		verify(userTokenDao).insert(any(UserToken.class));
	}

	@Test
	void testLogin_userNotExist_ServiceException() {

		when(userDao.findByName("1")).thenReturn(null);

		try {
			userService.login("1", "123");
		} catch (ServiceException e) {
			assertEquals(ErrorCode.USERNAME_UN_EXISTS, e.getErrorCode());
		}
	}

	@Test
	void testLogin_userPassword_notCorrect() {
		User user = new User();
		user.setUsername("1");
		user.setPassword(PasswordUtil.encrypt("123"));
		when(userDao.findByName("1")).thenReturn(user);
		try {
			userService.login("1", "1234");
		} catch (ServiceException e) {
			assertEquals(ErrorCode.WRONG_PASSWORD, e.getErrorCode());
		}
	}



	@Test
	void contextLoads() {
	}


}
