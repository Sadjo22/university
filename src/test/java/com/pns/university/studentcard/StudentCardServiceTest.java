package com.pns.university.studentcard;

import com.pns.university.student.Student;
import com.pns.university.student.StudentRepo;
import com.pns.university.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class StudentCardServiceTest {
    @Mock
    private StudentCardRepository studentCardRepository;
    private StudentCardService studentCardService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentCardService = new StudentCardService(studentCardRepository);
    }

    @Test
    void itShouldCollectAllTheStudentCards() {
        //Given
        Student student = new Student("peguy", "nya", "peguynya@gmail.fr", 12);
        String cardNum = "cmr123456";
        StudentCard studentCard = new StudentCard(student, cardNum);
        given(studentCardRepository.findAll()).willReturn(List.of(studentCard));

        //When
        List<StudentCard> studentCards = studentCardService.getAllCards();
        //Then
        assertThat(studentCards.size()).isEqualTo(1);
        assertThat(studentCards.get(0).getStudent()).isEqualTo(student);
        assertThat(studentCards.get(0).getCardNumber().equals(cardNum));
    }

}