package com.getir.readingisgood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.getir.readingisgood.common.SoftwareComponent;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SoftwareComponent(name = "SwaggerConfig", description = "Swagger configuration class.", technologies = { "java" })
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info());
	}

}
