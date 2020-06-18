package kr.ac.ks.app;

import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(StudentRepository studentRepository,
									LessonRepository lessonRepository){
		return (args) -> {
			studentRepository.save(Student.builder().name("1").email("1@1.com").build());
			studentRepository.save(Student.builder().name("2").email("2@2.com").build());
			lessonRepository.save(Lesson.builder().name("L1").quota(15).build());
			lessonRepository.save(Lesson.builder().name("L2").quota(15).build());
		};
	}

}
