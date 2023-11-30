package com.pns.university.course;

import com.pns.university.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Optional<List<Student>> getStudentRatingTheCourse(Long courseId) {
        if(courseId == null)
            return Optional.of(new ArrayList<>());
        return Optional.of(courseRepository.getStudentsRatingTheCourse(courseId));
    }

}
