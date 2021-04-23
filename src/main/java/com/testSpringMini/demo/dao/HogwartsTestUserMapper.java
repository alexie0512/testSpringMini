package com.testSpringMini.demo.dao;

import com.testSpringMini.demo.common.MySqlExtensionMapper;
import com.testSpringMini.demo.entity.HogwartsTestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HogwartsTestUserMapper extends MySqlExtensionMapper<HogwartsTestUser> {

    //HogwartsTestUser selectHogwartsTestUser(@Param("id") Integer id);

    int updateUserDemo(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("id") Integer id);

    List<HogwartsTestUser> searchbyIdorName(@Param("userId") Integer userId, @Param("userName") String userName);
}