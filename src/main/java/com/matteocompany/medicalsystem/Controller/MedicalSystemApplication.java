package com.matteocompany.medicalsystem.Controller;

import com.matteocompany.medicalsystem.Repository.DBInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.matteocompany.medicalsystem.Repository")
@EntityScan("com.matteocompany.medicalsystem.Model")
public class MedicalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalSystemApplication.class, args);
    }
    @Bean
    @Scope("singleton")
    public DBInitializer DBInitializer() {
        return new DBInitializer();
    }

}
