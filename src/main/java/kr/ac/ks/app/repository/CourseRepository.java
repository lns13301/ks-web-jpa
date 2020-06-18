package kr.ac.ks.app.repository;

import kr.ac.ks.app.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
