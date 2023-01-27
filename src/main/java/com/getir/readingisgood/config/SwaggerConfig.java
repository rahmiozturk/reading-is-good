package com.getir.readingisgood.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.getir.readingisgood.common.SoftwareComponent;
import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SoftwareComponent(name = "SwaggerConfig", description = "Swagger configuration class.", technologies = { "java" })
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/book/.*"), regex("/customer/.*"), regex("/order/.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Reading Is Good API")
				.description("ReadingIsGood is an online books retail firm which operates only on the Internet")
				.termsOfServiceUrl("http://getir.com").license("ReadingIsGood License")
				.licenseUrl("getir.com").version("1.0").build();
	}

}
