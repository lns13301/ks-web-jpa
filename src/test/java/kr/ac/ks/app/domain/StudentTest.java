package kr.ac.ks.app.domain;

import kr.ac.ks.app.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindByName() {
        String name = "John Doe";
        String email = "john@doe.com";
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        studentRepository.save(student);
        Student johnDoe = studentRepository.findByName("John Doe");
        assertThat(johnDoe.getName()).isEqualTo(name);
        assertThat(johnDoe.getEmail()).isEqualTo(email);
    }

    @Test
    public void testFindAllByEmailIsLike() {
        Student s1 = new Student("s1", "john1@test.com");
        Student s2 = new Student("s2", "john1@test.com");
        Student s3 = new Student("s3", "john1@test.com");
        Student s4 = new Student("s4", "john2@test.com");
        Student s5 = new Student("s5", "john3@test.com");
        studentRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));
        List<Student> john1List = studentRepository.findAllByEmailIsLike("%john1%");
        assertThat(john1List.get(0).getEmail()).isEqualTo("john1@test.com");
    }
}