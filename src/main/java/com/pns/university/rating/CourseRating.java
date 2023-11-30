package com.pns.university.rating;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pns.university.course.Course;
import com.pns.university.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CourseRating {
    @EmbeddedId
    private CourseRatingKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id",
            foreignKey = @ForeignKey(name = "rating_student_id_fk"))
    @JsonBackReference
    private Student student;


    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id",
            foreignKey = @ForeignKey(name = "rating_course_id_fk"))
    @JsonBackReference
    private Course course;

    @Column(nullable = false)
    private int rating;
}
