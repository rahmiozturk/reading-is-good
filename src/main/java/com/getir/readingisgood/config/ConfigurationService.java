package com.getir.readingisgood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.getir.readingisgood.ApplicationConfiguration;

@Component
public class ConfigurationService {

	@Autowired
	private ApplicationConfiguration properties;

	/**
	 * This method provide Authenticated user class.
	 */
	public ApplicationConfiguration.User getCurrentUser() {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		return properties.getUsers().get(currentUsername);
	}

	/**
	 * This method provide Authenticated user name.
	 */
	public String getCurrentUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
