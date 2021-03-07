package com.ray.message.controller;

import com.ray.message.exception.ServiceException;
import com.ray.message.model.User;
import com.ray.message.model.UserToken;
import com.ray.message.rep.RespResult;
import com.ray.message.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public RespResult login(@RequestBody User user, HttpServletResponse response) {
        try {
            UserToken userToken = userService.login(user.getUsername(), user.getPassword());
            response.setHeader("token", userToken.getToken());
            return RespResult.createWithData(userToken);
        } catch (ServiceException e) {
            return RespResult.createWithErrorCode(e.getErrorCode());
        }
    }

    @PostMapping("register")
    public RespResult register(@RequestBody User user) {
        try {
            user.setCreateTime(new Date());
            userService.register(user);
            return RespResult.createWithData(null);
        } catch (ServiceException e) {
            return RespResult.createWithErrorCode(e.getErrorCode());
        }
    }

    @RequestMapping("logout")
    public RespResult logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        try {
            userService.verifyToken(token);
        } catch (ServiceException e) {
            return RespResult.createWithErrorCode(e.getErrorCode());
        }
        userService.logout(token);
        return RespResult.createWithData("logout successfully");
    }



}
