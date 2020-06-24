package kr.ac.ks.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

    public void setStudent(Student student) {
        if (this.student != null){
            this.student.getCourses().remove(this);
        }

        this.student = student;

        if (student != null) {
            this.student.getCourses().add(this);
        }
    }

    public void setLesson(Lesson lesson) {
        if (this.lesson != null){
            this.lesson.getCourses().remove(this);
        }

        this.lesson = lesson;

        if (student != null) {
            this.lesson.getCourses().add(this);
        }
        this.lesson.setQuota(this.lesson.getQuota() - 1);
    }

    public static Course createCourse(Student student, Lesson... lessons) {
        Course course = new Course();
        course.setStudent(student);
        Arrays.stream(lessons).forEach(course::setLesson);
        return course;
    }

    public void update(Student student, Lesson... lessons) {
        this.student.getCourses().remove(this);
        setStudent(student);
        this.lesson.setQuota(this.lesson.getQuota() + 1);
        this.lesson.getCourses().remove(this);
        Arrays.stream(lessons).forEach(this::setLesson);
    }

    public void deleteCourse() {
        this.student.getCourses().remove(this);
        this.lesson.setQuota(this.lesson.getQuota() + 1);
        this.lesson.getCourses().remove(this);
        //courseRepository.delete(this);
    }
}
