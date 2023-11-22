package com.pns.university.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentServiceTest {

    private final StudentRepo studentRepo;
    private StudentService studentService;

    @Autowired
    public StudentServiceTest(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepo);
        studentRepo.deleteAll();
    }

    @Test
    void itShouldGetStudentsService() {
        //Given
        Long id = 1L;
        String firstname = "peguy";
        String lastname = "sadjo";
        String mail = "kafra@gmail.com";
        int age = 22;
        Student student = new Student(id, firstname, lastname, mail, age);
        Student student1 = new Student(2L, "POPO", "ZOZO", "zozo@gmail.com", 12);
        studentRepo.saveAll(List.of(student1, student));
        //When
        List<Student> studentList = studentService.getStudentsService();
        //Then
         assertThat(studentList.size()).isEqualTo(2);
         assertThat(studentList.contains(student));
         assertThat(studentList.contains(student1));
         assertEquals(studentList.get(1).getFirstName(),firstname);
         assertEquals(studentList.get(1).getLastName(),lastname);
         assertEquals(studentList.get(1).getEmail(),mail);
         assertEquals(studentList.get(1).getAge(),age);
    }

    @Test
    void itShouldAddNewStudentService() {
        //Given
        Long id = 1L;
        String firstname = "peguy2";
        String lastname = "sadjo2";
        String mail = "kafra2@gmail.com";
        int age = 82;
        Student student = new Student(id, firstname, lastname, mail, age);
        //When
        Student student1 = studentService.addNewStudentService(student);
        //Then
        assertThat(student1).isEqualTo(student);
        assertEquals(student1.getFirstName(),firstname);
        assertEquals(student1.getLastName(),lastname);
        assertEquals(student1.getEmail(),mail);
        assertEquals(student1.getAge(),age);
    }

    @Test
    void itShouldNotAddNewStudentWithWrongEmail() {
        //Given
        Long id = 1L;
        String firstname = "peguy2";
        String lastname = "sadjo2";
        String mail = "@gmail.com";
        int age = 82;
        Student student = new Student(id, firstname, lastname, mail, age);
        //When
        Student student1 = studentService.addNewStudentService(student);
        //Then
        assertThat(student1).isEqualTo(new Student());
    }

    @Test
    void itShouldNotAddNewStudentService() {
        //Given
        Long id = 1L;
        String firstname = "peguy";
        String lastname = "sadjo";
        String mail = "kafra23@gmail.com";
        int age = 22;
        Student student = new Student(id, firstname, lastname, mail, age);
        Student studentFake = new Student(1L, "soso", "roro", "popo@gmail.com", 32);
        studentRepo.save(student);
        //When
        Student student1 = studentService.addNewStudentService(studentFake);
        //Then
        assertThat(student1).isEqualTo(new Student());
        assertThat(studentRepo.findAll().size()).isEqualTo(1);
    }

    @Test
    void itShouldDeleteStudentService() {
        //Given
        Long id = 2L;
        String firstname = "peguy";
        String lastname = "sadjo";
        String mail = "kafra23@gmail.com";
        int age = 22;
        Student student = new Student(id, firstname, lastname, mail, age);
        studentRepo.save(student);
        //When
        String result = studentService.deleteStudentService(id);
        //Then
        assertThat(studentRepo.findAll().size()).isEqualTo(0);
        assertThat(result).isEqualTo("OK");
    }

    @Test
    void itShouldNotDeleteStudentService() {
        //Given
        Long id = 4L;
        //When
        String result = studentService.deleteStudentService(id);
        //Then
        assertThat(studentRepo.findAll().size()).isEqualTo(0);
        assertThat(result).isEqualTo("KO");
    }

    @Test
    void itShouldUpdateStudentService() {
        //Given
        //When
        //Then
    }
}