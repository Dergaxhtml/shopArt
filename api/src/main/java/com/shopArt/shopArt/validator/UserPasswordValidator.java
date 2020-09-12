package com.shopArt.shopArt.validator;

import com.shopArt.shopArt.model.dto.UserDto;
import com.shopArt.shopArt.model.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
public class UserPasswordValidator implements Validator {
  @Override
  public boolean supports(Class<?> aClass) {
    return UserDto.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "valid.passwordConfirm");

    UserDto userDto = (UserDto) o;
    if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
      errors.rejectValue("passwordConfirm", "valid.passwordConfirmDiff");
    }

    String login = userDto.getEmail();
    if (login.length() < 3 || login.length() > 25) {
      errors.rejectValue("login", "valid.loginLength");
    }
  }
}
