package com.shopArt.shopArt.controller;

import com.shopArt.shopArt.registration.OnRegistrationCompleteEvent;
import com.shopArt.shopArt.error.UserAlreadyExistException;
import com.shopArt.shopArt.model.dto.UserDto;
import com.shopArt.shopArt.model.entity.User;
import com.shopArt.shopArt.repository.UserRepository;
import com.shopArt.shopArt.service.UserService;
import com.shopArt.shopArt.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @GetMapping("all")
  public List<User> findAll() {
    return userRepository.findAll();
  }



  @PostMapping("/register")
  public GenericResponse registerUserAccount(
    @Validated final UserDto userDto, HttpServletRequest request) {

    User registered = userService.register(userDto);

    String appUrl = "http://" + request.getServerName() + ":" +
      request.getServerPort() + request.getContextPath();

    eventPublisher.publishEvent(
      new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

    return new GenericResponse("success");
  }
}
