package com.capstonelegal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
/*
@OpenAPIDefinition(tags = {
        @Tag(name = "Country Management", description = "Operations pertaining to countries in Capstone Legal"),
        @Tag(name = "State Management", description = "Operations pertaining to states in Capstone Legal"),
        @Tag(name = "District Management", description = "Operations pertaining to districts in Capstone Legal"),
        @Tag(name = "City Management", description = "Operations pertaining to cities in Capstone Legal"),
        @Tag(name = "Organization Management", description = "Operations pertaining to organizations in Capstone Legal"),
        @Tag(name = "Departments Management", description = "Operations pertaining to departments in Capstone Legal"),
        @Tag(name = "Contacts Management", description = "Operations pertaining to contacts in Capstone Legal"),
        @Tag(name = "Employee Management", description = "Operations pertaining to employees in Capstone Legal"),
        @Tag(name = "Judge Management", description = "Operations pertaining to judges in Capstone Legal"),
        @Tag(name = "Court Management", description = "Operations pertaining to courts in Capstone Legal"),
        @Tag(name = "Legal Practice Management", description = "Operations pertaining to Legal Practices in Capstone Legal"),
        @Tag(name = "Legal Case Management", description = "Operations pertaining to legal cases in Capstone Legal"),
        @Tag(name = "Legal Documents Management", description = "Operations pertaining to legal documents in Capstone Legal"),
        @Tag(name = "Tasks Management", description = "Operations pertaining to tasks in Capstone Legal")
})*/
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}