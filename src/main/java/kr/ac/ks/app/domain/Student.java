package kr.ac.ks.app.domain;

import kr.ac.ks.app.controller.StudentForm;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    @Builder
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void update(StudentForm studentForm) {
        setName(studentForm.getName());
        setEmail(studentForm.getEmail());
    }
}
