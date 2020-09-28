package com.shopArt.shopArt;

import com.shopArt.shopArt.validator.UserValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@SpringBootApplication
public class ShopArtApplication extends RepositoryRestConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(ShopArtApplication.class, args);
	}

	@Override
	public void configureValidatingRepositoryEventListener(
		ValidatingRepositoryEventListener v) {
		v.addValidator("beforeCreate", new UserValidator());
	}
}
