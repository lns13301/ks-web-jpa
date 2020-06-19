package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/students/new")
    public String showStudentForm(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "students/studentForm";
    }

    @PostMapping("/students/new")
    public String createStudent(@Valid StudentForm studentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "students/studentForm";
        }

        Student student = new Student();
        student.setName(studentForm.getName());
        student.setEmail(studentForm.getEmail());
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String list(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students/studentList";
    }

    @GetMapping("/students/update/{id}")
        public String updateStudentPage(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).get();

        model.addAttribute("studentForm", student);
        return "students/studentUpdateForm";
    }

    @PostMapping("/students/update/edit/{id}")
    public String updateStudent(@PathVariable Long id, @Valid StudentForm studentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "students/studentUpdateForm";
        }

        Student student = studentRepository.findById(id).get();
        student.update(studentForm);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id).get();

        List<Course> courses = courseRepository.findAll();
        List<Course> collect = courses.stream().filter(x -> x.getStudent() == student).collect(Collectors.toList());
        collect.forEach(Course::deleteCourse);
        collect.forEach(courseRepository::delete);

        studentRepository.delete(student);
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "redirect:/students";
    }
}
