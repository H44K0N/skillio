package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initCourses(CourseRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Course("Welding", "Learn to weld safely", "/images/courses/welding.webp"));
                repository.save(new Course("HMS", "Health, Environment and Safety basics", "/images/courses/hms.webp"));
                repository.save(new Course("Height-work", "Work safely at heights", "/images/courses/height.webp"));
                repository.save(new Course("Circular-saw", "Use the circular saw with precision", "/images/courses/saw.webp"));
            }
        };
    }
}
