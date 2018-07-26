package com.rock.geological.web.dao;


import com.rock.geological.web.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {

    /*根据用户名称查询用户*/
    @Query(value = "select * from user where username= ?1", nativeQuery = true)
    User findUserByName(String userName);


    /*登陆*/
    @Query(value = "select count(1) from user where username=?1 and password=?2", nativeQuery = true)
    int login(String username, String password);

}

