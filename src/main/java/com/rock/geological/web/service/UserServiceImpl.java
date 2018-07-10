package com.rock.geological.web.service;

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
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    public void save(User user){
        userDao.save(user);
    }


    public void delete(Integer id) {
        userDao.deleteById(id);
    }

    public List<User> findAll(){
        return (List<User>) userDao.findAll();
    }
}
