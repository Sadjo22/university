package com.pns.university.studentcard;

import com.pns.university.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCardService {

    private final StudentCardRepository studentCardRepository;

    @Autowired
    public StudentCardService(StudentCardRepository studentCardRepository) {
        this.studentCardRepository = studentCardRepository;
    }

    public List<StudentCard> getAllCards() {
        return studentCardRepository.findAll();
    }

    public Optional<StudentCard> getStudentCardById(Long id) {
        return studentCardRepository.findById(id);
    }

    public Optional<Student> getStudentByCardNumber(String cardNum) {
        return studentCardRepository.getStudentByCardNum(cardNum);
    }


}
