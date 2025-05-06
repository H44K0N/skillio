
package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.model.CourseSection;
import com.example.demo.model.LessonPage;
import com.example.demo.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initCourses(CourseRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Course welding = new Course("Welding", "Learn to weld safely", "/images/courses/welding.webp");
                Course height = new Course("Height-work", "Work safely at heights", "/images/courses/height.webp");
                Course saw = new Course("Circular-saw", "Use the circular saw with precision", "/images/courses/saw.webp");

                Course hms = new Course("HMS", "Health, Environment and Safety basics", "/images/courses/hms.webp");

                CourseSection section = new CourseSection();
                section.setTitle("Intro to HMS");
                section.setCourse(hms);

                LessonPage videoPage = new LessonPage();
                videoPage.setTitle("Hva er HMS?");
                videoPage.setMediaUrl("https://www.youtube.com/watch?v=0_lY5HJ4b9s");
                videoPage.setContent("Se videoen for å få en innføring i hva HMS innebærer.");
                videoPage.setSection(section);

                LessonPage quizPage = new LessonPage();
                quizPage.setTitle("Spørsmål om HMS");
                quizPage.setContent("""
                <ul>
                    <li>Hva står HMS for?</li>
                    <li>Hva kan være en psykisk belastning på arbeidsplassen?</li>
                    <li>Hvordan kan man forebygge helseskader?</li>
                    <li>Hva menes med 'forsvarlig arbeidsmiljø'?</li>
                </ul>
                """);
                quizPage.setSection(section);

                section.setPages(List.of(videoPage, quizPage));
                hms.setSections(List.of(section));

                repository.saveAll(List.of(welding, hms, height, saw));
            }
        };
    }
}
