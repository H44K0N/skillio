package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.CourseSection;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sections")
public class CourseSectionController {

    @Autowired
    private CourseSectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Vis alle seksjoner for et spesifikt kurs
    @GetMapping("/course/{courseId}")
    public String getSectionsByCourse(@PathVariable Long courseId, Model model) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            List<CourseSection> sections = sectionRepository.findByCourseId(courseId);
            model.addAttribute("sections", sections);
            model.addAttribute("course", course.get());
            return "section_list"; // Lager senere: resources/templates/section_list.html
        } else {
            return "redirect:/dashboard";
        }
    }

    // Vis skjema for å legge til ny seksjon
    @GetMapping("/course/{courseId}/new")
    public String showNewSectionForm(@PathVariable Long courseId, Model model) {
        CourseSection section = new CourseSection();
        section.setCourse(new Course());
        section.getCourse().setId(courseId);
        model.addAttribute("section", section);
        return "section_form"; // Lager senere: resources/templates/section_form.html
    }

    // Lagre ny seksjon
    @PostMapping
    public String saveSection(@ModelAttribute CourseSection section) {
        sectionRepository.save(section);
        return "redirect:/sections/course/" + section.getCourse().getId();
    }

    // Hent én seksjon via API (valgfritt)
    @ResponseBody
    @GetMapping("/api/{id}")
    public CourseSection getSectionById(@PathVariable Long id) {
        return sectionRepository.findById(id).orElse(null);
    }
}