package kr.ac.ks.app.repository;

import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCreate() {
        Student student = studentRepository.save(new Student());
        Lesson lesson = lessonRepository.save(new Lesson());
        Course course = new Course();

        course.setStudent(student);
        course.setLesson(lesson);

        Course persistCourse = courseRepository.save(course);

        assertThat(persistCourse.getLesson().getCourses()).contains(course);
        assertThat(persistCourse.getStudent().getCourses()).contains(course);

        assertThat(persistCourse.getStudent()).isEqualTo(student);
        assertThat(persistCourse.getLesson()).isEqualTo(lesson);
        assertThat(student.getCourses()).hasSize(1);
        assertThat(lesson.getCourses()).hasSize(1);
    }

    @Test
    public void testUpdate() {
        Student student = studentRepository.save(new Student("abc", "test@test.com"));
        Lesson lesson = lessonRepository.save(new Lesson("Web", 15));
        Student student1 = studentRepository.save(new Student("efg", "update@test.com"));
        Lesson lesson1 = lessonRepository.save(new Lesson("Programming", 10));
        Course course = new Course();

        course.setStudent(student);
        course.setLesson(lesson);

        Course persistCourse = courseRepository.save(course);

        persistCourse.update(student1, lesson1);
        courseRepository.flush();

        assertThat(persistCourse.getLesson().getName()).isEqualTo("Programming");
        assertThat(student.getCourses()).hasSize(0);
    }

    @Test
    public void testDelete() {
        Student student = studentRepository.save(new Student("abc", "test@test.com"));
        Lesson lesson = lessonRepository.save(new Lesson("Web", 15));
        Course course = new Course();

        course.setStudent(student);
        course.setLesson(lesson);

        Course persistCourse = courseRepository.save(course);

        persistCourse.deleteCourse();

        assertThat(student.getCourses()).hasSize(0);
        assertThat(lesson.getCourses()).hasSize(0);
    }
}