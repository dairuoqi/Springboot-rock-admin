package com.rock.geological.web.controller;

import com.rock.geological.res.ResponseMgr;
import com.rock.geological.web.pojo.User;
import com.rock.geological.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/getUserByName")
    public ResponseEntity<?> getUserByName(String userName) {
        User user = userService.findUserByName(userName);
        return ResponseEntity.ok(ResponseMgr.successWithData(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,Object> map) {
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        boolean result = userService.login(username, password);
        if (result) {
            return ResponseEntity.ok(ResponseMgr.success());
        }
        return ResponseEntity.ok(ResponseMgr.failed());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok(ResponseMgr.success());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
