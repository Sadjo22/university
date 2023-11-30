package com.pns.university.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pns.university.rating.CourseRating;
import com.pns.university.studentcard.StudentCard;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
@EqualsAndHashCode
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence",
                       sequenceName = "student_sequence",
                       allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
                    generator = "student_sequence")
    //@Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,
            unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "student",
              orphanRemoval = true,
              cascade = {PERSIST, REMOVE})
    @JsonIgnore
    private StudentCard studentCard;

    @OneToMany(mappedBy = "student",
            cascade = {PERSIST, REMOVE})
    @JsonManagedReference
    @JsonIgnore
    private List<CourseRating> ratings = new ArrayList<>();


    public Student(Long id, String firstName, String lastName, String email, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public void addCourseRating(CourseRating courseRating) {
        if(!this.ratings.contains(courseRating)){
            this.ratings.add(courseRating);
            courseRating.setStudent(this);
        }
    }

    public void removeCourseRating(CourseRating courseRating) {
        if(this.ratings.contains(courseRating)){
            this.ratings.remove(courseRating);
            courseRating.setStudent(null);
        }
    }

}
