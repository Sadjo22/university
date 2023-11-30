package com.pns.university.studentcard;

import com.pns.university.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {
    @Query("select s from StudentCard card, Student s where card.student.id = s.id and card.cardNumber = :cardNum")
    public Optional<Student> getStudentByCardNum(@Param("cardNum") String cardNum);
}
