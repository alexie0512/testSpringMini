package com.testSpringMini.demo.service;

import com.testSpringMini.demo.common.DemoToken;
import com.testSpringMini.demo.dto.ResultDto;
import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.dto.AddHogwartsTestUserDto;
import com.testSpringMini.demo.entity.HogwartsTestUser;

import java.util.List;

public interface AJUserService {

    public ResultDto<DemoToken> login(UserDto userDto);

    /**
     * 保存
     * @param hogwartsTestUser
     * @return
     */
    public ResultDto<HogwartsTestUser> save(HogwartsTestUser hogwartsTestUser);

    /**
     * 更新
     * @param hogwartsTestUser
     * @return
     */
    public ResultDto<HogwartsTestUser> update(HogwartsTestUser hogwartsTestUser);

    /**
     * 根据用户id 和 名字 模糊查询
     * @param hogwartsTestUser
     * @return
     */
    public ResultDto<List<HogwartsTestUser>> searchbyNameorId(HogwartsTestUser hogwartsTestUser);

    /**
     * 根据用户id 删除用户
     * @param userId
     * @return
     */
    public ResultDto<HogwartsTestUser> deletebyId(Integer userId);


}
