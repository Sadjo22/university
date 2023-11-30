package com.pns.university.studentcard;

import com.pns.university.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/studentCard")
public class StudentCardController {
    private final StudentCardService studentCardService;

    @Autowired
    public StudentCardController(StudentCardService studentCardService) {
        this.studentCardService = studentCardService;
    }

    @GetMapping
    public List<StudentCard> getAllCardsController() {
        return studentCardService.getAllCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getStudentCardByIdController (@PathVariable("id") Long cardId) {
        Optional<StudentCard> optionalStudentCard = studentCardService.getStudentCardById(cardId);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String responseBody;

        if(optionalStudentCard.isPresent()) {
            responseBody = "StudentCard present in the Db";
            headers.add("StudentCardResponse", "Ok");
            status = HttpStatus.OK;
        } else {
            responseBody = "The studentCard with the id " + cardId + " doesn't exist in the Db";
            headers.add("StudentCardResponse", "Not Found");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseBody, headers, status);
    }

    @GetMapping("/cardNumber/{card}")
    public ResponseEntity<String> getStudentCardByIdController(@PathVariable("card") String cardNum) {
        Optional<Student> optionalStudent = studentCardService.getStudentByCardNumber(cardNum);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String responseBody;

        if(optionalStudent.isPresent()){
             responseBody = "Student with the card number " + cardNum + " is present in the Db";
             headers.add("StudentResponse", "Ok");
             status = HttpStatus.OK;
        } else {
             responseBody = "The student with the card number " + cardNum + " doesn't exist in the Db";
             headers.add("StudentResponse", "Not Found");
             status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseBody, headers, status);
    }
}
