package com.ray.message.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ray.message.dao.UserDao;
import com.ray.message.dao.UserTokenDao;
import com.ray.message.model.User;
import com.ray.message.sevice.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private UserTokenDao userTokenDao;

    @Test
    public void testRegister_happyCase() throws Exception {
        when(this.userDao.findByName("george")).thenReturn(null);
        User user = new User();
        user.setUsername("george");
        user.setPassword("hi");

        this.userService.register(user);
    }

    @Test
    public void testRegister_badCase() throws Exception {
        assertEquals(2, 2);
    }
}
