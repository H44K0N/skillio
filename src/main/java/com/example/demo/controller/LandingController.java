package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LandingController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/dashboard")
    public String showLandingPage(Authentication auth, Model model) {
        if (auth != null && auth.isAuthenticated()) {
            List<Course> courses = courseRepository.findAll(); // TODO: filtrer ut fullførte kurs senere
            model.addAttribute("courses", courses);
            return "dashboard";
        }
        return "index"; // hvis ikke innlogget
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
