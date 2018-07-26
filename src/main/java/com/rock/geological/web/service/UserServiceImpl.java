package com.rock.geological.web.service;

import com.rock.geological.utils.MD5Util;
import com.rock.geological.web.dao.UserDao;
import com.rock.geological.web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        String passwordSalt = MD5Util.encode(user.getPassword());
        user.setPassword(passwordSalt);
        userDao.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        String passwordSalt = MD5Util.encode(password);
        int result = userDao.login(username, passwordSalt);
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }


    public void delete(Integer id) {
        userDao.deleteById(id);
    }

    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }
}
