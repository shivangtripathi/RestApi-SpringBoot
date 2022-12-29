package com.example.restapi.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	  
	  @Bean CommandLineRunner commandLineRunner(StudentRepository repository) {
	  return args->{ Student rahul = new Student(
	  "Rahul Sharma","rahulsharma@gmail.com",LocalDate.of(2000, 8, 10));
	  
	  Student ramesh = new Student(
	  "Ramesh Tyagi","rameshtyagi@gmail.com",LocalDate.of(1999, 8, 10));
	  
	  repository.saveAll(List.of(rahul,ramesh));
	  
	  
	  }; }
	 
	 
}
