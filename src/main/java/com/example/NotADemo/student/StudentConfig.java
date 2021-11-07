package com.example.NotADemo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student john = new Student(
                    "John",
                    "Doe@gmail.com",
                    LocalDate.of(1992, Month.JANUARY, 1)

            );
            Student ron = new Student(
                    "ron",
                    "ron@gmail.com",
                    LocalDate.of(1993, Month.JANUARY, 1)

            );
            Student ellie = new Student(
                    "ellie",
                    "ellie@gmail.com",
                    LocalDate.of(1994, Month.JANUARY, 1)

            );

            studentRepository.saveAll(List.of(john, ron, ellie));
        };
    }
}
