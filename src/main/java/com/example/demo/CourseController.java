package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // HTML-side: Vis skjema for å legge til nytt kurs
    @GetMapping("/new")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form"; // resources/templates/course_form.html
    }

    // HTML-side: Håndter innsending av skjema
    @PostMapping
    public String submitCourseForm(Course course) {
        courseRepository.save(course);
        return "redirect:/courses/new";
    }

    // REST API: Hent alle kurs som JSON
    @ResponseBody
    @GetMapping("/api")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // REST API: Lagre kurs via JSON
    @ResponseBody
    @PostMapping("/api")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    // HTML-side: Liste over alle kurs
    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course_list"; // Vis course_list.html
    }

    // HTML-side: Vis ett spesifikt kurs
    @GetMapping("/{id}")
    public String getCourseById(@PathVariable Long id, Model model) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            model.addAttribute("course", course.get());
            return "course";
        } else {
            return "redirect:/dashboard"; // fallback hvis kurs ikke finnes
        }
    }
}
