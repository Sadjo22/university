package com.pns.university.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pns.university.rating.CourseRating;
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
//@EqualsAndHashCode
//@AllArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,
            generator = "course_sequence")
    @Setter(AccessLevel.NONE)
    //@Getter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    private int credits;

    @OneToMany(mappedBy = "course",
               cascade = {PERSIST, REMOVE})
    @JsonManagedReference
    @JsonIgnore
    private List<CourseRating> ratings = new ArrayList<>();

    public Course(String name, String instructor, int credits) {
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    public void addCourseRating(CourseRating courseRating) {
        if(!this.ratings.contains(courseRating)){
            this.ratings.add(courseRating);
            courseRating.setCourse(this);
        }
    }

    public void removeCourseRating(CourseRating courseRating) {
        if(this.ratings.contains(courseRating)){
            this.ratings.remove(courseRating);
            courseRating.setCourse(null);
        }
    }
}
