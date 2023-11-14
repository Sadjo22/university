package com.pns.university.student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudentsService();
    };

    @PostMapping("/addNewStudent")
    public ResponseEntity<String> addNewStudent(@RequestBody Student student) {
        Student studentResponse = studentService.addNewStudentService(student);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String responseBody;
        if(studentResponse.equals(new Student())) {
            responseBody = "The student already exists in the Db";
            headers.add("StudentResponse", "Not Created");
            status = HttpStatus.ALREADY_REPORTED;
        } else {
            responseBody = "Student created successfully in the Db";
            headers.add("StudentResponse", "Created");
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseBody, headers, status);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent (@PathVariable("studentId") Long id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String responseBody;

        if(studentService.deleteStudentService(id).equals("OK")) {
            responseBody = "Student deleted successfully in the Db";
            headers.add("StudentResponse", "Deleted");
            status = HttpStatus.OK;
        } else {
            responseBody = "The student doesn't exist in the Db";
            headers.add("StudentResponse", "Not Found");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseBody, headers, status);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") Long id,
                                                @RequestBody Student student){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String responseBody;

        if(studentService.updateStudentService(id, student).equals("OK")) {
            responseBody = "Student updated successfully in the Db";
            headers.add("StudentResponse", "Updated");
            status = HttpStatus.OK;
        } else {
            responseBody = "The student doesn't exist in the Db";
            headers.add("StudentResponse", "Not Found");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseBody, headers, status);
    }
}
