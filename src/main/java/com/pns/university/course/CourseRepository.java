package com.pns.university.course;

import com.pns.university.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select s from Student s, CourseRating cr, Course c where cr.course.id = c.id and s.id = cr.student.id and c.id = :courseId")
    public List<Student> getStudentsRatingTheCourse(@Param("courseId") Long courseId);
}
