package com.shopArt.shopArt.validator;

import com.shopArt.shopArt.model.dto.UserDto;
import com.shopArt.shopArt.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateUserValidator")
public class UserValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return UserDto.class.equals(clazz);
  }

  @Override
  public void validate(Object obj, Errors errors) {

    UserDto user = (UserDto) obj;
    if (checkInputString(user.getEmail())) {
      errors.rejectValue("name", "name.empty");
    }

    if (checkInputString(user.getEmail())) {
      errors.rejectValue("email", "email.empty");
    }
  }

  private boolean checkInputString(String input) {
    return (input == null || input.trim().length() == 0);
  }

//  @Override
//  public boolean supports(Class<?> aClass) {
//    return UserDto.class.equals(aClass);
//  }
//
//  @Override
//  public void validate(Object o, Errors errors) {
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
//
//
//    UserDto userDto = (UserDto) o;
//
//    String login = userDto.getEmail();
//    if (login.length() < 3 && login.length() > 25 && isValidEmailAddress(login)) {
//      errors.rejectValue("login", "valid.loginLength");
//    }
//  }
//
//  public boolean isValidEmailAddress(String email) {
//    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//    java.util.regex.Matcher m = p.matcher(email);
//    return m.matches();
//  }
}
