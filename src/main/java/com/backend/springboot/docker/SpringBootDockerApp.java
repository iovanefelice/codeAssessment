package com.backend.springboot.docker;
import com.backend.springboot.docker.service.PersonService;
import com.backend.springboot.docker.service.impl.PersonServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.backend.springboot.docker.repository")
public class SpringBootDockerApp {


  public static void main(String[] args) {
    SpringApplication.run(SpringBootDockerApp.class, args);

  }

  @Bean
  PersonService personServiceBean(){
    return new PersonServiceImpl();
  }


}
