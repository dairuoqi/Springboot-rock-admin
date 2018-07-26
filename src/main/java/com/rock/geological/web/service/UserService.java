package com.rock.geological.web.service;

import com.rock.geological.web.pojo.User;

import java.util.List;

public interface UserService {

    void register(User user);

    boolean login(String username,String password);

    User findUserByName(String userName);

    void delete(Integer id);

    List<User> findAll();

}
