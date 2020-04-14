package com.system.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration{
	@Bean
	public Docket postsApi() {
		//Docket decides what kind of APIs you would wnat to document
		return new Docket(DocumentationType.SWAGGER_2).groupName("Python Examination System -api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}
	@SuppressWarnings("unchecked")
	private Predicate<String> postPaths() {
		return or(regex("/system.*"), regex("/api.*"));
	}
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Python Examination System Documentation")
				.description("Python Examination System Restful API")
				.termsOfServiceUrl("http://swagger.com")
				.contact("Neil Shah").license("@CogentInfotech")
				.licenseUrl("cogent@gmail.com").version("1.0").build();
	}
}