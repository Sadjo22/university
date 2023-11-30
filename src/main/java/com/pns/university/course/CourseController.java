package com.pns.university.course;

import com.pns.university.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCoursesController() {
        return courseService.getCourses();
    }

    @GetMapping("/{courseId}")
    public List<Student> getStudentRatingTheCourseController(@PathVariable("courseId") Long courseId){
        Optional<List<Student>> optionalStudentsList = courseService.getStudentRatingTheCourse(courseId);
        if(optionalStudentsList.isPresent())
            return optionalStudentsList.get();
        return new ArrayList<>();
    }
}
