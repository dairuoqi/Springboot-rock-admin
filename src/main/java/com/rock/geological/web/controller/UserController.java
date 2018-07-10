package com.rock.geological.web.controller;

import com.rock.geological.web.pojo.User;
import com.rock.geological.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    UserService userService;

    //查询所有
    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/getUserByName")
    public ResponseEntity<?> getUserByName(String userName) {
        User user = userService.findUserByName(userName);
        return ResponseEntity.ok(user);
    }

    //添加
    @PostMapping("/save")
    public void register(@RequestBody User user) {
        userService.save(user);
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
