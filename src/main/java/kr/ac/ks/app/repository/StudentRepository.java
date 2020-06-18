package kr.ac.ks.app.repository;

import kr.ac.ks.app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(final String name);
    List<Student> findAllByEmailIsLike(final String email);
}
