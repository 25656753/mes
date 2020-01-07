package com.jg.mes.dao;

import com.jg.mes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    List<User> readByusername(String name);

    int countByusername(String name);

    @Query(value = "select u from User u ")
   List<User> inall();

    @Modifying
    @Query(value = "update User u  set u.username=:name where u.userid=:id")
    void ina(@Param("id") String uid,@Param("name") String name);


    @Modifying
    @Query(value = "update user  set username=:name where userid=:id",nativeQuery = true)
    void inbb(@Param("id") String uid,@Param("name") String name);


}
