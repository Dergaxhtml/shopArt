package com.shopArt.shopArt.service;

import com.shopArt.shopArt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    com.shopArt.shopArt.model.entity.User user = userRepository.findByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
  }
}
