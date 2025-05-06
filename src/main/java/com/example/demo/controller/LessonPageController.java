package com.example.demo.controller;

import com.example.demo.model.LessonPage;
import com.example.demo.repository.LessonPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lessons")
public class LessonPageController {

    @Autowired
    private LessonPageRepository lessonPageRepository;

    // HTML-side: Vis én bestemt leksjon
    @GetMapping("/{id}")
    public String viewLesson(@PathVariable Long id, Model model) {
        Optional<LessonPage> lesson = lessonPageRepository.findById(id);
        if (lesson.isPresent()) {
            model.addAttribute("lesson", lesson.get());
            return "lesson"; // Lag lesson.html
        } else {
            return "redirect:/courses";
        }
    }

    // REST API: Hent alle leksjoner
    @ResponseBody
    @GetMapping("/api")
    public List<LessonPage> getAllLessons() {
        return lessonPageRepository.findAll();
    }

    // REST API: Lagre ny leksjon
    @ResponseBody
    @PostMapping("/api")
    public LessonPage createLesson(@RequestBody LessonPage lessonPage) {
        return lessonPageRepository.save(lessonPage);
    }

    // HTML: Listevisning av alle leksjoner (valgfritt)
    @GetMapping
    public String listLessons(Model model) {
        model.addAttribute("lessons", lessonPageRepository.findAll());
        return "lesson_list"; // Lag lesson_list.html om du ønsker
    }
}
