package com.rock.geological.web.dao;


import com.rock.geological.web.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserDao extends CrudRepository<User, Integer> {

    /*根据用户名称查询用户*/
    @Query(value = "select * from user where username= ?1", nativeQuery = true)
    User findUserByName(String userName);


}

