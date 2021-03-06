package com.cognitivescale.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	/**
	 * Product api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognitivescale.controller")).paths(postPaths()).build()
				.apiInfo(metaData());
	}

	/**
	 * Post paths.
	 *
	 * @return the predicate
	 */
	@SuppressWarnings("unchecked")
	private Predicate<String> postPaths() {
		return or(regex("/account.*"), regex("/beneficiary.*"), regex("/transaction.*"));
	}

	/**
	 * Meta data.
	 *
	 * @return the api info
	 */
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Bank API - Cognitive Scale").build();
	}

	/**
	 * Adds resource handlers.
	 *
	 * @param registry the registry
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
