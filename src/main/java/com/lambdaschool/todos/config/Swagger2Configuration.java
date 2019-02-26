package com.lambdaschool.todos.config;

import com.lambdaschool.todos.todos.ToDoController;
import com.lambdaschool.todos.users.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = { UserController.class, ToDoController.class })
public class Swagger2Configuration {
  @Bean
  public Docket todoAPI() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.lambdaschool.todos"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("To Do's API")
            .description("Java Data Persistence Sprint Challenge. This is a basic todo database scheme with users and a todo list.")
            .contact(new Contact("Danny Vail", "http://www.lambdaschool.com", "danny.e.vail@gmail.com"))
            .license("MIT")
            .licenseUrl("https://github.com/d-vail/Sprint-Challenge-Java-Data-Persistence/blob/master/LICENSE")
            .version("1.0.0")
            .build();
  }
}
