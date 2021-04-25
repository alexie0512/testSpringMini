package com.testSpringMini.demo.service.impl;

import com.testSpringMini.demo.common.UserConstants;
import com.testSpringMini.demo.dao.HogwartsTestUserMapper;
import com.testSpringMini.demo.dto.ResultDto;
import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.dto.AddHogwartsTestUserDto;
import com.testSpringMini.demo.entity.HogwartsTestUser;
import com.testSpringMini.demo.service.AJUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @Component : 声明为springboot的bean
 */


@Service
public class AJUserServiceImpl implements AJUserService {

    @Autowired
    private HogwartsTestUserMapper hogwartsTestUserMapper;

    @Override
    public String login(UserDto userDto) {
        return "用户名： " + userDto.getUsername() + "  - 密码： " + userDto.getPwd();
    }


    /**
     * 保存
     * @param hogwartsTestUser
     * @return
     */
    @Override
    public ResultDto<HogwartsTestUser> save(HogwartsTestUser hogwartsTestUser) {

        //校验用户名是否已经存在
        String userName= hogwartsTestUser.getUserName();
        HogwartsTestUser queryHTUser= new HogwartsTestUser();
        queryHTUser.setUserName(userName);
        HogwartsTestUser HTUser=hogwartsTestUserMapper.selectOne(queryHTUser);
        if(Objects.nonNull(HTUser)){
            return ResultDto.fail("用户名已存在");
        }

        //密码MD5加密存储
        String password= hogwartsTestUser.getPassword();
        String encryptedPWD= DigestUtils.md5DigestAsHex((UserConstants.md5Hex_sign+userName+password).getBytes());
        hogwartsTestUser.setPassword(encryptedPWD);
        hogwartsTestUser.setCreateTime(new Date());
        hogwartsTestUser.setUpdateTime(new Date());
        hogwartsTestUser.setAutoCreateCaseJobName("1");
        hogwartsTestUser.setStartTestJobName("1");
        hogwartsTestUserMapper.insertUseGeneratedKeys(hogwartsTestUser);
        return ResultDto.success("成功",hogwartsTestUser);
    }

    /**
     * 更新
     * @param hogwartsTestUser
     * @return
     */
    @Override
    public ResultDto<HogwartsTestUser> update(HogwartsTestUser hogwartsTestUser) {

        hogwartsTestUser.setUpdateTime(new Date());
        hogwartsTestUserMapper.updateByPrimaryKey(hogwartsTestUser);
        //hogwartsTestUserMapper.updateUserDemo(hogwartsTestUser.getUserName(),hogwartsTestUser.getPassword(),hogwartsTestUser.getEmail(),hogwartsTestUser.getId());
        return ResultDto.success("更新成功！", hogwartsTestUser);
    }

    /**
     * 根据用户id 和 名字 模糊查询
     * @param hogwartsTestUser
     * @return
     */
    @Override
    public ResultDto<List<HogwartsTestUser>> searchbyNameorId(HogwartsTestUser hogwartsTestUser) {

        /*List<HogwartsTestUser> hogwartsTestUserList=hogwartsTestUserMapper.searchbyIdorName(hogwartsTestUser.getId(),hogwartsTestUser.getUserName());*/
        List<HogwartsTestUser> hogwartsTestUserList= hogwartsTestUserMapper.select(hogwartsTestUser);
        return ResultDto.success("查询成功！",hogwartsTestUserList);
    }

    /**
     * 根据用户id 删除用户
     * @param userId
     * @return
     */
    @Override
    public ResultDto<HogwartsTestUser> deletebyId(Integer userId) {
        hogwartsTestUserMapper.deleteByPrimaryKey(userId);
        return ResultDto.success("删除成功！");
    }


}


//其他常用注解：
//Repository:用于dao层的bean
//Autowired:用于向一个bean中注入其他bean
//Service:用于service层的bean
//Configuration:用于声明springboot的配置文件类
//Value("${key}"):获取springboot配置文件中的值
//Bean: 声明其为bean实例，常和Configuration配合使用
