package kr.ac.ks.app.repository;


import kr.ac.ks.app.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
