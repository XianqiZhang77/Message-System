package com.ray.message.service;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@SpringBootTest
class UserServiceTests {

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
        Mockito.verifyNoMoreInteractions(userDao);
    }

    @Test
    public void testLogin_verify_userTokenDao() throws ServiceException {
        User user = new User();
        user.setUsername("1");
        user.setPassword(PasswordUtil.encrypt("123"));
        when(userDao.findByName("1")).thenReturn(user);
        userService.login("1", "123");
        verify(userTokenDao).insert(any(UserToken.class));
        verify(userDao).findByName("1");
    }

    @Test
    void testLogin_userNotExist_ServiceException() {

        when(userDao.findByName("1")).thenReturn(null);

        try {
            userService.login("1", "123");
        } catch (ServiceException e) {
            assertEquals(ErrorCode.USERNAME_UN_EXISTS, e.getErrorCode());
        }

        verify(userDao).findByName("1");
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
        verify(userDao).findByName("1");
    }

    @Test
    void testRegister_UsernameExists_Exception() {
        User user = new User();
        user.setUsername("1");
        when(userDao.findByName("1")).thenReturn(user);
        try {
            userService.register(user);
        } catch (ServiceException e) {
            assertEquals(ErrorCode.USERNAME_ALREADY_EXISTS, e.getErrorCode());
        }
        verify(userDao).findByName("1");
    }

    @Test
    void testRegister_Success() throws ServiceException {
        User user = new User();
        user.setUsername("1");
        when(userDao.findByName("1")).thenReturn(null);
        userService.register(user);
        verify(userDao).findByName("1");
        verify(userDao).insert(any(User.class));
    }

    @Test
    void testVerifyToken_INVALID_TOKEN() {
        when(userTokenDao.findByToken("1")).thenReturn(null);
        try {
            userService.verifyToken("1");
        } catch (ServiceException e) {
            assertEquals(ErrorCode.INVALID_TOKEN, e.getErrorCode());
        }
        verify(userTokenDao).findByToken("1");
    }

    @Test
    void testVerifyToken_TOKEN_EXPIRED() {
        UserToken userToken = new UserToken();
        String token = UUIDUtil.getToken();
        userToken.setToken(token);
        userToken.setExpireTime(DateUtil.addDays(new Date(166668889), 7));
        when(userTokenDao.findByToken(token)).thenReturn(userToken);
        try {
            userService.verifyToken(token);
        } catch (ServiceException e) {
            assertEquals(ErrorCode.TOKEN_EXPIRED, e.getErrorCode());
        }
        verify(userTokenDao).findByToken(token);
    }

    @Test
    void testVerifyToken_Pass() throws ServiceException {
        UserToken userToken = new UserToken();
        String token = UUIDUtil.getToken();
        userToken.setToken(token);
        userToken.setExpireTime(DateUtil.addDays(new Date(), 7));
        when(userTokenDao.findByToken(token)).thenReturn(userToken);
        assertEquals(true, userService.verifyToken(token));
        verify(userTokenDao).findByToken(token);
    }

    @Test
    void testLogout() {
        UserToken userToken = new UserToken();
        String token = UUIDUtil.getToken();
        userToken.setToken(token);
        userToken.setExpireTime(DateUtil.addDays(new Date(), 7));
        when(userTokenDao.findByToken(token)).thenReturn(userToken);
        userService.logout(token);
        verify(userTokenDao).findByToken(token);
        verify(userTokenDao).deleteUserToken(token);
    }

}
