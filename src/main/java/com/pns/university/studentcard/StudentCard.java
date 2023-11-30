package com.pns.university.studentcard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pns.university.student.Student;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentCard {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = MERGE)
    @JoinColumn(name = "studentId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_fk"))
    private  Student student;

    @Column(unique = true,
            length = 10)
    private String cardNumber;

    public StudentCard(Student student, String cardNumber) {
        this.student = student;
        this.cardNumber = cardNumber;
    }

    public StudentCard(Long id, Student student, String cardNumber) {
        this.id = id;
        this.student = student;
        this.cardNumber = cardNumber;
    }
    public StudentCard(Long id, String cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
    }



}


