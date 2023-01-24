package com.matheussilvadev.pessoas.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringForConfig implements WebMvcConfigurer{
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.matheussilvadev.pessoas.api"))
					.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Pessoas", "Gerencia pessoas"));
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Api Pessoas")
			.description("Api de pessoas (Desafio Técnico)")
			.version("1")
			.contact(new Contact("Matheus Pereira da Silva", "https://github.com/Matheuspsilva/", "matheuspsilva29@gmai.com"))
			.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
	}

}
