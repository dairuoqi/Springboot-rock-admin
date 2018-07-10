package com.rock.geological.web.service;

import com.rock.geological.web.pojo.User;

import java.util.List;

public interface UserService {

    User findUserByName(String userName);

    void save(User user);


    void delete(Integer id);

    List<User> findAll();

}
