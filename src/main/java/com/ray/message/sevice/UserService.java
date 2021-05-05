package com.ray.message.sevice;

import com.ray.message.dao.UserDao;
import com.ray.message.dao.UserTokenDao;
import com.ray.message.enums.ErrorCode;
import com.ray.message.exception.ServiceException;
import com.ray.message.model.User;
import com.ray.message.model.UserToken;
import com.ray.message.utils.DateUtil;
import com.ray.message.utils.PasswordUtil;
import com.ray.message.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;

    public UserToken login(String username, String password) throws ServiceException {
        User user = userDao.findByName(username);
        if (user == null) {
            throw new ServiceException(ErrorCode.USERNAME_UN_EXISTS);
        }
        String password1 = user.getPassword();
        String password2 = PasswordUtil.encrypt(password);
        if (!password1.equals(password2)) {
            throw new ServiceException(ErrorCode.WRONG_PASSWORD);
        }
        String token = UUIDUtil.getToken();
        UserToken userToken = new UserToken(user.getId(), token, DateUtil.addDays(new Date(), 7));
        userTokenDao.insert(userToken);
        return userToken;
    }

    public void register(User user) throws ServiceException {
        if (userDao.findByName(user.getUsername()) != null) {
            throw new ServiceException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        user.setCreateTime(new Date());
        user.setMemo("");
        userDao.insert(user);
    }


    public Integer verifyToken(String token) throws ServiceException {
        UserToken userToken = userTokenDao.findByToken(token);
        if (userToken == null) {
            throw new ServiceException(ErrorCode.INVALID_TOKEN);
        }
        if (userToken.getExpireTime().before(new Date())) {
            throw new ServiceException(ErrorCode.TOKEN_EXPIRED);
        }
        return userToken.getUserId();
    }

    public void logout(String token) {
        UserToken userToken = userTokenDao.findByToken(token);
        userTokenDao.deleteUserToken(userToken.getToken());
    }

}
