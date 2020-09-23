package com.shopArt.shopArt.service;

import com.shopArt.shopArt.error.UserAlreadyExistException;
import com.shopArt.shopArt.model.dto.UserDto;
import com.shopArt.shopArt.model.entity.User;
import com.shopArt.shopArt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


@Override
  public User register(final UserDto userDto) {
  if (userDto == null) {
    throw new UserAlreadyExistException();
  }
  if (emailExists(userDto.getEmail())) {
    throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
  }
  final User user = new User();

  user.setEmail(userDto.getEmail().toLowerCase());

  user.setPassword(passwordEncoder.encode(userDto.getPassword()));
  return userRepository.save(user);
 }



  private boolean emailExists(final String email) {
    return userRepository.findByEmail(email) != null;
  }
}
