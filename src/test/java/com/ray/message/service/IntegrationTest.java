package com.ray.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ray.message.dao.UserDaoTest;
import com.ray.message.dao.UserTokenDaoTest;
import com.ray.message.model.User;
import com.ray.message.model.UserToken;
import com.ray.message.utils.PasswordUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDaoTest userDaoTest;

    @Autowired
    private UserTokenDaoTest userTokenDaoTest;

    @Test
    void RegisterTest() throws Exception {
        User user = new User();
        user.setUsername("ray");
        user.setPassword("123");
        user.setEmail("11@gmi.com");
        user.setLocation("mtl");
        user.setNickname("ray");

        mockMvc.perform(post("/user/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(0)));

        User user1 = userDaoTest.findByName("ray");
        assertEquals(user.getUsername(), user1.getUsername());
    }

    @Test
    void LoginTest() throws Exception {
        User user = new User();
        user.setUsername("ray");
        user.setPassword(PasswordUtil.encrypt("123"));
        user.setEmail("11@gmi.com");
        user.setLocation("mtl");
        user.setNickname("ray");
        user.setMemo("");
        user.setCreateTime(new Date());
        userDaoTest.insert(user);
        User user1 = new User();
        user1.setUsername("ray");
        user1.setPassword("123");
        mockMvc.perform(post("/user/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(0)));
    }

    @Test
    void LoginTest_Wrong_Password() throws Exception {
        User user = new User();
        user.setUsername("ray");
        user.setPassword(PasswordUtil.encrypt("123"));
        user.setEmail("11@gmi.com");
        user.setLocation("mtl");
        user.setNickname("ray");
        user.setMemo("");
        user.setCreateTime(new Date());
        userDaoTest.insert(user);
        User user1 = new User();
        user1.setUsername("ray");
        user1.setPassword("1234");
        mockMvc.perform(post("/user/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(-2)));
    }

    @Test
    void LoginTest_Username_UN_Exists() throws Exception {
        User user1 = new User();
        user1.setUsername("ray11");
        user1.setPassword("1234");
        mockMvc.perform(post("/user/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(-3)));
    }


    @AfterEach
    void cleanUp() {
        userDaoTest.deleteAll();
        userTokenDaoTest.deleteAll();
    }

}
