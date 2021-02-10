package com.testSpringMini.demo.service;

import com.testSpringMini.demo.dto.UserDto;
import com.testSpringMini.demo.dto.addUserDto;

public interface AJUserService {

    public String login(UserDto userDto);

    public String register(addUserDto addUserDto);
}
