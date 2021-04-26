package com.testSpringMini.demo.common;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * DemoToken 操作类
 * 1.定义用于缓存token的map
 * 2.获取在线用户数（可选功能）
 * 3.根据token获取TokenDto（userId/userName/defaultJenkinsId/token）
 * 4.用户登录时新增token 和TokenDto
 * 5.用户退出时根据token移除TokenDto
 * 6.判断token是否有效
 *
 * @author Alexie on 2021/4/23
 * @ClassName TokenDb
 * @Description TODO
 * @Version 1.0
 */


@Component
public class TokenDb {

    /**
     * 1.定义用于缓存token的map
     */
    private Map<String,TokenDto> tokenMap= new HashMap<>();


    /**
     * 2.获取在线用户数（可选功能）
     * @return
     */
    public Integer getOnlineUserSize(){
        return  tokenMap.size();
    }

    /**
     * 3.根据token获取TokenDto（userId/userName/defaultJenkinsId/token）
     * @param token
     * @return
     */
    public TokenDto getUserInfo(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        return tokenMap.get(token);
    }

    /**
     * 4.用户登录时新增token 和TokenDto
     * @param token
     * @param tokenDto
     */

    @PostMapping(value = "addUser")
    public void addUserInfo(String token, TokenDto tokenDto){
        if(tokenDto==null){
            return;
        }
        tokenMap.put(token,tokenDto);
    }

    /**
     *  5.用户退出时根据token移除TokenDto
     * @param token
     * @return
     */
    @PostMapping(value = "removeToken")
    public TokenDto removeToken(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }

        return tokenMap.remove(token);
    }


    /**
     *  6.判断token是否有效
     * @param token
     * @return
     */
    public boolean isValid(String token){
        return tokenMap.get(token)!=null;
    }












}
