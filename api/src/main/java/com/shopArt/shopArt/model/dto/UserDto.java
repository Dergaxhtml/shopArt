package com.shopArt.shopArt.model.dto;

import com.shopArt.shopArt.validator.PasswordMatches;
import lombok.Data;

@Data
@PasswordMatches
public class UserDto {
  private String email;
  private String password;
  private String passwordConfirm;
}
