package com.pns.university.student;

import com.pns.university.utils.EmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private static final Logger logger = Logger.getLogger(StudentService.class.getName());

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public List<Student> getStudentsService() {
        return studentRepo.findAll();
    };

    public Student addNewStudentService(Student student) {
        Optional<Student> optionalStudent = studentRepo.findById(student.getId());
        if(!optionalStudent.isPresent()) {
            //validation of emailAddress
            boolean isValidEmail = EmailValidation.isValidEmailAddress(student.getEmail());
            if (!isValidEmail){
                logger.info("Wrong Email Address");
                return new Student();
            }

            Student student1 = studentRepo.save(student);
            logger.info("student added succesfully");
            return student1;
        } else {
            logger.info("student already present in the Db");
            return new Student();
        }
    }

    public String deleteStudentService(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if(optionalStudent.isPresent()) {
            logger.info("student " + optionalStudent.get().getFirstName() + " deleted successfully");
            studentRepo.delete(optionalStudent.get());
            return "OK";
        } else {
            logger.info("student with id " + id + " not present in the Db");
            return "KO";
        }
    }

    public String updateStudentService(Long id, Student student) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if(optionalStudent.isPresent()) {
            optionalStudent.get().setEmail(student.getEmail());
            optionalStudent.get().setAge(student.getAge());
            studentRepo.save(optionalStudent.get());
            logger.info("student updated successfully");
            return "OK";
        } else {
            logger.info("student not present in the Db");
            return "KO";
        }
    }
}
