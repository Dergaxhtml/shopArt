package com.shopArt.shopArt.service;

import com.shopArt.shopArt.error.UserAlreadyExistException;
import com.shopArt.shopArt.model.dto.UserDto;
import com.shopArt.shopArt.model.entity.User;

public interface UserService {
  User register(UserDto userDto) throws UserAlreadyExistException;
}
