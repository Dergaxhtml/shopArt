package com.shopArt.shopArt.config;

//import com.shopArt.shopArt.validator.UserPasswordValidator;

import com.shopArt.shopArt.validator.UserPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;

@Configuration
@EnableWebSecurity
public class SecurityConfig
  extends WebSecurityConfigurerAdapter {


  @Autowired
  @Qualifier("customUserDetailsService")
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public Validator userPasswordValidator() {
    return new UserPasswordValidator();
  }
  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/").permitAll()
      //.antMatchers("/invalidSession*").anonymous()
      //.antMatchers("/user/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
      // .anyRequest().hasAuthority("READ_PRIVILEGE")
      .and()
      .formLogin().disable()
//      .loginPage("/login")
//      .defaultSuccessUrl("/homepage.html")
//      .failureUrl("/login?error=true")
//      .successHandler(myAuthenticationSuccessHandler)
//      .failureHandler(authenticationFailureHandler)
//      .authenticationDetailsSource(authenticationDetailsSource)
//      .permitAll()
//      .and()
//      .sessionManagement()
//      .invalidSessionUrl("/invalidSession.html")
//      .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
//      .sessionFixation().none()
//      .and()
      .logout()
//      .logoutSuccessHandler(myLogoutSuccessHandler)
//      .invalidateHttpSession(false)
//      .logoutSuccessUrl("/logout.html?logSucc=true")
//      .deleteCookies("JSESSIONID")
      .permitAll();
//      .and()
//      .rememberMe().rememberMeServices(rememberMeServices()).key("theKey");
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    auth.authenticationProvider(authProvider);
  }

    
}
