package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LessonController {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonController(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping(value = "/lessons/new")
    public String createForm(Model model) {
        model.addAttribute("lessonForm", new LessonForm());
        return "lessons/lessonForm";
    }

    @PostMapping(value = "/lessons/new")
    public String create(LessonForm form) {
        Lesson lesson = new Lesson();
        lesson.setName(form.getName());
        lesson.setQuota(form.getQuota());
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }

    @GetMapping(value = "/lessons")
    public String list(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons/lessonList";
    }

    @GetMapping("/lessons/delete/{id}")
    public String deleteLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonRepository.findById(id).get();

        List<Course> courses = courseRepository.findAll();
        List<Course> collect = courses.stream().filter(x -> x.getLesson() == lesson).collect(Collectors.toList());
        collect.stream().forEach(x -> x.deleteCourse());
        collect.stream().forEach(x -> courseRepository.delete(x));

        lessonRepository.delete(lesson);
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "redirect:/lessons";
    }
}
