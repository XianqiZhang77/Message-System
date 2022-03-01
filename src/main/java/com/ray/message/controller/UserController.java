package com.ray.message.controller;

import com.ray.message.exception.ServiceException;
import com.ray.message.model.User;
import com.ray.message.model.UserToken;
import com.ray.message.rep.RespResult;
import com.ray.message.sevice.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@ControllerAdvice
@Log4j2
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public RespResult login(@RequestBody User user) throws ServiceException {
        log.info("user: " + user);
        UserToken userToken = userService.login(user.getUsername(), user.getPassword());
        return RespResult.createWithData(userToken);
    }

    @PostMapping("register")
    public RespResult register(@RequestBody User user) throws ServiceException {
        userService.register(user);
        return RespResult.createWithData(null);
    }

    @RequestMapping("logout")
    public RespResult logout(HttpServletRequest request) throws ServiceException {
        String token = request.getHeader("token");
        userService.verifyToken(token);
        userService.logout(token);
        return RespResult.createWithData("logout successfully");
    }
}
