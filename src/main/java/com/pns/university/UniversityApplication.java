package com.pns.university;

import com.github.javafaker.Faker;
import com.pns.university.course.Course;
import com.pns.university.rating.CourseRating;
import com.pns.university.rating.CourseRatingKey;
import com.pns.university.student.Student;
import com.pns.university.student.StudentRepo;
import com.pns.university.studentcard.StudentCard;
import com.pns.university.studentcard.StudentCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (StudentRepo studentRepo, StudentCardRepository studentCardRepository) {
		return args -> {
			Faker faker = new Faker();
			Student student = new Student();
			student.setFirstName(faker.name().firstName());
			student.setLastName(faker.name().lastName());
			student.setEmail(String.format("%s.%s@student.cmr",faker.name().firstName(), faker.name().lastName()));
			student.setAge(faker.number().numberBetween(18,45));
			Random random = new Random();
			Number cardNum = (Number)random.nextInt(1000000);
			Random random1 = new Random();
			StudentCard studentIdCard = new StudentCard(student, "cmr" + cardNum.toString());
			student.setStudentCard(studentIdCard);
			Course course = new Course("Java Language", "Karine Mike", 9) ;
			student.addCourseRating(new CourseRating(new CourseRatingKey(student.getId(), course.getId()), student, course, 5));
			studentRepo.save(student);

		/*	for (int i = 0; i < 5 ; i++) {
				Student student = new Student();
				student.setFirstName(faker.name().firstName());
				student.setLastName(faker.name().lastName());
				student.setEmail(String.format("%s.%s@student.cmr",faker.name().firstName(), faker.name().lastName()));
				student.setAge(faker.number().numberBetween(18,45));
				Random random = new Random();
				Number cardNum = (Number)random.nextInt(1000000);
				Random random1 = new Random();
				StudentCard studentIdCard = new StudentCard(student, "cmr" + cardNum.toString());
				student.setStudentCard(studentIdCard);
				studentRepo.save(student);
			}*/

		};
	}

}
